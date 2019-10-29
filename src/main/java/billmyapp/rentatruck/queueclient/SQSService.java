package billmyapp.rentatruck.queueclient;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.amazon.sqs.javamessaging.SQSConnection;
import billmyapp.rentatruck.queueclient.configuration.SQSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQSService {
	
	@Autowired
	private SQSConfig sqsConfig;
	
	public void sendMessage(String txtMsg)
	{
		SQSConnection connection =sqsConfig.getConnection();		
		Session session=null;
		
		try {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue =  session.createQueue("truckposition.fifo");		
			MessageProducer producer = session.createProducer(queue);
			TextMessage message = session.createTextMessage(txtMsg);
			// Set the message group ID
			message.setStringProperty("JMSXGroupID", "Default");
			producer.send(message);
			System.out.println("ID for sent JMS message " + message.getJMSMessageID());
			System.out.println("JMS Message Sequence Number " + message.getStringProperty("JMS_SQS_SequenceNumber"));
			
		} catch (JMSException e) {
			e.printStackTrace();
		}		

	}

}
