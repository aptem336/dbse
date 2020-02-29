package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.persist.AbstractPersistService;

import java.io.Serializable;

public abstract class AbstractController<T extends AbstractEntity> implements Serializable {

    public void commit(T t) {
        switch (t.getState()) {
            case TRANSIENT:
                t.setState(AbstractEntity.AbstractEntityState.PERSISTENT);
                getService().persist(t);
                break;
            case PERSISTENT:
                getService().merge(t);
                break;
            case REMOVED:
                break;
            case DETACHED:
                break;
        }
    }

    public void remove(T t) {
        t.setState(AbstractEntity.AbstractEntityState.REMOVED);
    }

    protected abstract AbstractPersistService<T> getService();
}