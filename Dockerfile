ARG JDK_VERSION=11-stretch

FROM openjdk:${JDK_VERSION} AS dependencies

ENV ANDROID_HOME=/android/

WORKDIR /android/
RUN wget -q https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip && \
    unzip -qq sdk-tools-linux-4333796.zip && \
    rm sdk-tools-linux-4333796.zip && \
    cd /android/tools/lib/ && \
    wget -q \
      https://repo1.maven.org/maven2/javax/activation/activation/1.1.1/activation-1.1.1.jar \
      https://repo1.maven.org/maven2/javax/xml/jaxb-impl/2.1/jaxb-impl-2.1.jar \
      https://repo1.maven.org/maven2/javax/xml/bind/jaxb-api/2.3.1/jaxb-api-2.3.1.jar && \
    cd - && \
    sed -i 's|\(^CLASSPATH=.*$\)|\1:$APP_HOME/lib/activation-1.1.1.jar:$APP_HOME/lib/jaxb-impl-2.1.jar:$APP_HOME/lib/jaxb-api-2.3.1.jar|' ./tools/bin/sdkmanager && \
    yes | ./tools/bin/sdkmanager --licenses

WORKDIR /build/
ADD . .

RUN ./gradlew --no-daemon --console plain --warning-mode all :app:processDebugResources

FROM openjdk:${JDK_VERSION} AS build

ENV ANDROID_HOME=/android/

COPY --from=dependencies /android /android
COPY --from=dependencies /root/.gradle /home/gradle/.gradle

RUN useradd --shell /bin/bash gradle && \
    chown -R gradle:gradle /home/gradle

USER gradle:gradle
WORKDIR /build/

CMD ./gradlew --console plain --warning-mode all build
