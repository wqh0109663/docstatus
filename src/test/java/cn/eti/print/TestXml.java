package cn.eti.print;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author wqh
 * @date 2019-7-15
 */
public class TestXml {

    /**
     * 解析extend_data_xml中的xml节点，根据指定的节点获得值
     *
     * @param xmlStr
     *            xml字符串
     * @param field
     *            节点id
     * @return 节点值
     * @throws DocumentException
     */
    public static String getXmlFieldData(String xmlStr, String field)
            throws DocumentException {

        Document document = DocumentHelper.parseText(xmlStr);

        Element javaelement = document.getRootElement();

        List<Element> objelements = javaelement.elements();

        List<Element> voidelement = objelements.get(0).elements();

        String elementval = "";

        for (Element voids : voidelement) {

            List<Element> strelement = voids.elements();

            if (strelement.get(0).getText().equals(field)) {
                elementval = strelement.get(1).getText();
                break;
            }
        }

        return elementval;
    }


    public static void main(String[] args) throws IOException, DocumentException {
        InputStream fileInputStream = new FileInputStream("aaa.xml");
        System.out.println(Charset.defaultCharset());
        System.out.println(System.getProperty("file.encoding"));
        StringWriter writer = new StringWriter();
        IOUtils.copy(fileInputStream, writer, "GBK");
        String theString = writer.toString();
        System.out.println(theString);
        String xmlFieldData = getXmlFieldData(theString, "");
        System.out.println();

    }


}
