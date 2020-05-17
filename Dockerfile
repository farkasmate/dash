ARG JDK_VERSION=11-buster
ARG RUBY_VERSION=2.5-buster

# android
FROM openjdk:${JDK_VERSION} AS android

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

# fastlane
FROM ruby:${RUBY_VERSION} AS fastlane

WORKDIR /bundle/

ADD Gemfile .
ADD Gemfile.lock .

RUN bundle install

# build
FROM openjdk:${JDK_VERSION} AS build

ENV ANDROID_HOME=/android/
ENV LANG=en_US.UTF-8

RUN apt-get -y update && \
    apt-get install -y ruby && \
    gem install bundler -v '~> 1' && \
    ln -s /usr/lib/x86_64-linux-gnu/libruby-2.5.so.2.5 /lib/x86_64-linux-gnu/libruby.so.2.5 && \
    rm -rf /var/lib/apt/lists/*

ENV GEM_HOME=/usr/local/bundle

COPY --from=android /android /android
COPY --from=android /root/.gradle /root/.gradle

COPY --from=fastlane /usr/local/bundle /usr/local/bundle

ADD entrypoint.sh /entrypoint.sh

WORKDIR /build/

ENTRYPOINT ["/entrypoint.sh"]
