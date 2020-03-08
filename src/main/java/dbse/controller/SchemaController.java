package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Attribute;
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

    public void commit(Schema schema) {
        new ArrayList<>(schema.getRelations()).stream()
                .filter(relation -> relation.getState() == AbstractEntity.AbstractEntityState.REMOVED)
                .forEach(schema::removeRelation);
        schema.getRelations().forEach(relation -> {
            new ArrayList<>(relation.getAttributes()).stream()
                    .filter(attribute -> attribute.getState() == AbstractEntity.AbstractEntityState.REMOVED)
                    .forEach(relation::removeAttribute);
            relation.getAttributes().forEach(attribute -> {
                attribute.setState(AbstractEntity.AbstractEntityState.PERSISTENT);
            });
            relation.setState(AbstractEntity.AbstractEntityState.PERSISTENT);
        });
        switch (schema.getState()) {
            case TRANSIENT:
                getService().persist(schema);
                break;
            case PERSISTENT:
                schema = getService().merge(schema);
                break;
            case REMOVED:
            case DETACHED:
                break;
        }
        setSchema(schema);
    }

    //DEL vvv
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
    //DEL ^^^

    @Inject
    private SchemaPersistService service;

    @Override
    protected AbstractPersistService<Schema> getService() {
        return service;
    }
}
