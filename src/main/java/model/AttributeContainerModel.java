package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AttributeContainerModel extends Model
        implements ModelContainer<AttributeContainerModel, AttributeModel> {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "container")
    private final List<AttributeModel> attributes = new ArrayList<>();

    @Override
    public void addContainedModel(AttributeModel attribute) {
        attributes.add(attribute);
        attribute.setContainer(this);
    }

    @Override
    public void removeContainedModel(AttributeModel attribute) {
        attributes.remove(attribute);
        attribute.setContainer(null);
    }

    @Override
    public List<AttributeModel> getContainedModels() {
        return attributes;
    }
}
