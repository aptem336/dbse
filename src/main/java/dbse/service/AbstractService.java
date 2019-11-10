package dbse.service;

import dbse.entity.AbstractEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class AbstractService {

    public final static String getAll = "getAll";
    @Inject
    private EntityManager em;

    public void add(AbstractEntity entity) {
        em.persist(entity);
    }

    public void remove(AbstractEntity abstractEntity) {
        em.remove(em.merge(abstractEntity));
    }

    public void save(AbstractEntity entity) {
        em.merge(entity);
    }

    public AbstractEntity getById(long id) {
        return em.find(AbstractEntity.class, id);
    }

    public List<AbstractEntity> getAll() {
        return em.createNamedQuery(getAll, AbstractEntity.class).getResultList();
    }
}
