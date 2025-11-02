package com.cd.bad.code;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;

public class DocumentElement {

    private final Element elem;
    private final String xPathString;

    public DocumentElement(Element elem, String xPathString) {
        this.elem = elem;
        this.xPathString = xPathString;
    }

    String toJsonString(Element elem) {
        String jsonString = "";
        String eleName = elem.getName();
        List<Attribute> list = elem.attributes();
        String titleAttrContent = elem.attributeValue("title");
        String fileAttrContent = elem.attributeValue("file");
        if (eleName == "doc")
        {
            for (Attribute attribute : list)
            {
                jsonString = jsonString.concat("{");
                String attrName = attribute.getName();
                //each one has to have "data" line, "attr" line "state" line and "children" line
                jsonString = jsonString.concat("'data':'").concat(titleAttrContent).concat("',");
                if (attrName.equals("key"))
                {
                    String keyContent = elem.attributeValue("key");
                    jsonString = jsonString.concat("'attr':{'id':'").concat(this.xPathString).concat("_dk:").concat(keyContent).concat("','file':'").concat(fileAttrContent).concat("'}");

                    break;
                }
                else if (attrName.equals("trnum"))
                {

                    String trnumContent = elem.attributeValue("trnum");
                    jsonString = jsonString.concat("'attr':{'id':'").concat(this.xPathString).concat("_dtrn:").concat(trnumContent).concat("','file':'").concat(fileAttrContent).concat("'}");

                    break;
                }
            }
            if (hasChildren(elem))
            {
                jsonString = jsonString.concat(",'state':'closed'");

            }
            jsonString = jsonString.concat("},");
        }

        else if (eleName == "folder")
        {
            jsonString = jsonString.concat("{");
            for (Attribute attribute : list)
            {
                String attrName = attribute.getName();
                jsonString = jsonString.concat("'data':'").concat(titleAttrContent).concat("',");
                if (attrName.equals("key"))
                {
                    String keyContent = elem.attributeValue("key");
                    jsonString = jsonString.concat("'attr':{'id':'").concat(this.xPathString).concat("_fk:").concat(keyContent).concat("'}");
                    if (fileAttrContent != null)
                    {
                        jsonString = jsonString.concat("','file':'").concat(fileAttrContent).concat("'}");
                    }

                    break;
                }
                else if (attrName.equals("type"))
                {
                    String typeContent = elem.attributeValue("type");
                    if (typeContent == "history")
                    {
                        jsonString = jsonString.concat("'attr':{'id':'").concat(this.xPathString).concat("_fth,");

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
