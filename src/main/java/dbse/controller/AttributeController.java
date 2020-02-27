package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Attribute;
import dbse.model.Relation;
import dbse.persist.AbstractPersistService;
import dbse.persist.AttributePersistService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class AttributeController extends AbstractController<Attribute> {

    public void create(Relation relation) {
        relation.getAttributes().add(new Attribute(relation));
        relation.setState(AbstractEntity.AbstractEntityState.changed);
    }

    @Inject
    private AttributePersistService service;

    @Override
    protected AbstractPersistService<Attribute> getService() {
        return service;
    }
}
