package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Relation;
import dbse.model.Schema;
import dbse.persist.AbstractPersistService;
import dbse.persist.SchemaPersistService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class SchemaController extends AbstractController<Schema> {

    @Inject
    private RelationController relationController;

    @Override
    public void commit(Schema schema) {
        List<Relation> relations = new ArrayList<>(schema.getRelations());
        relations.stream()
                .filter(relation -> relation.getState() == AbstractEntity.AbstractEntityState.REMOVED)
                .forEach(schema::removeRelation);
        super.commit(schema);
        schema.getRelations().forEach(relation -> {
            relation.setState(AbstractEntity.AbstractEntityState.PERSISTENT);
            relationController.commit(relation);
        });
    }

    @PostConstruct
    private void readSchema() {
        setSchema(getService().selectAll().get(0));
    }

    private Schema schema;

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    @Inject
    private SchemaPersistService service;

    @Override
    protected AbstractPersistService<Schema> getService() {
        return service;
    }
}
