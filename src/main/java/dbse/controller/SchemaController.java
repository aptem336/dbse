package dbse.controller;

import dbse.model.AbstractModel;
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
    private List<Schema> schemasList;
    @Inject
    private SchemaPersistService service;

    public void create() {
        Schema schema = new Schema();
        schemasList.add(schema);
        schema.setState(AbstractModel.AbstractEntityState.TRANSIENT);
    }

    public void commit() {
        new ArrayList<>(schemasList).stream()
                .filter(schema -> schema.getState() == AbstractModel.AbstractEntityState.REMOVED)
                .forEach(schema -> {
                    schemasList.remove(schema);
                    service.remove(schema);
                });
        schemasList.forEach(this::commit);
    }

    public void commit(Schema schema) {
        new ArrayList<>(schema.getRelations()).stream()
                .filter(relation -> relation.getState() == AbstractModel.AbstractEntityState.REMOVED)
                .forEach(schema::removeRelation);
        schema.getRelations().forEach(relation -> {
            new ArrayList<>(relation.getAttributes()).stream()
                    .filter(attribute -> attribute.getState() == AbstractModel.AbstractEntityState.REMOVED)
                    .forEach(relation::removeAttribute);
            relation.getAttributes().forEach(attribute -> {
                attribute.setState(AbstractModel.AbstractEntityState.PERSISTENT);
            });
            new ArrayList<>(relation.getUniqueConstraintConstraints()).stream()
                    .filter(attribute -> attribute.getState() == AbstractModel.AbstractEntityState.REMOVED)
                    .forEach(relation::removeUniqueConstraint);
            if (relation.getPrimaryKeyConstraint() != null) {
                if (relation.getPrimaryKeyConstraint().getState() == AbstractModel.AbstractEntityState.REMOVED) {
                    relation.setPrimaryKeyConstraint(null);
                }
            }
            relation.getUniqueConstraintConstraints().forEach(uniqueConstraint -> {
                uniqueConstraint.setState(AbstractModel.AbstractEntityState.PERSISTENT);
            });
            relation.setState(AbstractModel.AbstractEntityState.PERSISTENT);
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
        schema.setState(AbstractModel.AbstractEntityState.PERSISTENT);
    }

    @PostConstruct
    private void postConstruct() {
        schemasList = service.selectAll();
    }

    public List<Schema> getSchemasList() {
        return schemasList;
    }

    public void setSchemasList(List<Schema> schemasList) {
        this.schemasList = schemasList;
    }
}
