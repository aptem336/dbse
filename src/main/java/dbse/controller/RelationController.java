package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.AttributeEntity;
import dbse.model.RelationEntity;
import dbse.persist.AbstractPersistService;
import dbse.persist.AttributePersistService;
import dbse.persist.RelationPersistService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@ViewScoped
@Named
public class RelationController extends AbstractController<RelationEntity> {

    @Inject
    private RelationPersistService service;

    @Override
    AbstractPersistService<RelationEntity> getService() {
        return service;
    }

    public void create() {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        RelationEntity relationEntity = new RelationEntity(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
        add(relationEntity);
        relationEntity.setState(AbstractEntity.AbstractEntityState.added);
    }

    public void shift(RelationEntity relationEntity) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        relationEntity.shift(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
        relationEntity.setState(AbstractEntity.AbstractEntityState.changed);
    }

    public void create(RelationEntity relationEntity) {
        AttributeEntity attributeEntity = new AttributeEntity(relationEntity);
        relationEntity.getAttributes().add(attributeEntity);
        relationEntity.setState(AbstractEntity.AbstractEntityState.changed);
        attributeEntity.setState(AbstractEntity.AbstractEntityState.added);
    }
}
