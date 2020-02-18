package dbse.controller;

import dbse.model.RelationEntity;
import dbse.persist.AbstractPersistService;
import dbse.persist.RelationPersistService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
}
