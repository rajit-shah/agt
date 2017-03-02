A simple Spring Boot application to scan a file system for a text file. The application is a single  jar file containing all the dependencies.
The name of the jar file is file-scanner-service.jar

How to start the application:
 - open shell (in linux) or Command (in windows). Browse to the location where the jar file is located
 - run java -jar file-scanner-service.jar
 
The service runs with the context name: /scan-service

It can be accessed by: curl http://localhost:8080/scan-service
To scan the a directory for a txt files: curl http://localhost:8080/scan-service/scan?path=C:/TestData

Requirements to run: 
 - JRE-8 installed and JAVA_HOME must point to where the JRE is installed
 
Configuration:
By default, the application scans all text file. Further it is configured to scan files as large files containing more than 
	- 1000 words
	- and count words which repeat more than 50 times
	
	should the requirement changes, 
	for example: large files is to be regarded as one with 
	 - 2000 words and 
	 - count words if they repeat more than 100 times 
	
	 this can be configured by setting the environment variable and running the command as follows:
	 
	 - java -Dscan.repeatingWordFrequency=100 -Dscan.maxWordCount=2000 -jar file-scanner-service.jar

Design Considerations:
The Controller delegates to the instance of filesystemscanner.web.resources.IScannerService to handle filesystem  scan request. 
The implementation class filesystemscanner.web.resources.impl.TextFileScannerService.TextFileScannerService is injected as a
default implemenation for IScannerService. Should the different behaviour is required, a different implemenation class can be
provided and defined in configuration class filesystemscanner.web.contoller.ScannerServiceConfiguration. The property of
"scanner.name" can be then used to control the injection of different implementation class. The property can be specified 
as environment variable SCANNER_NAME. Should the property is not passed, the system assumes "txt" as default value and 
thus injecting an instance of filesystemscanner.web.resources.impl.TextFileScannerService.TextFileScannerService.
The property can be passed as:
	java -DSCANNER_NAME=txt -jar file-scanner-service.jar

The criteria for assuming large files and threshold value for repeating words can also be configured. This allows scanning 
feature to provide different results based upon different criteria. The bean defining these criterias are represented as
filesystemscanner.web.resources.impl.TextFileScanRequirements class with object attributes maxWordCount and repeatingWordFrequency.
The default values are 1000 and 50 consecutively. Different values can be supplied as enviroment variable as shown above.
