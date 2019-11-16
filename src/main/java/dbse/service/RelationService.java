package dbse.service;

import dbse.entity.Relation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class RelationService extends AbstractService<Relation> {

    @Inject
    private EntityManager em;

    public RelationService() {
        super(Relation.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public String getAllNamedQueryName() {
        return getAllNamedQueryName;
    }

    public static final String getAllNamedQueryName = "getAllRelation";
}
