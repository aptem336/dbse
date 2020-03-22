package dbse.controller.constraint;

import dbse.model.Attribute;
import dbse.model.Relation;
import dbse.model.constraint.UniqueConstraint;

import javax.inject.Named;

@Named
public class UniqueConstraintController {
    public void create(Relation relation, Attribute attribute) {
        new UniqueConstraint(relation, attribute);
    }

    public void addAttribute(UniqueConstraint uniqueConstraint, Attribute attribute) {
        uniqueConstraint.getAttributes().add(attribute);
    }

    public void removeAttribute(UniqueConstraint uniqueConstraint, Attribute attribute) {
        uniqueConstraint.getAttributes().remove(attribute);
        if (uniqueConstraint.getAttributes().isEmpty()) {
            uniqueConstraint.getTarget().removeUniqueConstraint(uniqueConstraint);
        }
    }
}
