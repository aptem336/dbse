package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Attribute;
import dbse.model.Relation;
import dbse.model.Schema;
import dbse.persist.AbstractPersistService;
import dbse.persist.RelationPersistService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class RelationController extends AbstractController<Relation> {

    @Inject
    private AttributeController attributeController;

    @Override
    public void commit(Relation relation) {
        List<Attribute> attributes = new ArrayList<>(relation.getAttributes());
        attributes.stream()
                .filter(attribute -> attribute.getState() == AbstractEntity.AbstractEntityState.REMOVED)
                .forEach(relation::removeAttribute);
        super.commit(relation);
        relation.getAttributes().forEach(attribute -> {
            attribute.setState(AbstractEntity.AbstractEntityState.PERSISTENT);
            attributeController.commit(attribute);
        });
    }

    public void create(Schema schema) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Relation relation = new Relation(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
        schema.addRelation(relation);
        relation.setState(AbstractEntity.AbstractEntityState.TRANSIENT);
    }

    public void shift(Relation relation) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        relation.shift(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
    }

    @Inject
    private RelationPersistService service;

    @Override
    protected AbstractPersistService<Relation> getService() {
        return service;
    }
}
