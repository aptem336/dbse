package dbse.entity;

import dbse.service.AttributeService;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = AttributeService.getAllNamedQueryName, query = "SELECT a FROM Attribute a"),
//        @NamedQuery(name = AttributeService.getAllForRelationNamedQueryName, query = "SELECT a FROM Attribute a WHERE a.relation = :relation")
})
public class Attribute extends AbstractEntity {

    private String name;

    @ManyToOne
    private Relation relation;

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
