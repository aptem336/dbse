package dbse.model.constraint;

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
        getAttributes().add(attribute);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
