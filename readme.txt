Users note:
-----------
System requirements:
- JRE 7

How to run application:
- java -jar chatwall.jar

How to stop application:
- Simply write the "exit" command to the input

How to use applications, and other details:
- see the requirements: Twitter-like-excercise.doc

Rebuilding application with Maven (including test phase, and jar assembly with embedded dependecies):
- mvn clean install assembly:single
- rename the resulting jar file to proper name (generated name is like: target/chatwall-1.0-jar-with-dependencies.jar -> chatwall.jar)

Limitations of the application:
- No persistent storage, all data is lost if the application is stopped
- No concurrent access


Developers note:
----------------
Possible improvements (out of scope in this release):
- Extend DependencyConfiguration with proper Dependency Injection framework (like Spring)
- Extend input matching algorithm with validation -> ReadCommand is triggered by anything (not trivial to distinguish from other commands), so currently it must be at the end of the command list
- Make application eligible for multithreaded access
	-- Replace Memory<?>Storage with concurrent implementations (ConcurrentHasMap, or some non-blocking hash-map implementation if performance matters)
	-- With proper threadsafe Storage implementation, the application is threadsafe, as all other classes are stateless
- Implement proper exit logic





Created by Zoltan Juhasz
