package filesystemscanner.web.beans;

/**
 * Class to represent the result of the text file scan. It contains two detailed {@link Directory}
 * information, one of all files containing large words and of other containing lesser words
 * @author rajit shahi
 *
 */
public class TextFileScanResult
{
	private Directory longFiles = new Directory();

	private Directory shortFiles = new Directory();

	/**
	 * 
	 * @return information on all files containing large words
	 */
	public Directory getLongFiles()
	{
		return longFiles;
	}

	/**
	 * 
	 * @return information on all files containing lesser words
	 */
	public Directory getShortFiles()
	{
		return shortFiles;
	}

	public void setLongFiles(Directory longFiles)
	{
		this.longFiles = longFiles;
	}

	public void setShortFiles(Directory shortFiles)
	{
		this.shortFiles = shortFiles;
	}
}