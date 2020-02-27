package dbse.persist;

import dbse.model.Schema;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class SchemaPersistService extends AbstractPersistService<Schema> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String allNamedQueryName = "getAllSchemas";

    @Override
    protected String getAllNamedQueryName() {
        return allNamedQueryName;
    }

    @Override
    protected Class<Schema> getAbstractEntityClass() {
        return Schema.class;
    }
}
