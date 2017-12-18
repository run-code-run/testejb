package testejb.bean.impl;

import testejb.MessageSender;
import testejb.bean.OrderBean;
import testejb.persistence.EOrderEntity;
import testejb.persistence.util.Utils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.util.List;


@Stateless
@Remote(OrderBean.class)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class OrderBeanImpl implements OrderBean {


    @Override
    public int addOrder(EOrderEntity eOrder) {

        new Utils().saveToDB(eOrder);

        try {
            new MessageSender().sendToQueue();
        } catch (JMSException  | NullPointerException | NamingException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<EOrderEntity> getOrders() {

        return new Utils().getRegisteredOrdersList();

    }


}
