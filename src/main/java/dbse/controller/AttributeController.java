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

    @Inject
    private AttributeService service;

    @Override
    AbstractService<Attribute> getService() {
        return service;
    }

    public Attribute create(Relation relation) {
        return add(new Attribute(relation));
    }

    public List<Attribute> getAllForRelation(Relation relation) {
        return getAbstractEntityList().stream().filter(
                attribute -> relation.equals(attribute.getRelation())
        ).collect(Collectors.toList());
    }
}
