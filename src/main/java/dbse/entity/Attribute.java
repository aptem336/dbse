package dbse.entity;

import dbse.service.AttributeService;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = AttributeService.getAllNamedQueryName, query = "SELECT a FROM Attribute a")
public class Attribute extends AbstractEntity {
}
