package dbse.entity;

import dbse.service.AbstractService;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = AbstractService.getAll, query = "SELECT e FROM Relation e")
public class AbstractEntity {

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
