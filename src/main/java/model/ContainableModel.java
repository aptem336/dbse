package model;

//Q bidirectional <M extends Model & ContainableModel<M, C>, C extends Model & ModelContainer<C, M>>
public interface ContainableModel<C extends Model> {
    C getContainer();

    void setContainer(C e);
}
