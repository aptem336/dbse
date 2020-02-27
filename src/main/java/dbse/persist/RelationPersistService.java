package dbse.persist;

import dbse.model.Relation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class RelationPersistService extends AbstractPersistService<Relation> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String allNamedQueryName = "getAllRelations";

    @Override
    protected String getAllNamedQueryName() {
        return allNamedQueryName;
    }

    @Override
    protected Class<Relation> getAbstractEntityClass() {
        return Relation.class;
    }
}
