package billmyapp.rentatruck.queueclient;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import billmyapp.rentatruck.queueclient.configuration.SQSConfig;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
    	String test="test string";
    	
		try {
			
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SQSConfig.class);
			SQSService sqsService = ctx.getBean(SQSService.class);
			sqsService.sendMessage("Hello World!");
	    	String msg="Hello World!";
	        System.out.println(msg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}    
    }
    
}
