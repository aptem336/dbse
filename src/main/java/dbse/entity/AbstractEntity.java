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
    private Long id;
    private String attribute;

    public AbstractEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String name) {
        this.attribute = name;
    }
}
