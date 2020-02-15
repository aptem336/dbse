package dbse.controller;

import dbse.entity.Attribute;
import dbse.entity.Relation;
import dbse.service.AbstractService;
import dbse.service.AttributeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
}
