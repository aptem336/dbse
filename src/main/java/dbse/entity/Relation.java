package dbse.entity;

import dbse.service.RelationService;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = RelationService.getAllNamedQueryName, query = "SELECT r FROM Relation r")
public class Relation extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
