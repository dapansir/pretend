package org.pretend.tools.parse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.pretend.common.loader.ExtensionLoader;
import org.pretend.common.util.ClassHelper;
import org.pretend.tools.api.NameSpaceHandler;
import org.pretend.tools.api.Parser;
import org.pretend.tools.parse.resolve.SchemaErrorHandler;
import org.pretend.tools.parse.resolve.SchemaResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class DocumentParser {
	
	/**
	 * JAXP attribute used to configure the schema language for validation.
	 */
	private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	/**
	 * JAXP attribute value indicating the XSD schema language.
	 */
	private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";
	
	private static final Map<String,NameSpaceHandler> nameSpaceHandlers = new ConcurrentHashMap<String,NameSpaceHandler>();
	
	private static NameSpaceHandler nameSpaceHandler = null;

	private String xmlPath = "";
	
	private boolean pathSet;
	
	public DocumentParser(String xmlPath) {
		super();
		setXmlPath(xmlPath);
	}
	
	private DocumentBuilderFactory createDocumentBuilderFactory(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
		return factory;
	}
	
	public Document getDocument() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilder builder = createDocumentBuilderFactory().newDocumentBuilder();
		builder.setEntityResolver(new SchemaResolver());
		builder.setErrorHandler(new SchemaErrorHandler());
		return builder.parse(getInputSource());
	}
	
	private InputSource getInputSource(){
		if(!isPathSet()){
			throw new IllegalStateException("the xmlPath to resolve cannot be null!");
		}
		ClassLoader loader = ClassHelper.getClassLoader(this.getClass());
		return new InputSource(loader.getResourceAsStream(xmlPath));
	}
	
	public boolean isPathSet() {
		return pathSet;
	}


	public void setPathSet(boolean pathSet) {
		this.pathSet = pathSet;
	}


	private void setXmlPath(String xmlPath) throws IllegalArgumentException {
		if(null == xmlPath || "".equals(xmlPath.trim())){
			throw new IllegalArgumentException("xmlPath cannot be null!");
		}
		this.xmlPath = xmlPath;
		setPathSet(true);
	}
	
	private static NameSpaceHandler getNameSpaceHandler(String uri) {
		NameSpaceHandler handler = nameSpaceHandlers.get(uri);
		if(null == handler) {
			nameSpaceHandlers.put(uri, ExtensionLoader.getExtensionLoader(NameSpaceHandler.class).getExtension(uri));
			handler = nameSpaceHandlers.get(uri);
		}
		return handler;
	}
	
	public Parser getParser() {
		
		return null;
	}

	public static void main(String[] args) {
		DocumentParser documentParser = new DocumentParser("META-INF/config/tools.xml");
		try {
			Document document = documentParser.getDocument();
			Element root = document.getDocumentElement();
			String uri = null;
			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if(node.getNodeType() == Element.ELEMENT_NODE){
					Element element = (Element) node;
					uri = element.getNamespaceURI();
					NameSpaceHandler handler = ExtensionLoader.getExtensionLoader(NameSpaceHandler.class).getExtension(uri);
					handler.parse(element);
				}
			}
			System.out.println(root.getLocalName());
			System.out.println(root.getTagName());
			System.out.println(root.getNamespaceURI());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
