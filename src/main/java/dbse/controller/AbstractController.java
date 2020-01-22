package dbse.controller;

import dbse.service.AbstractService;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

public abstract class AbstractController<AbstractEntity> implements Converter {

    private List<AbstractEntity> abstractEntityList;

    private Class<AbstractEntity> abstractEntityClass;

    AbstractController(Class<AbstractEntity> abstractEntityClass) {
        this.abstractEntityClass = abstractEntityClass;
    }

    abstract AbstractService<AbstractEntity> getService();

    public AbstractEntity add(AbstractEntity abstractEntity) {
        abstractEntityList.add(abstractEntity);
        getService().persist(abstractEntity);
        return abstractEntity;
    }

    public AbstractEntity add() throws IllegalAccessException, InstantiationException {
        return add(abstractEntityClass.newInstance());
    }

    public void remove(AbstractEntity abstractEntity) {
        abstractEntityList.remove(abstractEntity);
        getService().remove(abstractEntity);
    }

    public void remove(long id) {
        remove(getService().getById(id));
    }

    public void save(AbstractEntity abstractEntity) {
        getService().merge(abstractEntity);
    }

    public void save() {
        abstractEntityList.forEach(this::save);
    }

    public List<AbstractEntity> getAbstractEntityList() {
        return abstractEntityList;
    }

    public void setAbstractEntityList(List<AbstractEntity> abstractEntityList) {
        this.abstractEntityList = abstractEntityList;
    }

    @PostConstruct
    private void postConstruct() {
        abstractEntityList = getService().getAll();
    }

    @Override
    public AbstractEntity getAsObject(FacesContext context, UIComponent component, String id) {
        return getService().getById(Long.parseLong(id));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object abstractEntity) {
        return ((dbse.entity.AbstractEntity) abstractEntity).getId() + "";
    }
}