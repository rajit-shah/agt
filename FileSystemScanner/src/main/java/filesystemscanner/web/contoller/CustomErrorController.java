package filesystemscanner.web.contoller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import filesystemscanner.web.beans.Message;

/**
 * Contoller for custom error page
 * @author rajit shahi
 *
 */
@Controller
public class CustomErrorController implements ErrorController
{

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public ResponseEntity<Object> error(final Model model, final HttpServletRequest request)
	{

		// Get the Http error code.
		final int error_code = this.getHttpStatusCode(request);

		// Generates Error message for corresponding Http Error Code.
		final String error_message = this.generateErrorMessage(error_code);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<Object>(new Message(error_message), headers, HttpStatus.valueOf(error_code));
	}

	private int getHttpStatusCode(final HttpServletRequest request)
	{
		return (int) request.getAttribute("javax.servlet.error.status_code");
	}

	private String generateErrorMessage(final int error_code)
	{
		String message = "";
		switch (error_code) {
		case 400:
			message = "Bad Request";
			break;
		case 404:
			message = "Not Found";
			break;
		case 405:
			message = "Method Not Allowed";
			break;
		case 500:
			message = "Internal Server Error";
			break;
		}
		return message;
	}

	@Override
	public String getErrorPath()
	{
		return PATH;
	}
}