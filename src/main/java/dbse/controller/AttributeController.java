package dbse.controller;

import dbse.entity.Attribute;
import dbse.entity.Relation;
import dbse.service.AbstractService;
import dbse.service.AttributeService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
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

//    @Inject
//    private RelationController relationController;
//
//    //TODO свернуть, в relation controller?
//    public void create(Relation relation) {
//        Attribute attribute = new Attribute();
//        attribute.setRelation(relation);
//        relation.addAttribute(attribute);
//        add(attribute);
//        relationController.save(relation);
//    }
//
//    //TODO свернуть, в relation controller?
//    public void remove(Attribute attribute, Relation relation) {
//        relation.removeAttribute(attribute);
//        relationController.save(relation);
//    }
}
