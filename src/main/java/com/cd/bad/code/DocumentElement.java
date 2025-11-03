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

    public DocumentElement(Element elem, String xPathString) {
        this.elem = elem;
        this.xPathString = xPathString;
    }

    String toJsonString() {
        jsonString = "";
        elementName = this.elem.getName();
        attributes = this.elem.attributes();
        String titleAttrContent = this.elem.attributeValue("title");
        String fileAttrContent = this.elem.attributeValue("file");
        if (elementName == "doc")
        {
            for (Attribute attribute : attributes)
            {
                jsonString = jsonString.concat("{");
                String attrName = attribute.getName();
                //each one has to have "data" line, "attr" line "state" line and "children" line
                jsonString = jsonString.concat("'data':'").concat(titleAttrContent).concat("',");
                if (attrName.equals("key"))
                {
                    String keyContent = this.elem.attributeValue("key");
                    jsonString = jsonString.concat("'attr':{'id':'")
                            .concat(this.xPathString)
                            .concat("_dk:")
                            .concat(keyContent)
                            .concat("','file':'")
                            .concat(fileAttrContent)
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
                            .concat(fileAttrContent)
                            .concat("'}");

                    break;
                }
            }
            if (hasChildren(this.elem))
            {
                jsonString = jsonString.concat(",'state':'closed'");

            }
            jsonString = jsonString.concat("},");
        }

        else if (elementName == "folder")
        {
            jsonString = jsonString.concat("{");
            for (Attribute attribute : attributes)
            {
                String attrName = attribute.getName();
                jsonString = jsonString.concat("'data':'").concat(titleAttrContent).concat("',");
                if (attrName.equals("key"))
                {
                    String keyContent = this.elem.attributeValue("key");
                    jsonString = jsonString.concat("'attr':{'id':'")
                            .concat(this.xPathString)
                            .concat("_fk:")
                            .concat(keyContent)
                            .concat("'}");
                    if (fileAttrContent != null)
                    {
                        jsonString = jsonString.concat("','file':'")
                                .concat(fileAttrContent)
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

    private static boolean hasChildren(Element elem) {
        return !elem.elements()
                .isEmpty();
    }
}
