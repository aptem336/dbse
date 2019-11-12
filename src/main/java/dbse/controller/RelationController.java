package dbse.controller;

import dbse.entity.AbstractEntity;
import dbse.entity.Relation;
import dbse.service.AbstractService;
import dbse.service.RelationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class RelationController extends AbstractController<AbstractEntity> {

    @Inject
    private RelationService service;

    @Override
    AbstractService getService() {
        return service;
    }

    @Override
    AbstractEntity getEntity() {
        return new Relation();
    }
}
