package dbse.controller;

import dbse.model.Attribute;
import dbse.model.Relation;
import dbse.model.constraint.PrimaryKeyConstraint;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.Map;

@ViewScoped
@Named
public class AttributeController extends AbstractController<Attribute> {
    public void create(Relation relation) {
        new Attribute(relation);
    }

    //TODO simplify
    public void changeRelation(Attribute attribute) {
        //Q best way to get parameter?
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        String relationBlockId = requestParameterMap.get("relation_block_id");
        int relationIndex = Integer.parseInt(requestParameterMap.get("relation_index"));
        //Q связь через index?
        Relation newRelation = attribute.getRelation().getSchema().getRelations().get(relationIndex);
        attribute.getRelation().removeAttribute(attribute);
        newRelation.addAttribute(attribute);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(relationBlockId);
    }

    //TODO simplify
    public void changePrimaryKey(Attribute attribute) {
        //Q best way to get parameter?
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        String relationBlockId = requestParameterMap.get("relation_block_id");
        int relationIndex = Integer.parseInt(requestParameterMap.get("relation_index"));
        //Q связь через index?
        Relation newRelation = attribute.getRelation().getSchema().getRelations().get(relationIndex);
        attribute.getRelation().removeAttribute(attribute);
        newRelation.addAttribute(attribute);
        if (newRelation.getPrimaryKeyConstraint() != null) {
            newRelation.getPrimaryKeyConstraint().getAttributes().add(attribute);
        } else {
            newRelation.addPrimaryKeyConstraint(new PrimaryKeyConstraint(newRelation, attribute));
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(relationBlockId);
    }
}
