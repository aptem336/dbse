package dbse.persist;

import dbse.model.Schema;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class SchemaPersistService extends AbstractPersistService<Schema> {

    public static final String ALL_NAMED_QUERY_NAME = "getAllSchemas";
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected String getAllNamedQueryName() {
        return ALL_NAMED_QUERY_NAME;
    }

    @Override
    protected Class<Schema> getAbstractEntityClass() {
        return Schema.class;
    }
}
