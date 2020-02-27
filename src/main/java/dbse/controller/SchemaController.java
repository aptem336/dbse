package dbse.controller;

import dbse.model.Schema;
import dbse.persist.AbstractPersistService;
import dbse.persist.SchemaPersistService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class SchemaController extends AbstractController<Schema> {

    private Schema schema = new Schema();

    public Schema getTestSchema() {
        return schema;
    }

    @Inject
    private SchemaPersistService service;

    @Override
    protected AbstractPersistService<Schema> getService() {
        return service;
    }
}
