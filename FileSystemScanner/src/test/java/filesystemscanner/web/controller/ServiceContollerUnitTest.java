package filesystemscanner.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import filesystemscanner.web.contoller.ScannerServiceExceptionHandler;
import filesystemscanner.web.contoller.ServiceContoller;
import filesystemscanner.web.resources.impl.ScanRequirements;
import filesystemscanner.web.resources.impl.TextFileScanner;
import filesystemscanner.web.resources.impl.TextFileScannerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class ServiceContollerUnitTest
{

	private MockMvc mockMvc;

	private File sampleTestFile;

	static final String SampleResourceFile = "/sampledata";

	@Before
	public void setup() throws Exception
	{
		mockMvc = MockMvcBuilders.standaloneSetup(this.getController())
				.setControllerAdvice(new ScannerServiceExceptionHandler()).build();
		URI uri = ServiceContoller.class.getResource(SampleResourceFile).toURI();
		sampleTestFile = new File(uri);
	}

	@Test
	public void testNoPathSent() throws Exception
	{
		mockMvc.perform(get("/scan").accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}

	@Test
	public void testNonExistingPathSent() throws Exception
	{
		mockMvc.perform(get("/scan?path=D:/lova").accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}

	@Test
	public void testPathisNotDirectory() throws Exception
	{
		mockMvc.perform(
				get("/scan?path=" + this.sampleTestFile.getPath() + "/sample.txt").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testPathisDirectory() throws Exception
	{
		mockMvc.perform(get("/scan?path=" + this.sampleTestFile.getPath() + "/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testScanResult() throws Exception
	{
		mockMvc.perform(get("/scan?path=" + this.sampleTestFile.getPath() + "/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	private ServiceContoller getController()
	{
		ScanRequirements scanRequirements = new ScanRequirements();
		scanRequirements.setMaxWordCount(1000);
		scanRequirements.setRepeatingWordFrequency(50);
		return new ServiceContoller(new TextFileScannerService(new TextFileScanner(scanRequirements), scanRequirements));
	}
}