#!/bin/sh -e

if [ "${1}" = 'debug' ]
then
  exec bash
fi

if [ "${GITHUB_WORKSPACE}" ]
then
  cd ${GITHUB_WORKSPACE}
fi

exec bundle exec fastlane beta
