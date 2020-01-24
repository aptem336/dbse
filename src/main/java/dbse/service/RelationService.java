package dbse.service;

import dbse.entity.Relation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class RelationService extends AbstractService<Relation> {

    public RelationService() {
        super(Relation.class, getAllNamedQueryName);
    }

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String getAllNamedQueryName = "getAllRelation";
}
