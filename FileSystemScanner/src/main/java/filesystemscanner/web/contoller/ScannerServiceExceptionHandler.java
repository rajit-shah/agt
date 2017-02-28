package filesystemscanner.web.contoller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import filesystemscanner.web.beans.Message;
import filesystemscanner.web.exception.ScannerServiceException;

/**
 * @author rajit shahi
 *
 */
@ControllerAdvice
public class ScannerServiceExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler({ ScannerServiceException.class })
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request)
	{
		ScannerServiceException ire = (ScannerServiceException) e;
		Message message = new Message(ire.getMessage());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return this.handleExceptionInternal(e, message, headers, HttpStatus.FORBIDDEN, request);
	}
}
