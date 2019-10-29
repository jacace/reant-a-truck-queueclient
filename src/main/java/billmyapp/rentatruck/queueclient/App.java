package billmyapp.rentatruck.queueclient;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import billmyapp.rentatruck.queueclient.configuration.SQSConfig;

//
public class App {

	public static void main(String[] args) {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.register(SQSService.class);
			ctx.register(SQSConfig.class);
			ctx.refresh();

			SQSService sqsService = (SQSService) ctx.getBean(SQSService.class);
			sqsService.sendMessage("Hello World!");
			String msg = "Hello World!";
			System.out.println(msg);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
