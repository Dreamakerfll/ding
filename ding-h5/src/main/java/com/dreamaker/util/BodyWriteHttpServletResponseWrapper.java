package com.dreamaker.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class BodyWriteHttpServletResponseWrapper extends HttpServletResponseWrapper{

	ByteArrayOutputStream output;
	FilterServletOutputStream filterOutput;

	public BodyWriteHttpServletResponseWrapper(HttpServletResponse response) {
	    super(response);
	    output = new ByteArrayOutputStream();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
	    if (filterOutput == null) {
	        filterOutput = new FilterServletOutputStream(output);
	    }
	    return filterOutput;
	    
	}

	public byte[] getDataStream() {

	    return output.toByteArray();
	}
}