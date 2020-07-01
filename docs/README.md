# Dash web demo

## Build the build container

```
docker-compose build
```

## Precompile SASS resources

```
RUN_AS=$(id -u):$(id -g) docker-compose run sass
```

## Local testing

```
bundle install
bundle exec jekyll serve
```
