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

            //Producer
            TextMessage textMessage = jmsContext.createTextMessage("Hello world");
            jmsContext.createProducer().send(queue, textMessage);


            //Consumer
            TextMessage message = (TextMessage) jmsContext.createConsumer(queue).receive();
            System.out.println(message.getText());

        }catch (Exception e){
            System.out.println("Something went wrong :(  " + e.getMessage() );
        }

    }
}