package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AttributeContainerModel extends Model
        implements ModelContainer<AttributeModel> {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "container")
    private final List<AttributeModel> attributes = new ArrayList<>();

    @Override
    public void addContainedModel(AttributeModel attributeModel) {
        getContainedModels().add(attributeModel);
        attributeModel.setContainer(this);
    }

    @Override
    public void removeContainedModel(AttributeModel attributeModel) {
        getContainedModels().remove(attributeModel);
        attributeModel.setContainer(null);
    }

    @Override
    public List<AttributeModel> getContainedModels() {
        return attributes;
    }
}
