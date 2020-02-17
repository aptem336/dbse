package dbse.entity;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractEntity {

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
    private boolean delete;

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
