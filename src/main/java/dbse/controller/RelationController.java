package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Relation;
import dbse.model.Schema;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.Map;

@ViewScoped
@Named
public class RelationController extends AbstractController<Relation> {

    public void create(Schema schema) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Relation relation = new Relation(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
        schema.addRelation(relation);
        relation.setState(AbstractEntity.AbstractEntityState.TRANSIENT);
    }

    public void shift(Relation relation) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        relation.shift(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
        if (relation.getState() != AbstractEntity.AbstractEntityState.TRANSIENT) {
            relation.setState(AbstractEntity.AbstractEntityState.CHANGED);
        }
    }
}
