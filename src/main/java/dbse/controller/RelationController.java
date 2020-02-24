package dbse.controller;

import dbse.model.AbstractEntity;
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

    @Override
    RelationEntity getEntity() {
        return new RelationEntity();
    }

    @Inject
    private RelationPersistService service;

    @Override
    AbstractPersistService<RelationEntity> getService() {
        return service;
    }

    public void shift(RelationEntity relationEntity) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        relationEntity.shift(Integer.parseInt(requestParameterMap.get("x")), Integer.parseInt(requestParameterMap.get("y")));
        relationEntity.setState(AbstractEntity.AbstractEntityState.changed);
    }
}
