#!/usr/bin/env bash

docker-compose exec geoserver sh -c "cp /opt/geoserver/geoserver-exts/*  /usr/local/tomcat/webapps/geoserver/WEB-INF/lib/"
docker-compose restart geoserver

curl -u admin:geoserver -v -POST -H "Content-type: text/xml" -d "<workspace><name>ws</name></workspace>" "http://localhost:8088/geoserver/rest/workspaces"
curl -u admin:geoserver -v -XPOST  -H "Content-type: application/json"     -d '{"coverageStore":{"name":"copernicusStore","type":"NetCDF","enabled":true,"_default":false,"workspace":{"name":"ws"},"url":"file:/home/rastabaluba/projects/copernicus/src/main/resources/S3B_OL_2_LFR/instrument_data.nc "}}'     http://localhost:8088/geoserver/rest/workspaces/ws/coveragestores
curl -v -u admin:geoserver http://localhost:8088/geoserver/rest/workspaces/ws/coveragestore

