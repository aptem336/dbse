package model;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class Model {
    @Id
    @GeneratedValue
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
