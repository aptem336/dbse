package model;

import java.util.List;

public interface ModelContainer<C extends ModelContainer<C, M>, M extends ContainableModel<M, C>> {
    default void addContainedModel(M m) {
        getContainedModels().add(m);
        m.setContainer(getSelf());
    }

    default void removeContainedModel(M m) {
        getContainedModels().remove(m);
        m.setContainer(null);
    }

    C getSelf();

    List<M> getContainedModels();
}
