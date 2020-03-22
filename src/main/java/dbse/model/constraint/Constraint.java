package dbse.model.constraint;

import dbse.model.AbstractEntity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class Constraint<T extends ConstraintTarget> extends AbstractEntity {
    @ManyToOne
    private T target;

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }
}
