package dbse.controller;

import dbse.entity.Attribute;
import dbse.entity.Relation;
import dbse.service.AbstractService;
import dbse.service.AttributeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class AttributeController extends AbstractController<Attribute> {

    @Override
    Attribute getEntity() {
        return new Attribute();
    }

    Attribute getEntity(Relation relation) {
        Attribute attribute = new Attribute();
        attribute.setRelation(relation);
        return attribute;
    }

    @Inject
    private AttributeService service;

    @Override
    AbstractService<Attribute> getService() {
        return service;
    }

    public void addForRelation(Relation relation) {
        add(getEntity(relation));
    }

    public List<Attribute> getAllForRelation(Relation relation) {
        return getAbstractEntityList().stream().filter(
                attribute -> relation.equals(attribute.getRelation())
        ).collect(Collectors.toList());
    }
}
