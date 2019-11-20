package dbse.entity;

import dbse.service.RelationService;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = RelationService.getAllNamedQueryName, query = "SELECT r FROM Relation r")
public class Relation extends AbstractEntity {

    public void addAttribute() {
        Attribute attribute = new Attribute();
        attribute.setRelation(this);
        attributeList.add(attribute);
    }

    private String name;
    @OneToMany(mappedBy = "relation", cascade = CascadeType.ALL)
    private List<Attribute> attributeList;
    private int x, y;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
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
}
