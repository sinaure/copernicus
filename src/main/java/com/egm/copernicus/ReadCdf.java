package com.egm.copernicus;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
//RUN ME WITH argument  S3B_OL_2_LFR/tie_meteo.nc 
public class ReadCdf {
	private static Logger logger = LoggerFactory.getLogger(ReadCdf.class);
	static String fileName;
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
			ncfile = NetcdfFile.open(file.getAbsolutePath());
			getVariables();
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
			  logger.info(v.getNameAndDimensions());
		  }
		 
	}
}