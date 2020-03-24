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
        attributes.add(attribute);
        relation.addAttribute(attribute);
        relation.addUniqueConstraint(this);
        setState(AbstractEntityState.TRANSIENT);
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        getTarget().addAttribute(attribute);
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
        }
    }

    public void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
        getTarget().removeAttribute(attribute);
        if (getState() != AbstractModel.AbstractEntityState.TRANSIENT) {
            setState(AbstractModel.AbstractEntityState.CHANGED);
        }
        if (attributes.isEmpty()) {
            setState(AbstractEntityState.REMOVED);
        }
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
