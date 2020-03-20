package dbse.model;

import javax.persistence.*;

@Entity
public class Attribute extends AbstractEntity {

    private String name;
    @ManyToOne
    private Relation relation;

    public Attribute() {
    }

    public Attribute(Relation relation) {
        relation.addAttribute(this);
        setState(AbstractEntity.AbstractEntityState.TRANSIENT);
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
