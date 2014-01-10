#
# Base Server Module
#

[optional]
jvm
jmx
ext
resources

[lib]
lib/*.jar

[xml]
etc/jetty.xml

[ini-template]
## Server Threading Configuration
# minimum number of threads
threads.min=10
# maximum number of threads
threads.max=200
# thread idle timeout in milliseconds
threads.timeout=60000
# What host to listen on (leave commented to listen on all interfaces)
#jetty.host=myhost.com
# Dump the state of the Jetty server, components, and webapps after startup
jetty.dump.start=false
# Dump the state of the Jetty server, before stop
jetty.dump.stop=false


