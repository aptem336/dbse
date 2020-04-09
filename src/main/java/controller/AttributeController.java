package controller;

import model.AttributeModel;
import model.RelationModel;

import javax.inject.Named;

@Named
public class AttributeController extends Controller<AttributeModel> {
    public void create(RelationModel relation){
        relation.addContainedModel(new AttributeModel());
    }
}
