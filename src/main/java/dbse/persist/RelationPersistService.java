package dbse.persist;

import dbse.model.RelationEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class RelationPersistService extends AbstractPersistService<RelationEntity> {

    @Inject
    private EntityManager em;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String allNamedQueryName = "getAllRelation";

    @Override
    protected String getAllNamedQueryName() {
        return allNamedQueryName;
    }

    @Override
    protected Class<RelationEntity> getAbstractEntityClass() {
        return RelationEntity.class;
    }
}
