package dbse.service;

import dbse.entity.Attribute;
import dbse.entity.Relation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AttributeService extends AbstractService<Attribute> {

    @Inject
    private EntityManager em;

    public AttributeService() {
        super(Attribute.class, getAllNamedQueryName);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Attribute> getAllForRelation(Relation relation) {
        TypedQuery<Attribute> allForRelation = em.createNamedQuery(getAllForRelationNamedQueryName, Attribute.class);
        allForRelation.setParameter("relation", relation);
        return allForRelation.getResultList();
    }

    public static final String getAllNamedQueryName = "getAllAttribute";
    public static final String getAllForRelationNamedQueryName = "getAllAttributeForRelation";
}
