package filesystemscanner.web.beans;

/**
 * POJO class to represent repeating words and their occurrences
 * @author rajit shahi
 *
 */
public class RepeatingWords
{
	private String word;

	private int occurences;

	public RepeatingWords(String word, int occurences)
	{
		this.word = word;
		this.occurences = occurences;
	}

	public String getWord()
	{
		return word;
	}

	public int getOccurences()
	{
		return occurences;
	}

	public void setWord(String word)
	{
		this.word = word;
	}

	public void setOccurences(int occurences)
	{
		this.occurences = occurences;
	}
}
