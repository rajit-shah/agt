package filesystemscanner.web.resources.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import filesystemscanner.web.beans.RepeatingWords;
import filesystemscanner.web.beans.TextFileInfo;

@Component
public class TextFileScanner
{

	private TextFileScanRequirements scanRequirement;

	@Autowired
	public TextFileScanner(TextFileScanRequirements scanRequirement)
	{
		this.scanRequirement = scanRequirement;
	}

	private static final String CHARSET = "ISO-8859-1";

	private static final String DELIMETER = "-;:'` ,.[)]=(/*#?%$§+~<>°^–\t\r\n\"1234567890£";

	public Optional<TextFileInfo> scan(final File file)
	{
		try {
			String content = new String(Files.readAllBytes(Paths.get(file.toURI())), CHARSET);
			content = content.replaceAll("\\P{Print}", "");
			StringTokenizer st = new StringTokenizer(content, DELIMETER);
			ArrayList<String> words = new ArrayList<String>();
			while (st.hasMoreElements()) {
				words.add(st.nextToken());
			}

			TextFileInfo fileInfo = new TextFileInfo();
			fileInfo.setFilename(file.getName());
			fileInfo.setDirectory(file.getParent());
			fileInfo.setWordCount(words.size());
			if (words.size() > this.scanRequirement.getMaxWordCount()) {
				Set<String> duplicates = this.scanForDuplicates(words);
				for (String duplicate : duplicates) {
					int frequency = Collections.frequency(words, duplicate);
					if (frequency > this.scanRequirement.getRepeatingWordFrequency()) {
						fileInfo.getRepeatingWords().add(new RepeatingWords(duplicate, frequency));
					}
				}
			}
			return Optional.of(fileInfo);
		} catch (IOException e) {
			return Optional.empty();
		}
	}

	private Set<String> scanForDuplicates(final ArrayList<String> words)
	{
		final Set<String> duplicates = new HashSet<String>();
		for (String word : words) {
			if (!duplicates.contains(word)) {
				int frequency = Collections.frequency(words, word);
				if (frequency > this.scanRequirement.getRepeatingWordFrequency()) {
					duplicates.add(word);
				}
			}
		}
		return duplicates;
	}
}