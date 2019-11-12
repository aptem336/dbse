package dbse.service;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractService<AbstractEntity> {

    private Class<AbstractEntity> abstractEntityClass;

    AbstractService(Class<AbstractEntity> abstractEntityClass){
        this.abstractEntityClass = abstractEntityClass;
    }

    abstract EntityManager getEntityManager();

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
        return getEntityManager().createNamedQuery(getAll, abstractEntityClass).getResultList();
    }

    public final static String getAll = "getAll";
}
