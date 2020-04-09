package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AttributeModel extends Model
        implements ContainableModel<AttributeContainerModel> {
    @ManyToOne
    private AttributeContainerModel container;

    public AttributeContainerModel getContainer() {
        return container;
    }

    public void setContainer(AttributeContainerModel container) {
        this.container = container;
    }
}
