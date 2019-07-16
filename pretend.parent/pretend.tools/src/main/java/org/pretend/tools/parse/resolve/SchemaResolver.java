package org.pretend.tools.parse.resolve;

import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class SchemaResolver implements EntityResolver {

	/** Suffix for DTD files */
	public static final String DTD_SUFFIX = ".dtd";

	/** Suffix for schema definition files */
	public static final String XSD_SUFFIX = ".xsd";
	
	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		
		if(XSD_SUFFIX.equals(systemId)){
			
			return new InputSource();
		}else{
			throw new  SAXException("Unsupprot operation!");
		}
	}

}
