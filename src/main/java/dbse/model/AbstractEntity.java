package dbse.model;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    private String id;
    @Transient
    private AbstractEntityState state;

    {
        state = AbstractEntityState.PERSISTENT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AbstractEntityState getState() {
        return state;
    }

    public void setState(AbstractEntityState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }

    public enum AbstractEntityState {
        TRANSIENT,
        CHANGED,
        PERSISTENT,
        REMOVED,
        DETACHED
    }
}
