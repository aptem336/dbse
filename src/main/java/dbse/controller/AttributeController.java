package dbse.controller;

import dbse.model.AbstractModel;
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
        Relation relation = attribute.getRelation();
        relation.removeAttribute(attribute);
        if (relation.getPrimaryKeyConstraint() != null) {
            relation.getPrimaryKeyConstraint().getAttributes().remove(attribute);
            if (relation.getPrimaryKeyConstraint().getAttributes().isEmpty()){
                relation.getPrimaryKeyConstraint().setState(AbstractModel.AbstractEntityState.REMOVED);
            } else {
                relation.getPrimaryKeyConstraint().setState(AbstractModel.AbstractEntityState.CHANGED);
            }
        }
        //Q связь через index?
        int relationIndex = Integer.parseInt(requestParameterMap.get("relation_index"));
        Relation newRelation = relation.getSchema().getRelations().get(relationIndex);
        newRelation.addAttribute(attribute);
        String relationBlockId = requestParameterMap.get("relation_block_id");
        String newRelationBlockId = requestParameterMap.get("new_relation_block_id");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(relationBlockId);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(newRelationBlockId);
    }

    //TODO simplify
    public void changePrimaryKey(Attribute attribute) {
        //Q best way to get parameter?
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        Relation relation = attribute.getRelation();
        relation.removeAttribute(attribute);
        if (relation.getPrimaryKeyConstraint() != null) {
            relation.getPrimaryKeyConstraint().getAttributes().remove(attribute);
            if (relation.getPrimaryKeyConstraint().getAttributes().isEmpty()){
                relation.getPrimaryKeyConstraint().setState(AbstractModel.AbstractEntityState.REMOVED);
            } else {
                relation.getPrimaryKeyConstraint().setState(AbstractModel.AbstractEntityState.CHANGED);
            }
        }
        //Q связь через index?
        int relationIndex = Integer.parseInt(requestParameterMap.get("relation_index"));
        Relation newRelation = relation.getSchema().getRelations().get(relationIndex);
        newRelation.addAttribute(attribute);
        if (newRelation.getPrimaryKeyConstraint() != null) {
            newRelation.getPrimaryKeyConstraint().getAttributes().add(attribute);
            newRelation.getPrimaryKeyConstraint().setState(AbstractModel.AbstractEntityState.CHANGED);
        } else {
            newRelation.addPrimaryKeyConstraint(new PrimaryKeyConstraint(newRelation, attribute));
        }
        String relationBlockId = requestParameterMap.get("relation_block_id");
        String newRelationBlockId = requestParameterMap.get("new_relation_block_id");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(relationBlockId);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(newRelationBlockId);
    }
}
