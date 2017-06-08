FROM websphere-liberty:latest

COPY h2-1.4.195.jar /opt/ibm/wlp/usr/shared/resources/h2/
COPY server.xml /opt/ibm/wlp/usr/servers/defaultServer/

COPY jee7demo.war /opt/ibm/wlp/usr/servers/defaultServer/dropins/

ENV LICENCE accept
