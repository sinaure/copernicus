package com.egm.copernicus;

import ucar.nc2.NetcdfFile;

public class ShowNetcdfSchema {

    static String fileName;

    /** 
     * Prints schema (structure) of an existing netCDF file with a
     * specified file name.
     *
     * @param args name of netCDF file to be read.  */
    public static void main(String[] args) {
	
	if (args.length == 1)
	    fileName = args[0];
	else {
	    System.err.println("no netCDF file name specified, exiting ...");
	    System.exit(-1);
	}

	try {
		NetcdfFile nc = new NetcdfFile(fileName); // open it readonly
	    System.out.println(nc); // output schema in CDL form (like ncdump)
	} catch (java.io.IOException e) {
	    e.printStackTrace();
	}

    }
}
