package testejb.persistence.util;

import persistence.EOrderEntity;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@TransactionManagement(value = TransactionManagementType.BEAN)
public class Utils {


    public void saveToDB(EOrderEntity eOrder) {

        new MessageSender().sendToQueue(eOrder);

    }
}
