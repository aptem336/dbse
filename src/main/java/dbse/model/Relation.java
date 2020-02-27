package dbse.model;

import dbse.persist.RelationPersistService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = RelationPersistService.allNamedQueryName, query = "SELECT r FROM Relation r")
})
public class Relation extends AbstractEntity {

    @Transient
    public void shift(int x, int y) {//Q
        this.x += x;
        this.y += y;
    }

    private String name;

    private int x, y;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "relation")
    private List<Attribute> attributes = new ArrayList<>();
    @ManyToOne
    private Schema schema;

    public Relation() {
    }

    public Relation(Schema schema, int x, int y) {
        setSchema(schema);
        setX(x);
        setY(y);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}