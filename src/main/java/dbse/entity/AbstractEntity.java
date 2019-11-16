package dbse.entity;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private long id;

    public AbstractEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
