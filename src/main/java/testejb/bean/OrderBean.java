package testejb.bean;


import testejb.EOrder;

import javax.ejb.Remote;
import java.rmi.RemoteException;

@Remote(OrderBean.class)
public interface OrderBean {

    String getMessage();

    int addOrder(EOrder eOrder) throws RemoteException;
}
