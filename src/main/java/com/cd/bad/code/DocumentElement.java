package com.cd.bad.code;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;

public abstract class DocumentElement {

    protected final Element elem;
    protected final String xPathString;
    protected String jsonString;
    protected List<Attribute> attributes;
    protected String title;
    protected String file;

    protected DocumentElement(Element elem, String xPathString) {
        this.elem = elem;
        this.xPathString = xPathString;
    }

    public static DocumentElement createDocumentElement(Element elem, String xPathString) {
        switch (elem.getName()) {
            case "doc":
                return new DocElement(elem, xPathString);
            case "folder":
                return new FolderElement(elem, xPathString);
            default:
                throw new IllegalStateException("Unexpected value: " + elem.getName());
        }
    }

    String toJsonString() {
        jsonString = "";
        attributes = this.elem.attributes();
        title = this.elem.attributeValue("title");
        file = this.elem.attributeValue("file");
        processElement();
        return jsonString;
    }

    protected abstract String getElemName();

    protected void processElement() {
        if (getElemName() == "doc") {
            throw new IllegalStateException("processElement should be overridden in DocElement");
        } else if (getElemName() == "folder") {
            jsonString = jsonString.concat("{");
            processFolderAttributes();
            closeElement();
        }
    }

    private void processFolderAttributes() {
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
    }

    protected void closeElement() {
        jsonString = jsonString.concat("},");
    }

    protected void addStateClosed() {
        jsonString = jsonString.concat(",'state':'closed'");
    }

    protected static boolean hasChildren(Element elem) {
        return !elem.elements()
                .isEmpty();
    }
}
