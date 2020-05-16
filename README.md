# Dash

## Build the build container

```
docker-compose build
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

## If something goes wrong...

```
docker-compose run dash-pipeline debug
```
