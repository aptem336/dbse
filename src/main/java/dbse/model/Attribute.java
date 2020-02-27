package dbse.model;

import dbse.persist.AttributePersistService;

import javax.persistence.*;

@Entity


@NamedQueries({
        @NamedQuery(name = AttributePersistService.allNamedQueryName, query = "SELECT a FROM Attribute a"),
})
public class Attribute extends AbstractEntity {

    private String name;
    @ManyToOne
    private Relation relation;

    public Attribute() {
    }

    public Attribute(Relation relation) {
        this.relation = relation;
    }

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
                ", name=" + name +
                ", relation=" + relation +
                '}';
    }
}