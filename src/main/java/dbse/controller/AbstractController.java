package dbse.controller;

import dbse.entity.AbstractEntity;
import dbse.service.AbstractService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AbstractController {

    private List<AbstractEntity> abstractEntityList;
    @Inject
    private AbstractService abstractService;

    public void add() {
        AbstractEntity abstractEntity = new AbstractEntity();
        abstractEntityList.add(abstractEntity);
        abstractService.add(abstractEntity);
    }

    public void remove(long id) {
        abstractEntityList = abstractEntityList.stream().filter(abstractEntity -> abstractEntity.getId() != id).collect(Collectors.toList());
        abstractService.remove(id);
    }

    public void save() {
        abstractEntityList.forEach(abstractService::save);
    }

    @PostConstruct
    private void postConstruct() {
        abstractEntityList = abstractService.getAll();
    }

    public List<AbstractEntity> getAbstractEntityList() {
        return abstractEntityList;
    }

    public void setAbstractEntityList(List<AbstractEntity> abstractEntityList) {
        this.abstractEntityList = abstractEntityList;
    }
}