package dbse.persist;

import dbse.model.Attribute;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class AttributePersistService extends AbstractPersistService<Attribute> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String allNamedQueryName = "getAllAttributes";

    @Override
    protected String getAllNamedQueryName() {
        return allNamedQueryName;
    }

    @Override
    protected Class<Attribute> getAbstractEntityClass() {
        return Attribute.class;
    }
}
