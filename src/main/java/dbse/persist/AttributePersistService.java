package dbse.persist;

import dbse.entity.AttributeEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class AttributePersistService extends AbstractPersistService<AttributeEntity> {

    public AttributePersistService() {
        super(AttributeEntity.class, getAllNamedQueryName);
    }

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String getAllNamedQueryName = "getAllAttribute";
}
