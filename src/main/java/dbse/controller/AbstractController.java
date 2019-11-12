package dbse.controller;

import dbse.service.AbstractService;
import javax.annotation.PostConstruct;
import java.util.List;

public abstract class AbstractController<AbstractEntity> {

    private List<AbstractEntity> list;

    abstract AbstractService<AbstractEntity> getService();

    abstract AbstractEntity getEntity();

    public void add() {
        AbstractEntity abstractEntity = getEntity();
        list.add(abstractEntity);
        getService().add(abstractEntity);
    }

    public void remove(long id) {
        AbstractEntity abstractEntity = getService().getById(id);
        list.remove(abstractEntity);
        getService().remove(abstractEntity);
    }

    public void save() {
        list.forEach(getService()::save);
    }

    public List<AbstractEntity> getList() {
        return list;
    }

    public void setList(List<AbstractEntity> list) {
        this.list = list;
    }

    @PostConstruct
    private void postConstruct() {
        list = getService().getAll();
    }
}