package com.egm.copernicus;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ucar.ma2.Array;
import ucar.nc2.Dimension;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import ucar.nc2.VariableSimpleIF;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dt.GridCoordSystem;
import ucar.nc2.dt.GridDatatype;
import ucar.nc2.dt.grid.GridDataset;
//RUN ME WITH argument  S3B_OL_2_LFR/tie_meteo.nc 
//https://gis.stackexchange.com/questions/76194/netcdf-java-has-trouble-reading-this-netcdf-fileformat-and-fetching-values-fro
public class ReadCdf {
	private static Logger logger = LoggerFactory.getLogger(ReadCdf.class);
	static String fileName;
	static String fileCoord = "S3B_OL_2_LFR/instrument_data.nc";
	static NetcdfFile ncfile = null;
	/**
	 * Prints schema (structure) of an existing netCDF file with a specified file
	 * name.
	 *
	 * @param args name of netCDF file to be read.
	 */
	public static void main(String[] args) {

		if (args.length == 1)
			fileName = args[0];
		else {
			logger.error("no netCDF file name specified, exiting ...");
			System.exit(-1);
		}
		try {
			ClassLoader classLoader = new ReadCdf().getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			File filegeo = new File(classLoader.getResource(fileCoord).getFile());
			
			ncfile = NetcdfFile.open(file.getAbsolutePath());
			getVariables();
			readNetCDFFile(filegeo.getAbsolutePath());
			
		} catch (IOException ioe) {
			logger.error("trying to open " + fileName,ioe);
		} finally {
			if (null != ncfile)
				try {
					ncfile.close();
				} catch (IOException ioe) {
					logger.error("trying to close " + fileName,ioe);
				}
		}

	}
	private static void getVariables() {
		  for(Variable v : ncfile.getVariables()) {
			  for(Dimension d : v.getDimensions()) {
				  logger.info(v.getName()+" - "+d.getName());
			  }
			 
		  }
	}
	
	private static void readNetCDFFile(String filename) {
        NetcdfDataset ncfile = null;
        try {
            ncfile = NetcdfDataset.openDataset(filename);
            GridDataset grid = new GridDataset(ncfile);
            logger.info("GRID:" + grid);
            process(grid);
        } catch (IOException ioe) {
        	logger.info("trying to open " + filename, ioe);
        } finally {
            if (null != ncfile)
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                	logger.info("trying to close " + filename, ioe);
                }
        }
        NetcdfDataset.shutdown();
    }
	private static void process(GridDataset gds) throws IOException {
		logger.info("VARS:");
        for (VariableSimpleIF v : gds.getDataVariables())
        	logger.info(v.getName());
        GridDatatype grid = gds.findGridDatatype("FWHM");
        GridCoordSystem gcs = grid.getCoordinateSystem();
        double lat = -63.63;
        double lon = -14.83;
        //lat = -61.24769;
        //lon = 78.00004;
        lat = -77.7742;
        lon = 78.00004;

        // find the x,y index for a specific lat/lon position
        // xy[0] = x, xy[1] = y
        int[] xy = gcs.findXYindexFromLatLonBounded(lat, lon, null);

        // read the data at that lat, lon and the first time and z level (if any)
        // note order is t, z, y, x
        System.out.println("x,y:" + xy[0] + "," + xy[1]);
        Array data = grid.readDataSlice(0, 0, xy[1], xy[0]);
        // we know its a scalar
        double val = data.getDouble(0);

        System.out.printf("Value at %f %f == %f%n", lat, lon, val);
    }
	
}