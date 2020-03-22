package dbse.model.constraint;

import dbse.model.Attribute;
import dbse.model.Relation;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class PrimaryKey extends Constraint<Relation> {
    @OneToMany
    private List<Attribute> attributes;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
