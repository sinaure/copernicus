package com.egm.copernicus;

import java.io.IOException;

import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class ReadCdf {
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
			System.err.println("no netCDF file name specified, exiting ...");
			System.exit(-1);
		}
		try {
			ncfile = NetcdfFile.open(fileName);
			process();
		} catch (IOException ioe) {
			System.out.println("trying to open " + fileName);
		} finally {
			if (null != ncfile)
				try {
					ncfile.close();
				} catch (IOException ioe) {
					System.out.println("trying to close " + fileName);
				}
		}

	}
	private static void process() {
		String varName = "humidity"; 
		  Variable v = ncfile.findVariable(varName);
		  if (null == v) return;
		  System.out.println(v.toStringDebug());
	}
}