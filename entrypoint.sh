#!/bin/sh -e

case "${1}" in
  debug)
    exec bash
    ;;

  test)
    LANE=test
    ;;

  *)
    LANE=beta
esac

if [ "${GITHUB_WORKSPACE}" ]
then
  cd ${GITHUB_WORKSPACE}
fi

exec bundle exec fastlane ${LANE}
