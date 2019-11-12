package dbse.entity;

import javax.persistence.Entity;

@Entity
public class Relation extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
