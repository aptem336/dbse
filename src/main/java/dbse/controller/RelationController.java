package dbse.controller;

import dbse.entity.Relation;
import dbse.service.AbstractService;
import dbse.service.RelationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class RelationController extends AbstractController<Relation> {

    @Override
    Relation getEntity() {
        return new Relation();
    }

    @Inject
    private RelationService service;

    @Override
    AbstractService<Relation> getService() {
        return service;
    }
}
