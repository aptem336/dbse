package dbse.controller;

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
        //Q best way to get parameter?
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        create(schema, Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
    }

    public void create(Schema schema, int x, int y) {
        new Relation(schema, x, y);
    }

    public void shift(Relation relation) {
        //Q best way to get parameter?
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        shift(relation, Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
    }

    public void shift(Relation relation, int x, int y) {
        relation.shift(x, y);
    }
}
