package testejb.bean.impl;

import testejb.bean.OrderBean;
import testejb.persistence.EOrderEntity;
import testejb.persistence.util.Utils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.List;


@Stateless
@Remote(OrderBean.class)
@TransactionManagement(value= TransactionManagementType.BEAN)
public class OrderBeanImpl implements OrderBean {


    @Override
    public int addOrder(EOrderEntity eOrder) {

        new Utils().saveToDB(eOrder);

        return 0;
    }

    @Override
     public List<EOrderEntity> getOrders() {

        return new Utils().getRegisteredOrdersList();

    }


}
