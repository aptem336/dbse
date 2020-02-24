package dbse.model;

import dbse.persist.AttributePersistService;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = AttributePersistService.allNamedQueryName, query = "SELECT a FROM AttributeEntity a"),
})
public class AttributeEntity extends AbstractEntity {

    private String name;
    @ManyToOne
    private RelationEntity relation;

    public AttributeEntity() {
    }

    public AttributeEntity(RelationEntity relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RelationEntity getRelation() {
        return relation;
    }

    public void setRelation(RelationEntity relation) {
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
