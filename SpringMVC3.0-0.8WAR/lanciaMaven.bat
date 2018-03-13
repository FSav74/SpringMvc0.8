call mvn clean

call mvn compile
call mvn package

del "C:\software\jboss-eap-6.3.0\jboss-eap-6.3\standalone\deployments\SpringMVC3.0-0.8WAR-1.0-SNAPSHOT*"

move "C:\software\progetti-KEPLERx86_64\SpringMVC3.0-0.8\SpringMVC3.0-0.8WAR\target\SpringMVC3.0-0.8WAR-1.0-SNAPSHOT.war" "C:\software\jboss-eap-6.3.0\jboss-eap-6.3\standalone\deployments\"
