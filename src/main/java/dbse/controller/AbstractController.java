package dbse.controller;

import dbse.service.AbstractService;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractController<AbstractEntity> implements Converter<AbstractEntity>, Serializable {

    private List<AbstractEntity> abstractEntityList;

    abstract AbstractEntity getEntity();

    abstract AbstractService<AbstractEntity> getService();

    public AbstractEntity create() {
        return add(getEntity());
    }

    public AbstractEntity add(AbstractEntity abstractEntity) {
        abstractEntityList.add(abstractEntity);
        return abstractEntity;
    }

    public AbstractEntity remove(AbstractEntity abstractEntity) {
        abstractEntityList.remove(abstractEntity);
        return abstractEntity;
    }

    public AbstractEntity saveToDB(AbstractEntity abstractEntity) {
        return getService().merge(abstractEntity);
    }

    public void saveAbstractEntityListToDB() {
        abstractEntityList.forEach(this::saveToDB);
    }

    @PostConstruct
    private void readEntityListFromDB() {
        abstractEntityList = getService().getAll();
    }

    public List<AbstractEntity> getAbstractEntityList() {
        return abstractEntityList;
    }

    public void setAbstractEntityList(List<AbstractEntity> abstractEntityList) {
        this.abstractEntityList = abstractEntityList;
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