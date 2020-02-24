package dbse.model;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractEntity {

    {
        state = AbstractEntityState.persisted;//Q STATES PROCESSING?
    }

    @Id
    @GeneratedValue
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Transient
    private AbstractEntityState state;

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
        //Q STATES NAMING:
        // TRANSIENT,
        // DETACHED,
        // PERSISTED?
        added,
        changed,
        removed,
        persisted
    }
}
