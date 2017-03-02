package filesystemscanner.web.contoller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import filesystemscanner.web.beans.Message;
import filesystemscanner.web.resources.IScannerService;
import filesystemscanner.web.resources.impl.TextFileScanRequirements;
import filesystemscanner.web.resources.impl.TextFileScanner;
import filesystemscanner.web.resources.impl.TextFileScannerService;

/**
 * Configuration class for providing an instance of {@link IScannerService}
 * @author rajit shahi
 *
 */
@Configuration
public class ScannerServiceConfiguration
{

	@Autowired
	TextFileScanRequirements scanRequirements;

	@Autowired
	TextFileScanner textFileScanner;

	private static final Logger logger = Logger.getLogger(ScannerServiceConfiguration.class);

	@Bean
	@ConditionalOnProperty(name = "scanner.name", havingValue = "txt", matchIfMissing = true)
	public IScannerService txtScannerService()
	{
		logger.info("returing an instance of " + TextFileScannerService.class.getName() + " for the interface "
				+ IScannerService.class.getName());
		return new TextFileScannerService(textFileScanner, scanRequirements);
	}

	/**
	 * Only for demonstration
	 * @return dummy implementation of {@link IScannerService}
	 */
	@Bean
	@ConditionalOnProperty(name = "scanner.name", havingValue = "dummy")
	public IScannerService dummyScannerService()
	{
		logger.info("returing an instance of " + TextFileScannerService.class.getName() + " for the interface "
				+ IScannerService.class.getName());
		return new IScannerService() {
			@Override
			public Object scan(String path)
			{
				return new Message("Dummy Service implementation for scanning : " + path);
			}
		};
	}

	@Bean
	public ObjectMapper objectMapper()
	{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper;
	}

}
