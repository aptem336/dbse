package model;

public interface ContainableModel<M extends ContainableModel<M, C>, C extends ModelContainer<C, M>> {
    C getContainer();

    void setContainer(C e);
}
