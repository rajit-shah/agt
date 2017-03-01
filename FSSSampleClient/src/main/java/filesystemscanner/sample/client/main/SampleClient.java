package filesystemscanner.sample.client.main;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.ektorp.http.HttpClient;
import org.ektorp.http.HttpResponse;
import org.ektorp.http.StdHttpClient;

/**
 * Sample client that consumes FileSystemScanner Service. The client uses the org.ektorp library to
 * connect to the remote http service. It is assumed that the service is running in the same machine
 * as the client will be running and that the service is bound to the port 8080 of the local
 * machine.
 * <p>
 * The client calls the /scan service and prints the result into the console.
 * @author rajit shahi
 *
 */
public class SampleClient
{

	int subDirectoryCount = 0;

	ObjectMapper om = new ObjectMapper();

	public void callService(String path)
	{
		StdHttpClient.Builder builder = new StdHttpClient.Builder().host("localhost").port(8080).caching(false);
		HttpClient client = builder.build();
		try {
			HttpResponse httpResponse = client.get("/scan-service/scan?path=" + path);
			if (httpResponse.isSuccessful()) {
				try {
					JSONObject json = om.readValue(httpResponse.getContent(), JSONObject.class);
					JSONObject longFiles = json.getJSONObject("longFiles");
					System.out.println("**** LONG FILES ****");
					System.out.println("<Directory " + longFiles.getString("name") + ">");
					JSONArray fileInfos = longFiles.getJSONArray("fileInfo");
					printFiles(fileInfos, 5);
					printDirectory(longFiles, 5);

					JSONObject shortFiles = json.getJSONObject("shortFiles");
					System.out.println("**** SHORT FILES ****");
					System.out.println("<Directory " + shortFiles.getString("name") + ">");
					directoryCountReset();
					fileInfos = shortFiles.getJSONArray("fileInfo");
					printFiles(fileInfos, 5);
					printDirectory(shortFiles, 5);
				} catch (IOException e) {
					System.out.println("Error Parsing the response from the service: " + httpResponse.getRequestURI());
				}
			} else {
				JSONObject json = om.readValue(httpResponse.getContent(), JSONObject.class);
				System.out.println("There was problem calling the service. The server responded with HTTP status code: "
						+ httpResponse.getCode() + " and message " + json.toString());
			}
		} catch (Exception e) {
			System.out.println("There was problem calling the service. Error:  " + e.getMessage());
		} finally {
			client.shutdown();
		}

	}

	private int directoryCountUp()
	{
		return ++subDirectoryCount;
	}

	private int directoryCountReset()
	{
		return subDirectoryCount = 0;
	}

	public static void main(String[] args)
	{
		if (args.length == 0) {
			System.out.println("Please supply the path to scan for the text file..");
		} else {
			new SampleClient().callService(args[0]);
		}
	}

	private void printDirectory(JSONObject json, int tab)
	{
		JSONObject directory = json.getJSONObject("directory");
		if (!directory.isNullObject()) {
			System.out.printf("%-" + tab + "s %5s %n", "",
					"<sub-directory #" + directoryCountUp() + " " + directory.getString("name") + ">");
			JSONArray fileInfos = directory.getJSONArray("fileInfo");
			printFiles(fileInfos, tab * 2);
			printDirectory(directory, tab);
		}
	}

	protected void printFiles(JSONArray fileInfos, int tab)
	{
		int size = fileInfos.size();
		for (int i = 1; i <= size; i++) {
			JSONObject fileInfo = fileInfos.getJSONObject(i - 1);
			StringBuilder builder = new StringBuilder();
			builder.append("<file #").append(i).append(" ").append(fileInfo.getString("filename")).append(">");
			builder.append("<#").append(fileInfo.getInt("wordCount")).append(" words").append(">");

			System.out.printf("%-" + tab + "s %5s", "", builder.toString());
			JSONArray repeatingWords = fileInfo.getJSONArray("repeatingWords");
			if (!repeatingWords.isEmpty()) {
				for (int r = 0; r < repeatingWords.size(); r++) {
					JSONObject repeatingWord = repeatingWords.getJSONObject(r);
					builder = new StringBuilder();
					builder.append("<").append(repeatingWord.getString("word")).append(" ")
							.append(repeatingWord.getInt("occurences")).append(">");
					System.out.printf("%-5s %1s", "", builder.toString());
				}
			}

			System.out.println();
		}
	}
}
