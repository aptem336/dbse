package dbse.entity;

import dbse.service.RelationService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = RelationService.getAllNamedQueryName, query = "SELECT r FROM Relation r")
})
public class Relation extends AbstractEntity {

    private String name;
    private int x, y;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "relation")
    private List<Attribute> attributes = new ArrayList<>();

    public Relation() {
    }

    public Relation(int x, int y) {
        this.x = x;
        this.y = y;
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void removeAttribute(Attribute attribute) {
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
