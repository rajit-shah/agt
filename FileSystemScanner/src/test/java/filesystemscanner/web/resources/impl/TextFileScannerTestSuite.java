package filesystemscanner.web.resources.impl;

import java.io.File;
import java.net.URI;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import filesystemscanner.web.beans.TextFileInfo;
import filesystemscanner.web.contoller.ServiceContoller;

/**
 * Test case for {@link TextFileScanner} class
 * @author rajit shahi
 *
 */
public class TextFileScannerTestSuite
{
	private TextFileScanner scanner;

	private TextFileScanRequirements scanRequirements;

	private File sampleTestFile;

	@Before
	public void setup() throws Exception
	{
		scanRequirements = new TextFileScanRequirements();
		scanRequirements.setMaxWordCount(10);
		scanRequirements.setRepeatingWordFrequency(2);
		this.scanner = new TextFileScanner(scanRequirements);
		URI uri = ServiceContoller.class.getResource("/sampledata/sample.txt").toURI();
		sampleTestFile = new File(uri);
	}

	@Test
	public void testSampleFileScan()
	{
		Optional<TextFileInfo> optional = scanner.scan(sampleTestFile);
		Assert.assertTrue(optional.isPresent());
		Assert.assertEquals("Total number of words count", 22, optional.get().getWordCount(), 0);
		Assert.assertFalse("Recurring words are present", optional.get().getRepeatingWords().isEmpty());
		Assert.assertEquals("Number of Recurring words are", 2, optional.get().getRepeatingWords().size(), 0);
		Assert.assertTrue("Recurring words count should be more than 2 as set in ScanRequirements", optional.get()
				.getRepeatingWords().get(0).getOccurences() > scanRequirements.getRepeatingWordFrequency());
	}
}
