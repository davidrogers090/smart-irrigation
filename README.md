# smart-irrigation
A smart irrigation system that allows for irrigation control via REST.  A simple WebApp will be provided.

# Development tools
Tomcat 9  
Maven 3  

# Build
This project uses Maven 3 to handle the build.  The pom.xml has a profile configured to automatically deploy the completed war to tomcat.  For this to work, the profile must be enabled and the tomcat webapps directory must be specified in your settings.xml.  Example settings.xml configuration:  
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
	<activeProfiles>
		<activeProfile>tomcatConfig</activeProfile>
	</activeProfiles>
</settings>
```
