package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class SchemaModel extends Model {
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "schema")
    private List<RelationModel> relations;

    public void addRelation(RelationModel relation) {
        relations.add(relation);
        relation.setSchema(this);
    }

    public void removeRelation(RelationModel relation) {
        relations.remove(relation);
        relation.setSchema(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RelationModel> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationModel> relationModels) {
        this.relations = relationModels;
    }
}
