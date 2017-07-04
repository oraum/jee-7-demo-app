FROM websphere-liberty:latest

COPY h2-1.4.195.jar /opt/ibm/wlp/usr/shared/resources/h2/
COPY server.xml /opt/ibm/wlp/usr/servers/defaultServer/

COPY jee7demo-ear-1.0-SNAPSHOT.ear /opt/ibm/wlp/usr/servers/defaultServer/dropins/
#COPY jee7demo.ear /opt/ibm/wlp/usr/servers/defaultServer/dropins/

ENV LICENCE accept
