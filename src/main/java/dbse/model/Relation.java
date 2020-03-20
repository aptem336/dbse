package dbse.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Relation extends AbstractEntity {
    private String name;
    private int x, y;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "relation")
    private List<Attribute> attributes = new ArrayList<>();
    @ManyToOne
    private Schema schema;
    public Relation() {
    }
    public Relation(Schema schema, int x, int y) {
        setX(x);
        setY(y);
        schema.addRelation(this);
        setState(AbstractEntity.AbstractEntityState.TRANSIENT);
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        attribute.setRelation(this);
        if (getState() != AbstractEntity.AbstractEntityState.TRANSIENT) {
            setState(AbstractEntity.AbstractEntityState.CHANGED);
        }
    }

    public void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
        attribute.setRelation(null);
        if (getState() != AbstractEntity.AbstractEntityState.TRANSIENT) {
            setState(AbstractEntity.AbstractEntityState.CHANGED);
        }
    }

    @Transient
    public void shift(int x, int y) {
        this.x += x;
        this.y += y;
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
