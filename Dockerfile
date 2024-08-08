FROM eclipse-temurin:17.0.5_8-jre-alpine@sha256:02c04793fa49ad5cd193c961403223755f9209a67894622e05438598b32f210e
# Default Settings (for optional Parameter)
ENV ZONEINFO ${ZONEINFO:-America/Bogota}

# Set Variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk\
    PATH=/usr/lib/jvm/java-17-openjdk/bin:$PATH\
    CLASSPATH=$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar\
    NLS_LANG=SPANISH_SPAIN.AL32UTF8

# Update the system and prepare it
RUN apk update && apk upgrade --available && sync\
    && apk add --no-cache nano bash tzdata openssl openjdk17-jre\
    && ln -snf /usr/share/zoneinfo/${ZONEINFO} /etc/localtime\
    && echo ${ZONEINFO} > /etc/timezone\
    && sed -i 's/ash/bash/g' /etc/passwd\
	&& addgroup -g 1000 glfsmart && \
    adduser --disabled-password --uid 1000 --ingroup glfsmart --shell /bin/bash glfsmart && \
	rm -rf /var/lib/apt/lists/* && rm -rf /var/cache/apt/*\
    && rm -rf /tmp/{.}* /tmp/*\
	&& rm -rf /var/cache/apk/*

USER glfsmart
WORKDIR /home/glfsmart

ARG JAR_FILE
COPY ${JAR_FILE} app.jar 

EXPOSE 8585

# Run the application
CMD ["java", "-jar", "/home/glfsmart/app.jar"]
