package dbse.model.constraint;

import dbse.model.AbstractModel;
import dbse.model.Attribute;
import dbse.model.Relation;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UniqueConstraint extends Constraint<Relation> {
    @OneToMany
    private List<Attribute> attributes = new ArrayList<>();

    public UniqueConstraint() {
    }

    public UniqueConstraint(Relation relation, Attribute attribute) {
        relation.addUniqueConstraint(this);
        attributes.add(attribute);
        getTarget().getAttributes().add(attribute);
        attribute.setRelation(getTarget());
        setState(AbstractEntityState.TRANSIENT);
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        getTarget().getAttributes().add(attribute);
        attribute.setRelation(getTarget());
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
        }
    }

    public void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
        if (attributes.isEmpty()) {
            getTarget().setPrimaryKeyConstraint(null);
        }
        getTarget().removeAttribute(attribute);
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
        }
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
