#!/bin/sh

npm install
node node_modules/sass/sass.js --load-path node_modules --load-path ../_sass --embed-source-map dash.scss ../assets/css/dash.css
