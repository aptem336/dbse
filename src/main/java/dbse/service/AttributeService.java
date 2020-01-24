package dbse.service;

import dbse.entity.Attribute;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class AttributeService extends AbstractService<Attribute> {

    public AttributeService() {
        super(Attribute.class, getAllNamedQueryName);
    }

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static final String getAllNamedQueryName = "getAllAttribute";
}
