# Dash

## Build the build container

```
docker-compose build
docker push matefarkas/dash-pipeline
```
## Run tests

```
docker-compose run dash-pipeline test
```

## Run the pipeline

```
export JKS_STORE_BASE64=$(cat keystore.jks | base64 -w0)
export JKS_STORE_PASSWORD=<store_password>
export JKS_KEY_ALIAS=dash
export JKS_KEY_PASSWORD=<key_password>
export SUPPLY_JSON_KEY_DATA=$(cat secret.json)

docker-compose run dash-pipeline
```

## Releasing a new version

Run before invoking the pipeline:

```
export RELEASE=true

```

### On GitHub

1. Bump `versionCode` in `app/build.gradle`
2. Add changelog to `fastlane/metadata/android/en-US/changelogs/<versionCode>.txt`
3. Create a PR to the `release` branch
4. Wait for the automatic test
5. Merge if test is green

## If something goes wrong...

```
docker-compose run dash-pipeline debug
```
