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
    public AttributeContainerModel getSelf() {
        return this;
    }

    @Override
    public List<AttributeModel> getContainedModels() {
        return attributes;
    }
}
