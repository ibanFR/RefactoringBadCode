package com.cd.bad.code;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;

public class DocumentElement {

    private final Element elem;
    private final String xPathString;
    private String jsonString;
    private String elementName;
    private List<Attribute> attributes;
    private String title;
    private String file;

    public DocumentElement(Element elem, String xPathString) {
        this.elem = elem;
        this.xPathString = xPathString;
    }

    String toJsonString() {
        jsonString = "";
        elementName = this.elem.getName();
        attributes = this.elem.attributes();
        title = this.elem.attributeValue("title");
        file = this.elem.attributeValue("file");
        if (elementName == "doc") {
            processDocAttributes();
            if (hasChildren(this.elem)) {
                addStateClosed();

            }
            jsonString = jsonString.concat("},");
        }

        else if (elementName == "folder")
        {
            jsonString = jsonString.concat("{");
            for (Attribute attribute : attributes)
            {
                String attrName = attribute.getName();
                jsonString = jsonString.concat("'data':'").concat(title).concat("',");
                if (attrName.equals("key"))
                {
                    String keyContent = this.elem.attributeValue("key");
                    jsonString = jsonString.concat("'attr':{'id':'")
                            .concat(this.xPathString)
                            .concat("_fk:")
                            .concat(keyContent)
                            .concat("'}");
                    if (file != null)
                    {
                        jsonString = jsonString.concat("','file':'")
                                .concat(file)
                                .concat("'}");
                    }

                    break;
                }
                else if (attrName.equals("type"))
                {
                    String typeContent = this.elem.attributeValue("type");
                    if (typeContent == "history") {
                        jsonString = jsonString.concat("'attr':{'id':'")
                                .concat(this.xPathString)
                                .concat("_fth,");

                    }
                    break;
                }
            }
            jsonString = jsonString.concat("},");
        }
        return jsonString;
    }

    private void addStateClosed() {
        jsonString = jsonString.concat(",'state':'closed'");
    }

    private void processDocAttributes() {

        for (Attribute attribute : attributes)
        {
            jsonString = jsonString.concat("{");
            String attrName = attribute.getName();
            jsonString = jsonString.concat("'data':'").concat(title).concat("',");
            if (attrName.equals("key"))
            {
                String keyContent = this.elem.attributeValue("key");
                jsonString = jsonString.concat("'attr':{'id':'")
                        .concat(this.xPathString)
                        .concat("_dk:")
                        .concat(keyContent)
                        .concat("','file':'")
                        .concat(file)
                        .concat("'}");

                break;
            }
            else if (attrName.equals("trnum"))
            {

                String trnumContent = this.elem.attributeValue("trnum");
                jsonString = jsonString.concat("'attr':{'id':'")
                        .concat(this.xPathString)
                        .concat("_dtrn:")
                        .concat(trnumContent)
                        .concat("','file':'")
                        .concat(file)
                        .concat("'}");

                break;
            }
        }
    }

    private static boolean hasChildren(Element elem) {
        return !elem.elements()
                .isEmpty();
    }
}
