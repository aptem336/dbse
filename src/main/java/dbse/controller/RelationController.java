package dbse.controller;

import dbse.entity.Relation;
import dbse.service.AbstractService;
import dbse.service.RelationService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
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

//    public Relation create(int x, int y) {
//        return add(new Relation(x, y));
//    }
}
