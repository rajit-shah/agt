package filesystemscanner.web.exception;

/**
 * Generic exception to be thrown during problem in fulfilling the service request
 * @author rajit shahi
 *
 */
public class ScannerServiceException extends RuntimeException
{

	private static final long serialVersionUID = 8685890487499066093L;

	public ScannerServiceException(String message)
	{
		super(message);
	}
}
