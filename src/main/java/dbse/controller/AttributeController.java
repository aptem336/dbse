package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Attribute;
import dbse.model.Relation;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class AttributeController extends AbstractController<Attribute> {

    public void create(Relation relation) {
        Attribute attribute = new Attribute();
        relation.addAttribute(attribute);
        attribute.setState(AbstractEntity.AbstractEntityState.TRANSIENT);
    }
}
