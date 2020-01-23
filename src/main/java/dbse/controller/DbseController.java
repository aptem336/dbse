package dbse.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

//Q нужен?
@RequestScoped
@Named
public class DbseController {
    @Inject
    RelationController relationController;
    @Inject
    AttributeController attributeController;

    public void save() {
        relationController.getAbstractEntityList().forEach(System.out::println);
        attributeController.getAbstractEntityList().forEach(System.out::println);
        relationController.saveAbstractEntityList();
        attributeController.saveAbstractEntityList();
    }
}
