package dbse.model.constraint;

import dbse.model.Attribute;
import dbse.model.Relation;

import javax.persistence.Entity;

@Entity
public class PrimaryKeyConstraint extends UniqueConstraint {
    public PrimaryKeyConstraint() {
    }

    public PrimaryKeyConstraint(Relation relation, Attribute attribute) {
        super(relation, attribute);
        relation.setPrimaryKeyConstraint(this);
    }
}
