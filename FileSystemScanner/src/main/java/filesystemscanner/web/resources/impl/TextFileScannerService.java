package filesystemscanner.web.resources.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import filesystemscanner.web.beans.TextFileInfo;
import filesystemscanner.web.beans.TextFileScanResult;
import filesystemscanner.web.exception.ScannerServiceException;
import filesystemscanner.web.resources.IScannerService;

/**
 * Implementation of {@link IScannerService} which scans the file system for files of extension
 * <b>TXT</b>. It scans provided path with TXT file format. Further based upon
 * {@link ScanRequirements}, it counts number of words and if number of words are larger than the
 * property <code> ScanRequirements.getMaxWordCount()</code> then it scans for the frequent word
 * occurrences and includes them as a part of a result if the occurrences are found to be greater
 * than <code> ScanRequirements.getRepeatingWordFrequency()</code> property.
 * @author rajit shahi
 *
 */
public class TextFileScannerService implements IScannerService
{

	private static final String FILE_EXTENSION = "txt";

	private TextFileScanner textFileScanner;

	private ScanRequirements scanRequirements;

	@Autowired
	public TextFileScannerService(TextFileScanner textFileScanner, ScanRequirements scanRequirements)
	{
		this.textFileScanner = textFileScanner;
		this.scanRequirements = scanRequirements;
	}

	@Override
	public Object scan(String path)
	{
		File file = new File(path);
		if (file.exists()) {
			Optional<TextFileScanResult> optional = this.read(file, f -> {
				if (f.isDirectory()) {
					return true;
				} else if (f.isFile()) {
					String name = f.getName();
					int pos = name.lastIndexOf('.');
					String ext = name.substring(pos + 1);
					return ext.equals(FILE_EXTENSION);
				} else {
					return false;
				}
			});

			if (optional.isPresent()) {
				return optional.get();
			} else {
				throw new ScannerServiceException("Nothing is found in path " + path + ".");
			}

		} else {
			throw new ScannerServiceException("The given path " + path + " could not be located.");
		}
	}

	public Optional<TextFileScanResult> read(final File path, final FileFilter filter)
	{
		File[] listOfFiles = path.listFiles(filter);
		if (listOfFiles == null) {
			return Optional.empty();
		}

		TextFileScanResult scanResult = new TextFileScanResult();
		scanResult.getLongFiles().setName(path.getName());
		scanResult.getLongFiles().setPath(path.getPath());
		scanResult.getShortFiles().setName(path.getName());
		scanResult.getShortFiles().setPath(path.getPath());

		for (int i = 0; i < listOfFiles.length; i++) {
			File currentFile = listOfFiles[i];
			if (currentFile.isFile()) {
				Optional<TextFileInfo> scan = this.textFileScanner.scan(currentFile);
				if (scan.isPresent()) {
					TextFileInfo fileInfo = scan.get();
					if (fileInfo.getWordCount() > this.scanRequirements.getMaxWordCount()) {
						scanResult.getLongFiles().addFileInfo(fileInfo);
					} else {
						scanResult.getShortFiles().addFileInfo(fileInfo);
					}
				}
			} else if (currentFile.isDirectory()) {
				Optional<TextFileScanResult> read = this.read(currentFile, filter);
				if (read.isPresent()) {
					TextFileScanResult scan = read.get();
					scanResult.getLongFiles().setDirectory(scan.getLongFiles());
					scanResult.getShortFiles().setDirectory(scan.getShortFiles());

				}
			}
		}
		return Optional.of(scanResult);
	}

}