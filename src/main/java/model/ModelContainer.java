package model;

import java.util.List;

public interface ModelContainer<M extends Model> {
    void addContainedModel(M m);

    void removeContainedModel(M m);

    List<M> getContainedModels();
}
