package com.cd.bad.code;

import org.approvaltests.Approvals;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLToJsonTest {

    @Test
    void shouldTranslateEmptyXMLToJson() throws Exception {
        XMLToJson translate = new XMLToJson();

        URL url = new URL("file:./src/test/resources/toc.xml");
        String xPathString = "fk:AMM24_fk:AMM24-00-00_fk:AMM24-00-00-02";

        Approvals.verify(translate.getJson(url, xPathString));
    }

    @Test
    @DisplayName("should return an empty json when folder is empty")
    void shouldReturnAnEmptyJsonWhenDocumentIsEmpty() throws Exception {

        //test fixture
        Document document = createDocument("<folder></folder>");

        //given
        XMLToJson translate = new XMLToJson();

        //when
        String jsonForDocument = translate.getJsonForDocument("/", document);

        //then
        assertEquals("[]", jsonForDocument);
    }

    @Test
    @DisplayName("should parse simple doc element")
    @Disabled
    void shouldParseSimpleDocElement() throws Exception {

        //test fixture
        Document document = createDocument("<folder><doc title=\"Hello World\"/></folder>");

        //given
        XMLToJson translate = new XMLToJson();

        //when
        String jsonForDocument = translate.getJsonForDocument("/", document);

        //then
        assertEquals("[{'data':'Hello World'}]", jsonForDocument);
    }

    private Document createDocument(String docAsString) throws DocumentException {
        return DocumentHelper.parseText(docAsString);
    }
}
