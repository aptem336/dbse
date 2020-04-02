import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
    @Produces
    @PersistenceContext(unitName = "dbse_pu")
    private EntityManager em;
}
