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
    EntityManager getEntityManager() {
        return em;
    }
}
