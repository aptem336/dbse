package dbse.entity;

import dbse.service.AttributeService;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = AttributeService.getAllNamedQueryName, query = "SELECT a FROM Attribute a"),
})
public class Attribute extends AbstractEntity {

    private String name;

    @ManyToOne
    private Relation relation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + getId() +
                ", name=" + name +
                ", relation=" + relation +
                '}';
    }
}
