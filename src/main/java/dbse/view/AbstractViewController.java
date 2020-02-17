package dbse.view;

import dbse.persist.AbstractPersistService;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractViewController<AbstractEntity extends dbse.entity.AbstractEntity> implements Converter<AbstractEntity>, Serializable {

    private List<AbstractEntity> abstractEntityList;

    abstract AbstractEntity getEntity();

    abstract AbstractPersistService<AbstractEntity> getService();

    public AbstractEntity add() {
        return add(getEntity());
    }

    public AbstractEntity add(AbstractEntity abstractEntity) {
        abstractEntityList.add(abstractEntity);
        return abstractEntity;
    }

    public AbstractEntity remove(AbstractEntity abstractEntity) {
        abstractEntityList.remove(abstractEntity);
        return abstractEntity;
    }

    public void saveAbstractEntityListToDataSource() {
        abstractEntityList.forEach(abstractEntity -> {
            if (abstractEntity.isDelete()) {
                getService().remove(abstractEntity);
            } else {
                getService().merge(abstractEntity);
            }
        });
    }

    @PostConstruct
    private void readEntityListFromDataSource() {
        abstractEntityList = getService().getAll();
    }

    public List<AbstractEntity> getAbstractEntityList() {
        return abstractEntityList;
    }

    public void setAbstractEntityList(List<AbstractEntity> abstractEntityList) {
        this.abstractEntityList = abstractEntityList;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, AbstractEntity abstractEntity) {
        return abstractEntity.getId() + "";
    }

    @Override
    public AbstractEntity getAsObject(FacesContext context, UIComponent component, String id) {
        return getService().getById(Long.parseLong(id));
    }
}