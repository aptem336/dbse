package dbse.persist;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractPersistService<AbstractEntity> {

    private final Class<AbstractEntity> abstractEntityClass;

    AbstractPersistService(Class<AbstractEntity> abstractEntityClass, String getAllNamedQueryName) {
        this.abstractEntityClass = abstractEntityClass;
        this.getAllNamedQueryName = getAllNamedQueryName;
    }

    protected abstract EntityManager getEntityManager();

    public AbstractEntity persist(AbstractEntity abstractEntity) {
        getEntityManager().persist(abstractEntity);
        return abstractEntity;
    }

    public AbstractEntity merge(AbstractEntity abstractEntity) {
        return getEntityManager().merge(abstractEntity);
    }

    public AbstractEntity remove(AbstractEntity abstractEntity) {
        getEntityManager().remove(getEntityManager().merge(abstractEntity));
        return abstractEntity;
    }

    public List<AbstractEntity> getAll() {
        return getEntityManager().createNamedQuery(getAllNamedQueryName, abstractEntityClass).getResultList();
    }

    private final String getAllNamedQueryName;

    public AbstractEntity getById(long id) {
        return getEntityManager().find(abstractEntityClass, id);
    }
}
