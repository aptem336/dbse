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

    //TODO: persist|merge?
    public void add(AbstractEntity abstractEntity) {
        getEntityManager().persist(abstractEntity);
    }

    public void remove(AbstractEntity abstractEntity) {
        getEntityManager().remove(getEntityManager().merge(abstractEntity));
    }

    public void save(AbstractEntity abstractEntity) {
        getEntityManager().merge(abstractEntity);
    }

    public AbstractEntity getById(long id) {
        return getEntityManager().find(abstractEntityClass, id);
    }

    public List<AbstractEntity> getAll() {
        return getEntityManager().createNamedQuery(getAllNamedQueryName, abstractEntityClass).getResultList();
    }

    private final String getAllNamedQueryName;
}
