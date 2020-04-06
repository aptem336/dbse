package model;

import view.Positioned;
import view.draggable.DragSource;

import javax.persistence.*;

@Entity
public class RelationModel extends AttributeContainerModel
        implements DragSource, Positioned {

    private String name;
    @ManyToOne
    private SchemaModel schema;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "relation")
    private PrimaryKeyModel primaryKey;
    private int x, y;

    public RelationModel() {
    }

    public RelationModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SchemaModel getSchema() {
        return schema;
    }

    public void setSchema(SchemaModel schemaModel) {
        this.schema = schemaModel;
    }

    public PrimaryKeyModel getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKeyModel primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
