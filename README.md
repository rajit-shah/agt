
	There are two java projects.  

	1. FileSystemScanner is the service written in Spring Boot that allows scanning for file system as per the task description.
	
	2. FSSSampleClient is the client that consumes FileSystmScanner service running in local machine at port 8080. 
	   It takes only full path to the directory as the parameter.
	
	The build version are available inside build folder.

	Follow below instructions to run the service:
	 - download file-scanner-service.jar file
	 - open shell (in linux) or Command (in windows). Browse to the location where the jar file is located
	 - run java -jar file-scanner-service.jar	
	 
	By default, the application scans all text file. Further it is configured to scan files as large files containing more than 
	 - 1000 words
	 - and count words which repeat more than 50 times
	
	Should the requirement changes, for example: large files is to be regarded as one with 
	 - 2000 words and 
	 - count words if they repeat more than 100 times 
	
	this can be configured by setting the environment variable and running the command as follows:
	 
	- java -Dscan.repeatingWordFrequency=100 -Dscan.maxWordCount=2000 -jar file-scanner-service.jar
	 
	 
	Follow below instructions to run the sample client:
	 - download sample-client-bin.zip file and unzip it  
	 - open shell (in linux) or Command (in windows) and browse into the unzipped location 
	 - locate sample-client.jar or sample-client.bat file
	 - run java -jar file-scanner-service.jar {path} or sample-client.bat {path} to run the client
	 	
