package controller;

import model.RelationModel;
import model.SchemaModel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

@Named
public class SchemaController extends Controller<SchemaModel> {

    @Inject
    private EntityManager em;
    private List<SchemaModel> schemas;
    private SchemaModel schema;

    public void merge() {
    }

    public void merge(SchemaModel schema) {
    }

    @PostConstruct
    private void readSchemas() {
        schema = new SchemaModel();
        schema.addRelation(new RelationModel(100, 100));
        schema.addRelation(new RelationModel(200, 200));
    }

    public List<SchemaModel> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<SchemaModel> schemas) {
        this.schemas = schemas;
    }

    public SchemaModel getSchema() {
        return schema;
    }

    public void setSchema(SchemaModel schema) {
        this.schema = schema;
    }
}
