package filesystemscanner.web.beans;

/**
 * POJO class that can be used to represent a message
 * @author rajit shahi
 *
 */
public class Message
{
	private String message;

	public Message(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}
}