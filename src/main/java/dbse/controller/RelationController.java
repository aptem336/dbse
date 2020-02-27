package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Relation;
import dbse.model.Schema;
import dbse.persist.AbstractPersistService;
import dbse.persist.RelationPersistService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@ViewScoped
@Named
public class RelationController extends AbstractController<Relation> {

    public void create(Schema schema) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        schema.getRelations().add(new Relation(schema, Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y"))));
        schema.setState(AbstractEntity.AbstractEntityState.changed);
    }

    public void shift(Relation relation) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        relation.shift(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
        relation.setState(AbstractEntity.AbstractEntityState.changed);
    }

    @Inject
    private RelationPersistService service;

    @Override
    protected AbstractPersistService<Relation> getService() {
        return service;
    }
}
