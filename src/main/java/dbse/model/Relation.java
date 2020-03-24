package dbse.model;

import dbse.model.constraint.ConstraintTarget;
import dbse.model.constraint.PrimaryKeyConstraint;
import dbse.model.constraint.UniqueConstraint;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Relation extends AbstractModel implements AttributesContainer, ConstraintTarget {
    private String name;
    private int x, y;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "relation")
    private List<Attribute> attributes = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "target")
    private PrimaryKeyConstraint primaryKeyConstraint;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "target")
    private List<UniqueConstraint> uniqueConstraintConstraints = new ArrayList<>();
    @ManyToOne
    private Schema schema;

    public Relation() {
    }

    public Relation(Schema schema, int x, int y) {
        setX(x);
        setY(y);
        schema.addRelation(this);
        setState(AbstractModel.AbstractEntityState.TRANSIENT);
    }

    public List<Attribute> getNotPrimaryKeyAttributes() {
        if (primaryKeyConstraint != null) {
            return attributes.stream()
                    .filter(attribute -> !primaryKeyConstraint.getAttributes().contains(attribute))
                    .collect(Collectors.toList());
        }
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        attribute.setRelation(this);
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
        }
    }

    public void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
        attribute.setRelation(null);
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
        }
    }

    public void addUniqueConstraint(UniqueConstraint uniqueConstraint) {
        uniqueConstraintConstraints.add(uniqueConstraint);
        uniqueConstraint.setTarget(this);
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
        }
    }

    public void removeUniqueConstraint(UniqueConstraint uniqueConstraint) {
        uniqueConstraintConstraints.remove(uniqueConstraint);
        uniqueConstraint.setTarget(null);
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
        }
    }

    @Transient
    public void shift(int x, int y) {
        this.x += x;
        this.y += y;
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
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

    public PrimaryKeyConstraint getPrimaryKeyConstraint() {
        return primaryKeyConstraint;
    }

    public void setPrimaryKeyConstraint(PrimaryKeyConstraint primaryKeyConstraint) {
        this.primaryKeyConstraint = primaryKeyConstraint;
    }

    public List<UniqueConstraint> getUniqueConstraintConstraints() {
        return uniqueConstraintConstraints;
    }

    public void setUniqueConstraintConstraints(List<UniqueConstraint> uniqueConstraintConstraints) {
        this.uniqueConstraintConstraints = uniqueConstraintConstraints;
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
