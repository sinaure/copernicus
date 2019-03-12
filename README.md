# Copernicus



## 1.download product metadata 



```bash
./dhusget.sh -u asinatra -p p0rtugal -c 7.084936032762926,43.53319183759211:7.02391041325609,43.509168148747605 -F 'platformname:Sentinel-3 AND producttype:OL_2_WRR'  -t 48
```

### 2. get product

find inside :

search@q=platformname%3ASentinel-3 AND ingestiondate%3A[NOW-60DAYS TO NOW] AND instrumentshortname%3AOLCI AND productlevel%3AL2

the product reference

```bash
wget --no-check-certificate --user sinaure --password p0rtugal https://scihub.copernicus.eu/dhus/odata/v1/Products('5b03a273-e7a0-44b3-92b4-228673a8838e')/$value

```

### ReadNetCdf

Run as: 

with arguments var :   S3B_OL_2_LFR/instrument_data.nc  (file path)

https://www.unidata.ucar.edu/software/thredds/current/netcdf-java/tutorial/NetcdfFile.html



### Defis

DEFIS THALES: REALTIME MONITORING + IOT

DEFIS ATOS : utilizer les services Mundi

DEFIS Cluster SAFE : securité jeux olympiques 2024

DEFIS CNES : harvest forecasting / agricolture

DEFIS KINAXIA : cityscan.fr  POLLUTION AIR / ANALYSE SOL / PLAN HURBANISME (vectoriel)

DEFIS IGN : access a WMS / WMS /WMTS   (valorizer source des donnes IGN)



### ATOS MUNDI

https://jupyter.org/documentation

https://mundiwebservices.com/marketplace 

Mundi: Atos copernicus ==> dias

CORE SERVICES (tematique) -- DIAS 

Formats : NETCDF, cloud optimize geotiff, JPEG2000





https://gis.stackexchange.com/questions/284946/converting-netcdf-to-geotiff-file

SENTINEL ==> scheduled   PLEIADE ==> on demand

SENTINEL 1-2 resolution (OPTIQUE = 10 RADAR = 50 m)



### TOOLS

Download NetcdfUI

https://www.unidata.ucar.edu/software/thredds/v4.5/netcdf-java/ToolsUI.html

java -Xmx1g -jar toolsUI.jar

-----------------





Idee : 

1. get data NETcdf
2. convert to geotiff or raster
3. send to geoserver
4. retrive from geoserver project to openlayer 

### docker

```bash
docker volume create geoserver 
docker volume create postgres_database
docker-compose up

```

### GEOSERVER

accessible at localhost:8088/geoserver

1. create WMS
2. consume wms from web app with gogle maps or openlayer