package dbse.persist;

import dbse.model.AttributeEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class AttributePersistService extends AbstractPersistService<AttributeEntity> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String allNamedQueryName = "getAllAttribute";

    @Override
    protected String getAllNamedQueryName() {
        return allNamedQueryName;
    }

    @Override
    protected Class<AttributeEntity> getAbstractEntityClass() {
        return AttributeEntity.class;
    }
}
