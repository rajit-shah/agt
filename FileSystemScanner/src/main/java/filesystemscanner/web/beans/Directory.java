package filesystemscanner.web.beans;

import java.util.ArrayList;

/**
 * Represents a information of a directory in file-system. It contains name and path of the
 * directory and List of informations on Text file. It further contains information on
 * sub-directories.
 * @author rajit shahi
 *
 */
public class Directory
{
	private String name;

	private String path;

	private ArrayList<TextFileInfo> fileInfo = new ArrayList<TextFileInfo>();

	private Directory directory;

	public String getName()
	{
		return name;
	}

	public String getPath()
	{
		return path;
	}

	public ArrayList<TextFileInfo> getFileInfo()
	{
		return fileInfo;
	}

	public Directory getDirectory()
	{
		return directory;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public void setFileInfo(ArrayList<TextFileInfo> fileInfo)
	{
		this.fileInfo = fileInfo;
	}

	public void setDirectory(Directory directory)
	{
		this.directory = directory;
	}

	public void addFileInfo(TextFileInfo fileInfo)
	{
		this.fileInfo.add(fileInfo);
	}
}