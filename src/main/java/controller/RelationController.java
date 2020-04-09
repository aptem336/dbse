package controller;

import model.RelationModel;
import model.SchemaModel;

import javax.inject.Named;

@Named
public class RelationController extends Controller<RelationModel> {

    public void create(SchemaModel schema, int x, int y) {
        schema.addContainedModel(new RelationModel(x, y));
    }
}
