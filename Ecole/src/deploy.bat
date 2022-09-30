rm -r java
mkdir java
mkdir classes
cp */*.java java
javac -d classes java/*.java
cp -r classes/ '/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/Ecole/WEB-INF'
rm -r java
rm -r classes