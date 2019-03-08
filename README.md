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

### /ReadNetCdf

Run as: 

with arguments var :   S3B_OL_2_LFR/instrument_data.nc  (file path)



Mundi: Atos copernicus ==> dias

CORE SERVICES (tematique) -- DIAS 