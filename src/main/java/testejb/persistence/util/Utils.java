package testejb.persistence.util;

import persistence.EOrderEntity;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;


@TransactionManagement(value = TransactionManagementType.BEAN)
public class Utils {


    @PersistenceContext(unitName = "myapp")
    private EntityManagerFactory entityManagerFactory;


    protected void setUp() throws Exception {

        entityManagerFactory = Persistence.createEntityManagerFactory("myapp");

    }


    public List<EOrderEntity> getRegisteredOrdersList() {

        List<EOrderEntity> orders = null;

        try {

            setUp();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            orders = entityManager.createQuery(
                    "from EOrderEntity",
                    EOrderEntity.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }


    public void saveToDB(EOrderEntity eOrder) {

        new MessageSender().sendToQueue(eOrder);

    }
}
