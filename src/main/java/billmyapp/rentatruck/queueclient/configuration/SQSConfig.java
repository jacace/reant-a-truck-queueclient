package billmyapp.rentatruck.queueclient.configuration;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {
	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;

	@Bean
	public SQSConnection getConnection() {
		// Native AWS client
		AmazonSQSClient awsClient = new AmazonSQSClient(getAmazonAWSCredentials());
		SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(), awsClient);
		SQSConnection connection = null;

		try {
			connection = connectionFactory.createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		// JMS client
		// AmazonSQSMessagingClientWrapper jmsClient =
		// connection.getWrappedAmazonSQSClient();
		return connection;
	}

	@Bean
	public AWSCredentials getAmazonAWSCredentials() {
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}

}
