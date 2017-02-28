package filesystemscanner.web.contoller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author rajit shahi
 *
 */
@SpringBootApplication
@ComponentScan("filesystemscanner.web")
public class ServiceStarter
{
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(ServiceStarter.class, args);
	}
}
