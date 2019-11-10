package dbse.entity;

import dbse.service.RelationService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = RelationService.getAll, query = "SELECT r FROM RelationEntity r")
public class RelationEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public RelationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
