A simple Spring Boot application to scan a file system for a text file. The application is a single  jar file containing all the dependencies.
The name of the jar file is file-scanner-service.jar

How to start the application:
 - open shell (in linux) or Command (in windows). Browse to the location where the jar file is located
 - run java -jar file-scanner-service.jar
 
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