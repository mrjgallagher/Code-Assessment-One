
This is a piece of demo code which was supposed to take under 2 hours to write.

It was also my my first time using Gradle ! Which i may now be converted to !

Run >$ Gradle clean build

Start up db
hsqldb-2.4.1\hsqldb>java -cp lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:data/mydb --dbname.0 Test

open up DB UI
hsqldb-2.4.1\hsqldb>java -cp lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing

Next Steps
############
Writing unit tests
Write Base class for handling DB Connections
Improve error handling and increase Error logging Details
