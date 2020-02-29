package dbse.persist;

import dbse.model.AbstractEntity;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractPersistService<T extends AbstractEntity> {

    public void persist(T t) {
        getEntityManager().persist(t);
    }

    public void detach(T t) {
        getEntityManager().detach(t);
    }

    public T merge(T t) {
        return getEntityManager().merge(t);
    }

    public void remove(T t) {
        getEntityManager().remove(getEntityManager().merge(t));
    }

    protected abstract String getAllNamedQueryName();

    protected abstract Class<T> getAbstractEntityClass();

    public List<T> selectAll() {
        return getEntityManager().createNamedQuery(getAllNamedQueryName(), getAbstractEntityClass()).getResultList();
    }

    public T getById(String id) {
        return getEntityManager().find(getAbstractEntityClass(), id);
    }

    protected abstract EntityManager getEntityManager();
}
