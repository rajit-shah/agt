package filesystemscanner.web.resources;

/**
 * An abstract File system scanner service
 * 
 * <p>
 * Instances of this interface can be used to scan the file system with different scan requirements
 *
 * @author rajit shahi
 *
 */
public interface IScannerService
{
	/**
	 * scans a directory referred by given path
	 * @param path
	 * @return
	 */
	public Object scan(String path);
}
