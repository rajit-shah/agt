
	There are two java projects.  

	1. FileSystemScanner is the service written in Spring Boot that allows scanning for file system as per the task description.
	
	2. FSSSampleClient is the client that consumes FileSystmScanner service running in local machine at port 8080. It takes only full path to the directory as the parameter.
	
	The build version are available inside build folder.

	Follow below instructions to run the service:

	 - open shell (in linux) or Command (in windows). Browse to the location where the jar file is located
	 - run java -jar file-scanner-service.jar	 