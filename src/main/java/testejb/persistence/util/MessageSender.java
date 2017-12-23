package testejb.persistence.util;

import persistence.EOrderEntity;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MessageSender {


    //TODO прописать ебучего юзера в сессию

    public void sendToQueue(EOrderEntity entity) {

        try {

            InitialContext initialContext = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("java:jboss/exported/jms/RemoteConnectionFactory");
            Queue queue = (Queue) initialContext.lookup("java:/jms/queue/txrequest");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
//            ObjectMessage message = session.createObjectMessage(entity);
            /*ObjectMessage message = session.createObjectMessage();
            message.setObject(eOrder);*/

            connection.start();

//            TextMessage message = session.createTextMessage("Hello!");
            ObjectMessage om = session.createObjectMessage(entity);


            producer.send(om);
            connection.close();


        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
