package dbse.controller;

import dbse.entity.Attribute;
import dbse.entity.Relation;
import dbse.service.AbstractService;
import dbse.service.AttributeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class AttributeController extends AbstractController<Attribute> {

    @Inject
    private AttributeService service;

    AttributeController() {
        super(Attribute.class);
    }

    @Override
    AbstractService<Attribute> getService() {
        return service;
    }
    //TODO: или всё же через управление коллекциями?
    public void addForRelation(Relation relation) {
        Attribute attribute = new Attribute();
        attribute.setRelation(relation);
        //TODO: persist|merge?
        service.save(attribute);
    }
    public List<Attribute> getAllForRelation(Relation relation) {
        return service.getAllForRelation(relation);
    }
}
