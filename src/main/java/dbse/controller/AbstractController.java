package dbse.controller;

import dbse.service.AbstractService;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

public abstract class AbstractController<AbstractEntity> implements Converter {

    private List<AbstractEntity> abstractEntityList;

    abstract AbstractEntity getEntity();

    abstract AbstractService<AbstractEntity> getService();

    public AbstractEntity create() {
        return add(getEntity());
    }

    public AbstractEntity add() {
        return add(getEntity());
    }

    public AbstractEntity add(AbstractEntity abstractEntity) {
        abstractEntityList.add(abstractEntity);
        return getService().persist(abstractEntity);
    }

    public void remove(AbstractEntity abstractEntity) {
        abstractEntityList.remove(abstractEntity);
        getService().remove(abstractEntity);
    }

    public void remove(long id) {
        remove(getService().getById(id));
    }

    public AbstractEntity save(AbstractEntity abstractEntity) {
        return getService().merge(abstractEntity);
    }

    public void saveAbstractEntityList() {
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