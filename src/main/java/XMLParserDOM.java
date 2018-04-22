import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Note: you need to implement either XMLDOMParser.java OR XMLSAXParser.java

class XMLParserDOM {

    private static boolean methodStarted = false;
    private static List<Method> methods = new ArrayList<>();
    private static Method method;


    void parse(String xml) {
        methods = new ArrayList<>();
        try{
            DOMParser parser = new DOMParser();
            parser.parse(xml);
            Document document = parser.getDocument();
            dataParser(document);
            outputGenerator();
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private static void outputGenerator() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Method method : methods) {
            stringBuilder.append(method.getVisibility()).append(" ");
            stringBuilder.append(method.getReturnType()).append(" ");
            stringBuilder.append(method.getId()).append("(");
            for (int i = 0; i < method.getParameters().size(); i++) {
                stringBuilder.append(method.getParameters().get(i).getType()).append(" ");
                stringBuilder.append(method.getParameters().get(i).getName());
                if (i == method.getParameters().size() - 1) {
                    stringBuilder.append(")");
                } else {
                    stringBuilder.append(", ");
                }
            }
            if (method.getExceptions().size() > 0) {
                stringBuilder.append("\n\tthrows ");
                for (int i = 0; i < method.getExceptions().size(); i++) {
                    stringBuilder.append(method.getExceptions().get(i));
                    if (i == method.getExceptions().size() - 1) {
                        stringBuilder.append(";\n");
                    } else {
                        stringBuilder.append(", ");
                    }
                }
            } else
                stringBuilder.append(";\n");
        }
        writeout(stringBuilder);
    }

    private static void dataParser(Node node) {
        if (node == null)
            return;
        int type = node.getNodeType();
        switch (type) {
            case Node.DOCUMENT_NODE: {
                dataParser(((Document) node).getDocumentElement());
                break;
            }
            case Node.ELEMENT_NODE: {
                String nodeName = node.getNodeName();
                if ((!methodStarted) &&
                        (nodeName.equals("abstract_method"))) {
                    methodStarted = true;
                    method = new Method();
                    method.setId(node.getAttributes().getNamedItem("id").getTextContent());
                } else if (methodStarted) {
                    if (nodeName.equals("visibility"))
                        method.setVisibility(node.getTextContent());
                    if (nodeName.equals("parameters")) {
                        Parameter parameter;
                        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                            try {
                                parameter = new Parameter();
                                if (node.getChildNodes().item(i).getNodeName().equals("argument")) {
                                    parameter.setName(node.getChildNodes().item(i).getTextContent());
                                    parameter.setType(node.getChildNodes().item(i).getAttributes()
                                            .getNamedItem("type").getTextContent());
                                    method.addParameter(parameter);
                                }
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    }

                    if (nodeName.equals("throw")) {
                        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                            try {
                                if (node.getChildNodes().item(i).getNodeName().equals("exception"))
                                    method.addException(node.getChildNodes().item(i).getTextContent());
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    }
                    if (nodeName.equals("return")) {
                        method.setReturnType(node.getTextContent());
                        methodStarted = false;
                        methods.add(method);
                        method = new Method();
                    }
                }

                NodeList childNodes = node.getChildNodes();
                if (childNodes != null) {
                    int length = childNodes.getLength();
                    for (int loopIndex = 0; loopIndex < length; loopIndex++) {
                        dataParser(childNodes.item(loopIndex));
                    }
                }
            }
            break;
        }
    }

    private static void writeout(Object o) {
        System.out.println(o.toString());
    }
}
