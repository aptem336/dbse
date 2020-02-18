package dbse;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataSourceDefinition(name = "java:global/jdbc/dbse_db",
        className = "org.sqlite.JDBC",
        url = "jdbc:sqlite:D:\\dbse_db.db"
)
public class DataSourceProducer {
    @Produces
    @PersistenceContext(unitName = "dbse_pu")
    private EntityManager em;
}
