package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.AttributeEntity;
import dbse.model.RelationEntity;
import dbse.persist.AbstractPersistService;
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
        relationEntity.setState(AbstractEntity.AbstractEntityState.added);//Q STATES PROCESSING?
    }
    
    public void shift(RelationEntity relationEntity) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        relationEntity.shift(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
        relationEntity.setState(AbstractEntity.AbstractEntityState.changed);//Q STATES PROCESSING?
    }

    public void create(RelationEntity relationEntity) {
        relationEntity.getAttributes().add(new AttributeEntity(relationEntity));
        relationEntity.setState(AbstractEntity.AbstractEntityState.changed);//Q STATES PROCESSING?
    }
}
