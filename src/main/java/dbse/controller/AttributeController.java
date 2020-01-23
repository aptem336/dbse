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

    @Inject
    private AttributeService service;

    AttributeController() {
        super(Attribute.class);
    }

    @Override
    AbstractService<Attribute> getService() {
        return service;
    }

    public void addForRelation(Relation relation) {
        add(new Attribute(relation));
    }

    //Q: или из БД запросом?
    public List<Attribute> getAllForRelation(Relation relation) {
        return getAbstractEntityList().stream().filter(
                attribute -> relation.equals(attribute.getRelation())
        ).collect(Collectors.toList());
    }
}
