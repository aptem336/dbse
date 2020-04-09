package model;

import java.util.List;

public interface ModelContainer<C extends Model & ModelContainer<C, M>, M extends Model & ContainableModel<C>> {
    default void addContainedModel(M m) {
        getContainedModels().add(m);
        m.setContainer((C) this);//Q
    }

    default void removeContainedModel(M m) {
        getContainedModels().remove(m);
        m.setContainer(null);
    }

    List<M> getContainedModels();
}
