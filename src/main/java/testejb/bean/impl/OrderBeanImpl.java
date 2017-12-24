package testejb.bean.impl;

import testejb.bean.OrderBean;
import entities.EOrderEntity;
import testejb.tools.MessageSender;


import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
@Remote(OrderBean.class)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class OrderBeanImpl implements OrderBean {


    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public int addOrder(EOrderEntity eOrder) {

        new MessageSender().sendToQueue(eOrder);

        return 0;
    }

    @Override
    public List<EOrderEntity> getOrders() {


        List<EOrderEntity> orders = null;

        try {

            orders = entityManager.createQuery(
                    "from EOrderEntity",
                    EOrderEntity.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;

    }
}
