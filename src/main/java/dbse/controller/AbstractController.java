package dbse.controller;

import dbse.model.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractController<T extends AbstractEntity> implements Serializable {

    public void remove(T t) {
        t.setState(AbstractEntity.AbstractEntityState.REMOVED);
    }
}