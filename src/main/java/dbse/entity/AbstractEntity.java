package dbse.entity;

import dbse.service.AbstractService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = AbstractService.getAll, query = "SELECT r FROM AbstractEntity r")
public class AbstractEntity {

    @Id
    @GeneratedValue
    private long id;
    private String attribute;

    public AbstractEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String name) {
        this.attribute = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractEntity) {
            return obj.getClass() == this.getClass() && ((AbstractEntity) obj).id == this.id;
        }
        return false;
    }
}
