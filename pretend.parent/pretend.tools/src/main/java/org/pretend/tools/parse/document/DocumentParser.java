package org.pretend.tools.parse.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.pretend.common.bean.BeanDefinition;
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
	
	public List<BeanDefinition> parseXML() throws ParserConfigurationException, SAXException, IOException{
		List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();
		Document document = getDocument();
		Element root = document.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Element.ELEMENT_NODE){
				Element element = (Element) node;
				beanDefinitions.add(parseElement(element));
			}
		}
		return beanDefinitions;
	}

	public BeanDefinition parseElement(Element element){
		NameSpaceHandler handler = ExtensionLoader.getExtensionLoader(NameSpaceHandler.class).getExtension(element.getNamespaceURI());
		Parser parser = handler.getParser(element.getLocalName());
		return parser.parse(element);
	}
	
	public static void main(String[] args) {
		DocumentParser docParser = new DocumentParser("META-INF/config/tools.xml");
		try {
			docParser.parseXML();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
