package dbse.service;

import dbse.entity.Attribute;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class AttributeService extends AbstractService<Attribute> {

    @Inject
    private EntityManager em;

    public AttributeService() {
        super(Attribute.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public String getAllNamedQueryName() {
        return getAllNamedQueryName;
    }

    public static final String getAllNamedQueryName = "getAllAttribute";
}
