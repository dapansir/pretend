package org.pretend.tools.parse.resolve;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SchemaErrorHandler implements ErrorHandler {

	@Override
	public void warning(SAXParseException exp) throws SAXException {
		System.out.println(exp.getLineNumber());
	}

	@Override
	public void error(SAXParseException exp) throws SAXException {
		System.out.println(exp.getLineNumber());
	}

	@Override
	public void fatalError(SAXParseException exp) throws SAXException {
		System.out.println(exp.getLineNumber());
	}

}
