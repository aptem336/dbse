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

    public void remove(Long id) {
        em.remove(em.merge(em.find(AbstractEntity.class, id)));
    }

    public void save(AbstractEntity entity) {
        em.merge(entity);
    }

    public List<AbstractEntity> getAll() {
        return em.createNamedQuery(getAll, AbstractEntity.class).getResultList();
    }
}
