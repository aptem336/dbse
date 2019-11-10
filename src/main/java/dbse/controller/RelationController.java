package dbse.controller;

import dbse.entity.RelationEntity;
import dbse.service.RelationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class RelationController {

    private List<RelationEntity> all;
    @Inject
    private RelationService relationService;

    public void insert() {
        RelationEntity relationEntity = new RelationEntity();
        all.add(relationEntity);
        relationService.insert(relationEntity);
    }

    @PostConstruct
    private void postConstruct() {
        all = relationService.getAll();
    }

    public List<RelationEntity> getAll() {
        return all;
    }

    public void setAll(List<RelationEntity> all) {
        this.all = all;
    }
}