package com.cd.bad.code;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class DocElement extends DocumentElement {
    public DocElement(Element elem, String xPathString) {
        super(elem, xPathString);
    }

    @Override
    protected String getElemName() {
        return "doc";
    }

    @Override
    protected void processElement() {
        processDocAttributes();
        if (hasChildren(this.elem)) {
            addStateClosed();
        }
        closeElement();
    }

    protected void processDocAttributes() {

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
}
