package com.cd.bad.code;

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
}
