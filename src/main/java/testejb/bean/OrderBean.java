package testejb.bean;


import persistence.EOrderEntity;

import javax.ejb.Remote;
import java.rmi.RemoteException;
import java.util.List;

@Remote(OrderBean.class)
public interface OrderBean {


    int addOrder(EOrderEntity eOrder) throws RemoteException;

    List<EOrderEntity> getOrders();
}
