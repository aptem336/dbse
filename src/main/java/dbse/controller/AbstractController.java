package dbse.controller;

import dbse.service.AbstractService;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class AbstractController<AbstractEntity> {

    private List<AbstractEntity> abstractEntityList;

    private Class<AbstractEntity> abstractEntityClass;

    AbstractController(Class<AbstractEntity> abstractEntityClass) {
        this.abstractEntityClass = abstractEntityClass;
    }

    abstract AbstractService<AbstractEntity> getService();

    public void add() throws IllegalAccessException, InstantiationException {
        AbstractEntity abstractEntity = abstractEntityClass.newInstance();
        abstractEntityList.add(abstractEntity);
        getService().add(abstractEntity);
    }

    public void remove(long id) {
        AbstractEntity abstractEntity = getService().getById(id);
        abstractEntityList.remove(abstractEntity);
        getService().remove(abstractEntity);
    }

    public void save() {
        abstractEntityList.forEach(getService()::save);
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
}