package testejb.persistence.util;

import testejb.persistence.EOrderEntity;

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
            ObjectMessage message = session.createObjectMessage(entity);
            producer.send(message);
            connection.close();

        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
