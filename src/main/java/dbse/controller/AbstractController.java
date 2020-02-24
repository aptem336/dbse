package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.persist.AbstractPersistService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractController<T extends AbstractEntity> implements Serializable {

    abstract T getEntity();

    abstract AbstractPersistService<T> getService();

    private List<T> list;

    public T create() {
        T t = getEntity();
        t.setState(AbstractEntity.AbstractEntityState.added);
        add(t);
        return t;
    }

    public void add(T t) {
        list.add(t);
    }

    public void remove(T t) {
        t.setState(AbstractEntity.AbstractEntityState.removed);
    }

    @PostConstruct
    private void readAll() {
        list = getService().selectAll();
    }

    public void writeAll() {
        list.forEach(this::write);
        readAll();
    }

    public void write(T t) {
        switch (t.getState()) {
            case added:
//                getService().persist(t);
//                break;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}