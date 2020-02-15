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

    @Inject
    private RelationController relationController;

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

    public void create(Relation relation) {
        Attribute attribute = new Attribute();
        attribute.setRelation(relation);
        getService().persist(attribute);

        relation.addAttribute(attribute);
        relationController.getService().merge(relation);
    }

    public void remove(Attribute attribute, Relation relation) {
        relation.removeAttribute(attribute);
        relationController.getService().merge(relation);
    }
}
