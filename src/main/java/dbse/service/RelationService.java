package dbse.service;

import dbse.entity.RelationEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class RelationService {

    public final static String getAll = "getAll";
    @Inject
    private EntityManager em;

    public void insert(RelationEntity relationEntity) {
        em.persist(relationEntity);
    }

    public List<RelationEntity> getAll() {
        return em.createNamedQuery(getAll, RelationEntity.class).getResultList();
    }
}
