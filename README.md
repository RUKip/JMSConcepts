# JMSConcepts
JMS concepts demo, for the RUG course Enterprise Application Integration 

### Prerequisites

Download glassfish from http://download.oracle.com/glassfish/5.0.1/release/glassfish-5.0.1.zip

Run the server (linux):
```
cd glassfish5\glassfish\bin\
sh startserv
```

Run the server (Windows):
```
cd glassfish5\glassfish\bin\
./startserv.bat
```

Create a queue and other desitination resources you want to use under JMS Resources -> Destination Resources. This queue can then be referenced in the code by that name. 
