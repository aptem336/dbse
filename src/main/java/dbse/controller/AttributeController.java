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
    public void changeAttributeContainer(Attribute attribute) {
        //Q best way to get parameter?
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        Relation relation = attribute.getRelation();
        PrimaryKeyConstraint primaryKeyConstraint = relation.getPrimaryKeyConstraint();
        if (primaryKeyConstraint != null) {
            if (primaryKeyConstraint.getAttributes().contains(attribute)) {
                primaryKeyConstraint.removeAttribute(attribute);
            } else {
                relation.removeAttribute(attribute);
            }
        } else {
            relation.removeAttribute(attribute);
        }
        //Q связь через index?
        int relationIndex = Integer.parseInt(requestParameterMap.get("container_index"));
        Relation newRelation = relation.getSchema().getRelations().get(relationIndex);
        //Q другой способ разделения?
        boolean isPrimaryKey = Boolean.parseBoolean(requestParameterMap.get("is_primary_key"));
        if (isPrimaryKey) {
            PrimaryKeyConstraint newRelationPrimaryKeyConstraint = newRelation.getPrimaryKeyConstraint();
            if (newRelationPrimaryKeyConstraint != null) {
                newRelationPrimaryKeyConstraint.addAttribute(attribute);
            } else {
                new PrimaryKeyConstraint(newRelation, attribute);
            }
        } else {
            newRelation.addAttribute(attribute);
        }

        String containerBlockId = requestParameterMap.get("container_block_id");
        String newContainerBlockId = requestParameterMap.get("new_container_block_id");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(containerBlockId);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(newContainerBlockId);
    }
}
