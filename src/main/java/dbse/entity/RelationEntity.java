package dbse.entity;

import dbse.persist.RelationPersistService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = RelationPersistService.getAllNamedQueryName, query = "SELECT r FROM RelationEntity r")
})
public class RelationEntity extends AbstractEntity {

    private String name;
    private int x, y;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "relation")
    private List<AttributeEntity> attributes = new ArrayList<>();

    public RelationEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<AttributeEntity> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeEntity> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(AttributeEntity attribute) {
        attributes.add(attribute);
    }

    public void removeAttribute(AttributeEntity attribute) {
        attributes.remove(attribute);
    }

    @Override
    public String toString() {
        return "Relation{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
