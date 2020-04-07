package model;

import view.draggable.DragTarget;
import view.draggable.PositionedDragMethod;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Entity
public class SchemaModel extends Model
        implements DragTarget<RelationModel> {

    @Override
    public Consumer<RelationModel> getDragMethod() {
        return PositionedDragMethod::accept;
    }

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "schema")
    private List<RelationModel> relations = new ArrayList<>();

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
