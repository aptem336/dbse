package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.persist.AbstractPersistService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractController<T extends AbstractEntity> implements Serializable {

    public void syncWithDataSource(T t) {
        switch (t.getState()) {
            case added:
                t.setState(AbstractEntity.AbstractEntityState.persisted);
                getService().persist(t);
                break;
            case changed:
                getService().merge(t);
                break;
            case removed:
                getService().remove(t);
                break;
            case persisted:
                break;
        }
    }

    @PostConstruct
    private void getListFromDataSource() {
        list = getService().selectAll();
    }

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void add(T t) {
        list.add(t);
        t.setState(AbstractEntity.AbstractEntityState.added);
    }

    public void remove(T t) {
        t.setState(AbstractEntity.AbstractEntityState.removed);
    }

    protected abstract AbstractPersistService<T> getService();
}