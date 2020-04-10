package model;

import view.drag.DragTarget;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SchemaModel extends Model
        implements DragTarget, ModelContainer<SchemaModel, RelationModel> {
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "schema")
    private final List<RelationModel> relations = new ArrayList<>();

    @Override
    public SchemaModel getSelf() {
        return this;
    }

    @Override
    public List<RelationModel> getContainedModels() {
        return relations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
