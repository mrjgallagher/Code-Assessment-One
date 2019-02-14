
A small piece of demonstration code written in 2/3 hours.

This was interesting as it was my first time using Gradle ! I may now be converted !

Instructions 
#############
Run >$ Gradle clean build 

Start up db
hsqldb-2.4.1\hsqldb>java -cp lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:data/mydb --dbname.0 Test

Set Main.java as your main class, and add logs.txt as an input argument.

Next Steps
############
Writing unit tests
Write Base class for handling Database Connections
Improve error handling flow and increase error logging messages output details
