package dbse.model;

import dbse.persist.SchemaPersistService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = SchemaPersistService.ALL_NAMED_QUERY_NAME, query = "SELECT s FROM Schema s"),
})
public class Schema extends AbstractEntity {
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "schema")
    private List<Relation> relations = new ArrayList<>();

    public void addRelation(Relation relation) {
        relations.add(relation);
        relation.setSchema(this);
        if (getState() != AbstractEntity.AbstractEntityState.TRANSIENT) {
            setState(AbstractEntity.AbstractEntityState.CHANGED);
        }
    }

    public void removeRelation(Relation relation) {
        relations.remove(relation);
        relation.setSchema(null);
        if (getState() != AbstractEntity.AbstractEntityState.TRANSIENT) {
            setState(AbstractEntity.AbstractEntityState.CHANGED);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

}