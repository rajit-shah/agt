package filesystemscanner.web.beans;

import java.util.ArrayList;

/**
 * POJO class that represents the information on a text file like name of the file, directory it is
 * located and number of words it contains. It further contains information on repeating words.
 * @author rajit shahi
 *
 */
public class TextFileInfo
{
	private String directory;

	private String filename;

	private int wordCount;

	private ArrayList<RepeatingWords> repeatingWords = new ArrayList<RepeatingWords>();

	/**
	 * 
	 * @return name of the directory
	 */
	public String getDirectory()
	{
		return directory;
	}

	/**
	 * 
	 * @return name of the file
	 */
	public String getFilename()
	{
		return filename;
	}

	/**
	 * 
	 * @return number of words contained in the text file
	 */
	public int getWordCount()
	{
		return wordCount;
	}

	/**
	 * 
	 * @return List of repeating words
	 */
	public ArrayList<RepeatingWords> getRepeatingWords()
	{
		return repeatingWords;
	}

	/**
	 * set name of the directory
	 * @param directory
	 */
	public void setDirectory(String directory)
	{
		this.directory = directory;
	}

	/**
	 * set name of a file
	 * @param filename
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	/**
	 * set total number of words counted
	 * @param wordCount
	 */
	public void setWordCount(int wordCount)
	{
		this.wordCount = wordCount;
	}

	/**
	 * set array list of repeating words
	 * @param repeatingWords
	 */
	public void setRepeatingWords(ArrayList<RepeatingWords> repeatingWords)
	{
		this.repeatingWords = repeatingWords;
	}
}
