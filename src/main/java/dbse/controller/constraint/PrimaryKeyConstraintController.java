package dbse.controller.constraint;

import dbse.model.Attribute;
import dbse.model.Relation;
import dbse.model.constraint.PrimaryKeyConstraint;
import dbse.model.constraint.UniqueConstraint;

import javax.inject.Named;

@Named
public class PrimaryKeyConstraintController extends UniqueConstraintController {
    public void create(Relation relation, Attribute attribute) {
        new PrimaryKeyConstraint(relation, attribute);
    }

    @Override
    public void removeAttribute(UniqueConstraint uniqueConstraint, Attribute attribute) {
        super.removeAttribute(uniqueConstraint, attribute);
        if (uniqueConstraint.getAttributes().isEmpty()) {
            uniqueConstraint.getTarget().removePrimaryKeyConstraint();
        }
    }
}
