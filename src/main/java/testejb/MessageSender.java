package testejb;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageSender {





    //TODO Important!
    public  void sendToQueue() throws JMSException, NamingException {


        InitialContext initialContext = new InitialContext();
        //TODO fixed?
        initialContext.addToEnvironment(Context.SECURITY_PRINCIPAL, "guest");
        initialContext.addToEnvironment(Context.SECURITY_CREDENTIALS, "1234qwerty");
        ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("java:jboss/exported/jms/RemoteConnectionFactory");

        Queue queue = (Queue) initialContext.lookup("java:/jms/queue/txrequest");
        //CONNECTION == NULL!!!
        //TODO получить connection factory from JNDI
        Connection connection = connectionFactory.createConnection();

        try {

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            connection.start();
            Message msg = session.createMessage();
            msg.setJMSDestination(queue);
            msg.setStringProperty("body", "Hi there");

            producer.send(msg);
        } finally {
            connection.close();
        }
    }


}
