package filesystemscanner.web.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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

	@Bean
	@ConditionalOnProperty(name = "scanner.name", havingValue = "txt", matchIfMissing = true)
	public IScannerService txtScannerService()
	{
		return new TextFileScannerService(textFileScanner, scanRequirements);
	}

	@Bean
	public ObjectMapper objectMapper()
	{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper;
	}

}
