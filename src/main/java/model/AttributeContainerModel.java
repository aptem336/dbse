package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AttributeContainerModel extends Model {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "container")
    private List<AttributeModel> attributes = new ArrayList<>();

    public void addAttribute(AttributeModel attribute){
        attributes.add(attribute);
        attribute.setContainer(this);
    }

    public void removeAttribute(AttributeModel attribute){
        attributes.remove(attribute);
        attribute.setContainer(null);
    }

    public List<AttributeModel> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeModel> attributeModels) {
        this.attributes = attributeModels;
    }
}
