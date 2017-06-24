FROM tomcat:9.0

RUN rm -rf /usr/local/tomcat/webapps/ROOT
ADD prototype-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

RUN apt-get update \
	&& DEBIAN_FRONTEND=noninteractive apt-get -y install qemu-kvm libvirt-bin libvirt-clients

ENV CATALINA_OPTS "-agentlib:jdwp=transport=dt_socket,address=1043,server=y,suspend=n"

CMD ["catalina.sh", "run"]