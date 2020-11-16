import javax.jms.*;
import javax.naming.*;

public class Example {

    public static void main(String[] args)
    {
        try {
            //Create a context (shared for consumer/producer)
            InitialContext initialContext = new InitialContext();

            //Lookup the location and other settings for factory and queue as defined in properties
            ConnectionFactory connectionFactory
                    = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");

            Queue queue = (Queue) initialContext.lookup("testqueue");

            JMSContext jmsContext = connectionFactory.createContext();
            JMSConsumer consumer = jmsContext.createConsumer(queue);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        System.out.println(message.getBody(String.class));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            //Producer
            TextMessage textMessage = jmsContext.createTextMessage("Hello");
            TextMessage textMessage2 = jmsContext.createTextMessage("World");

            jmsContext.createProducer().send(queue, textMessage);
            jmsContext.createProducer().send(queue, textMessage2);

            consumer.close();

        }catch (Exception e){
            System.out.println("Something went wrong :(  " + e.getMessage() );
        }
    }
}
