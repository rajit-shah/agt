package filesystemscanner.web.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import filesystemscanner.web.exception.ScannerServiceException;
import filesystemscanner.web.resources.IScannerService;

/**
 * @author rajit shahi
 * 
 */
@RestController
@EnableAutoConfiguration
public class ServiceContoller
{
	@Autowired
	IScannerService service;

	@RequestMapping(value = "/scan", method = RequestMethod.GET)
	Object scan(@RequestParam(value = "path", defaultValue = "") String path)
	{
		if (path != null && !path.isEmpty()) {
			return service.scan(path);
		} else {
			throw new ScannerServiceException(
					"Please send directory path to scan and the file type to limit the scan as a request parameter. For example: scan?path=c:/folder&filetype=txt");
		}
	}
}
