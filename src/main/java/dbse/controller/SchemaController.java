package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.model.Schema;
import dbse.persist.SchemaPersistService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

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
        setSchema(service.merge(schema));
    }

    @PostConstruct
    private void loadSchema() {
        setSchema(service.getById(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("schema")));
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

    public SchemaPersistService getService() {
        return service;
    }
}
