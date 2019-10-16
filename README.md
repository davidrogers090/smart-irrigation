# smart-irrigation
A smart irrigation system that allows for irrigation control via REST.  A simple WebApp will be provided. NOTE: This project is not complete. Use Postman or other testing tools to access the backend.

# Development tools
Tomcat 9  
Maven 3  

# Build
This project uses Maven 3 to handle the build.  There are several options for building the project.  Listed below are 3 of the options in order from least to most production worthy.

Option 1 - Spring Boot development: This project makes use of Spring Boot.  As such, the following maven command will build the project and deploy it to an embedded tomcat server: `mvn spring-boot:run`.  This is useful for development as it allows for quick turn-around when testing, but should not be used in production.

Option 2 - Runnable war: Building the project using the following command will package the project into a runnable war file: 
`mvn clean package`.  The war file that is created (in the /target directory) contains an embedded tomcat server and when executed, will automatically set up and deploy the smart-irrigation project as a web service.  To execute the war file, use the following command: `java -jar <directory>/smart-irrigation-spring-boot.war` where `<directory>` is replaced with directory in which the war file resides.

Option 3 - Deploy to existing tomcat: The pom.xml has a profile configured to automatically deploy the completed war to tomcat.  For this to work, the profile must be enabled and the tomcat webapps directory must be specified in your settings.xml.  Example settings.xml configuration:  
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
	<localRepository />
	<interactiveMode />
	<usePluginRegistry />
	<offline />
	<pluginGroups />
	<servers />
	<mirrors />
	<proxies />
	<profiles>
		<profile>
			<id>tomcatConfig</id>
			<properties>
				<webappsDir>C:\DevTools\Tomcat9\webapps</webappsDir>
			</properties>
		</profile>
	</profiles>
</settings>
```
To deploy to the configured tomcat server, run the following command: `mvn clean package -P tomcatConfig`

# Technology Stack
The following technologies are used:
* Spring-boot
* H2 in-memory sql database
* JPA
* Quartz scheduler
* JavaScript

