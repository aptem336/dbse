package dbse.entity;

import dbse.service.RelationService;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = RelationService.getAllNamedQueryName, query = "SELECT r FROM Relation r")
})
public class Relation extends AbstractEntity {

    private String name;
    private int x, y;

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

    @Override
    public String toString() {
        return "Relation{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
