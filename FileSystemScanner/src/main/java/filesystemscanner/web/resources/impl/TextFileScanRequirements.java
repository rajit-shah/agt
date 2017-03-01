package filesystemscanner.web.resources.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author rajit shahi
 *
 */
@ConfigurationProperties(prefix = "scan")
@Configuration 
public class ScanRequirements
{
	private int maxWordCount;

	private int repeatingWordFrequency;

	public int getMaxWordCount()
	{
		return maxWordCount;
	}

	public int getRepeatingWordFrequency()
	{
		return repeatingWordFrequency;
	}

	public void setMaxWordCount(int maxWordCount)
	{
		this.maxWordCount = maxWordCount;
	}

	public void setRepeatingWordFrequency(int repeatingWordCount)
	{
		this.repeatingWordFrequency = repeatingWordCount;
	}
}
