package dbse.model.constraint;

import dbse.model.Attribute;
import dbse.model.Relation;

import javax.persistence.Entity;

@Entity
public class PrimaryKeyConstraint extends UniqueConstraint {
    public PrimaryKeyConstraint() {
    }

    public PrimaryKeyConstraint(Relation relation, Attribute attribute) {
        relation.addUniqueConstraint(this);
        relation.addPrimaryKeyConstraint(this);
        getAttributes().add(attribute);
        setState(AbstractEntityState.TRANSIENT);
    }
}
