package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Schema;
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

    public void create() {
        Schema schema = new Schema();
        schemasList.add(schema);
        schema.setState(AbstractEntity.AbstractEntityState.TRANSIENT);
    }

    public void commit() {
        new ArrayList<>(schemasList).stream()
                .filter(schema -> schema.getState() == AbstractEntity.AbstractEntityState.REMOVED)
                .forEach(schema -> {
                    schemasList.remove(schema);
                    service.remove(schema);
                });
        schemasList.forEach(this::commit);
    }

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
                service.persist(schema);
                break;
            case PERSISTENT:
            case CHANGED:
                service.merge(schema);
                break;
        }
    }

    @PostConstruct
    private void postConstruct() {
        schemasList = service.selectAll();
    }

    private List<Schema> schemasList;

    public List<Schema> getSchemasList() {
        return schemasList;
    }

    public void setSchemasList(List<Schema> schemasList) {
        this.schemasList = schemasList;
    }

    @Inject
    private SchemaPersistService service;
}
