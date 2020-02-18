package dbse.controller;

import dbse.model.AttributeEntity;
import dbse.persist.AbstractPersistService;
import dbse.persist.AttributePersistService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class AttributeController extends AbstractController<AttributeEntity> {

    @Override
    AttributeEntity getEntity() {
        return new AttributeEntity();
    }

    @Inject
    private AttributePersistService service;

    @Override
    AbstractPersistService<AttributeEntity> getService() {
        return service;
    }
}
