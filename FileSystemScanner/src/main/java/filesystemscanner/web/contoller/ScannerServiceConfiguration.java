package filesystemscanner.web.contoller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import filesystemscanner.web.resources.IScannerService;
import filesystemscanner.web.resources.impl.TextFileScanner;
import filesystemscanner.web.resources.impl.TextFileScannerService;

/**
 * @author rajit shahi
 *
 */
@Configuration
public class ScannerServiceConfiguration
{
	@Bean
	@ConditionalOnProperty(name = "scanner.name", havingValue = "txt", matchIfMissing = true)
	public IScannerService txtScannerService()
	{
		return new TextFileScannerService();
	}
	
	@Bean
	public TextFileScanner getTextFileScanner()
	{
		return new TextFileScanner();
	}
	
	@Bean
	public ObjectMapper objectMapper()
	{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper;
	}
	
}
