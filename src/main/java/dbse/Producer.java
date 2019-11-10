package dbse;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Producer {
    @Produces
    @PersistenceContext(unitName = "dbse_pu")
    private EntityManager em;
}
