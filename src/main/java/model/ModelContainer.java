package model;

import java.util.List;

public interface ModelContainer<C extends Model, E extends Model & ContainableModel<C>> {
    void addContainedModel(E e);

    void removeContainedModel(E e);

    List<E> getContainedModels();
}
