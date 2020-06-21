FROM alpine:latest

ENV NO_UPDATE_NOTIFIER=true

WORKDIR /build/_precompile

RUN apk add --update-cache \
    npm \
  && rm -rf /var/cache/apk/*

ENTRYPOINT ["./entrypoint.sh"]
