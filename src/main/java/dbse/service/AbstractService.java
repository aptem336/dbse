package dbse.service;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractService<AbstractEntity> {

    private final Class<AbstractEntity> abstractEntityClass;

    AbstractService(Class<AbstractEntity> abstractEntityClass, String getAllNamedQueryName) {
        this.abstractEntityClass = abstractEntityClass;
        this.getAllNamedQueryName = getAllNamedQueryName;
    }

    protected abstract EntityManager getEntityManager();

    public AbstractEntity persist(AbstractEntity abstractEntity) {
        getEntityManager().persist(abstractEntity);
        return abstractEntity;
    }

    public AbstractEntity remove(AbstractEntity abstractEntity) {
        getEntityManager().remove(getEntityManager().merge(abstractEntity));
        return abstractEntity;
    }

    public AbstractEntity merge(AbstractEntity abstractEntity) {
        return getEntityManager().merge(abstractEntity);
    }

    public AbstractEntity getById(long id) {
        return getEntityManager().find(abstractEntityClass, id);
    }

    public List<AbstractEntity> getAll() {
        return getEntityManager().createNamedQuery(getAllNamedQueryName, abstractEntityClass).getResultList();
    }

    private final String getAllNamedQueryName;
}
