FROM tomcat:9.0

ARG debug_port

RUN rm -rf /usr/local/tomcat/webapps/ROOT
ADD ./target/prototype-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

RUN apt-get update \
	&& DEBIAN_FRONTEND=noninteractive apt-get -y install qemu-kvm libvirt-bin libvirt-clients \
	&& apt-get clean

ENV CATALINA_OPTS "-Xms128M -Xmx384M -agentlib:jdwp=transport=dt_socket,address=$debug_port,server=y,suspend=n"

CMD ["catalina.sh", "run"]