package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Attribute;
import dbse.model.Relation;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.Map;

@ViewScoped
@Named
public class AttributeController extends AbstractController<Attribute> {

    public void create(Relation relation) {
        Attribute attribute = new Attribute();
        relation.addAttribute(attribute);
        attribute.setState(AbstractEntity.AbstractEntityState.TRANSIENT);
    }

    public void changeRelation(Attribute attribute) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //Q связь через index
        Relation newRelation = attribute.getRelation().getSchema().getRelations().get(Integer.parseInt(requestParameterMap.get("relation_index")));
        attribute.getRelation().removeAttribute(attribute);
        newRelation.addAttribute(attribute);
    }
}
