package com.cd.bad.code;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class FolderElement extends DocumentElement {

    public FolderElement(Element elem, String xPathString) {
        super(elem, xPathString);
    }

    @Override
    protected String getElemName() {
        return "folder";
    }

    @Override
    protected void processElement() {
        jsonString = jsonString.concat("{");
        processFolderAttributes();
        closeElement();
    }

    protected void processFolderAttributes() {
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
}
