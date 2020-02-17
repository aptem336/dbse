package dbse.persist;

import dbse.entity.RelationEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class RelationPersistService extends AbstractPersistService<RelationEntity> {

    public RelationPersistService() {
        super(RelationEntity.class, getAllNamedQueryName);
    }

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String getAllNamedQueryName = "getAllRelation";
}
