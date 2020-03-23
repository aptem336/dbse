package dbse.controller;

import dbse.model.AbstractModel;

import java.io.Serializable;

public abstract class AbstractController<T extends AbstractModel> implements Serializable {
    public void remove(T t) {
        t.setState(AbstractModel.AbstractEntityState.REMOVED);
    }
}
