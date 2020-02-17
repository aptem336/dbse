package dbse.view;

import dbse.entity.AttributeEntity;
import dbse.persist.AbstractPersistService;
import dbse.persist.AttributePersistService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class AttributeViewController extends AbstractViewController<AttributeEntity> {

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
