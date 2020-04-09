package model;

public interface ContainableModel<C extends Model> {
    C getContainer();

    void setContainer(C e);
}
