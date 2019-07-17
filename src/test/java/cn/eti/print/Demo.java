package cn.eti.print;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2019-7-11
 */
public class Demo {
    public static void main(String[] args) throws IOException, DocumentException {

        InputStream inputStream = new FileInputStream("aaa.xml");
        String theString = IOUtils.toString(inputStream, "GBK");
        System.out.println(theString);
        List<Element> xmlFieldData = getXmlFieldData(theString);
        List list = getFiledsList(xmlFieldData, "fd_373c0c155d1ca8");


        //String fd_373c0c155d1ca8 = getXmlFieldData(theString, "fd_373c0c155d1ca8");//OA单号
//        //String fd_373c0bf271de26 = getXmlFieldData(theString, "fd_373c0bf271de26");//申请人
//        String fd_373c0c03939866 = getXmlFieldData(theString, "fd_373c0c03939866");//申请人岗位
//        String fd_373e4b4e6d2e24 = getXmlFieldData(theString, "fd_373e4b4e6d2e24");//年
//        String fd_373e4b4fa8dafc = getXmlFieldData(theString, "fd_373715a0fbf3f8");//月
//        //String fd_373c0c155d1ca8 = getXmlFieldData(theString, "fd_373c0c155d1ca8");
//        // System.out.println(fd_373c0c155d1ca8);
//        //System.out.println(fd_373c0bf271de26);
//        System.out.println(fd_373c0c03939866);
//        System.out.println(fd_373e4b4e6d2e24);
//        System.out.println(fd_373e4b4fa8dafc);


    }

    /**
     * get all void elements from a string xmlString converted by inputstream
     *
     * @param xmlStr
     * @return list contains all void elements
     * @throws DocumentException
     */
    public static List<Element> getXmlFieldData(String xmlStr)
            throws DocumentException {
        Document document = DocumentHelper.parseText(xmlStr);
        Element javaElement = document.getRootElement();
        String name = javaElement.getName();
        System.out.println(name);
        List<Element> objElements = javaElement.elements();
        return (List<Element>) objElements.get(0).elements();

    }

    private static boolean booleanNext(Element element) {
        return element.elements().size() > 0;
    }

    /**
     * check given list element type
     *
     * @param elements list
     * @return <p>if list element is org.dom4j.Element then it will return true</p>
     * <p>otherwise it return false</p>
     */
    private static boolean checkListElementType(List elements) {
        if (elements != null && elements.size() > 0) {
            for (Object element : elements) {
                if (!(element instanceof Element)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }



    private static List getFiledsList(List<Element> elementList, String field) {
        if (elementList == null || elementList.size() == 0 || field == null) {
            return null;
        }
        for (int i = 0; i < elementList.size(); i++) {
            Element element = elementList.get(i);
            List<Element> elements = element.elements();
            if (field.equals(elements.get(0).getText())) {
//                elements.get(1)
            }

            Class<?> aClass = elements.get(0).getClass();
            boolean boolena = elements.get(0) instanceof Element;
            System.out.println();


        }
        List<String> resultsList = new ArrayList<>();


//
//        ArrayList list = new ArrayList();
//        for (int i = 0; i < elementList.size(); i++) {
//
//            //当前节点中含有那个字段
//            if (elementList.get(i).getText().equals(field)) {
//                List attributes = elementList.get(i + 1).attributes();
//                if (attributes.size() > 0) {
//                    if (attributes.get(0).equals("java.util.ArrayList")) {
//                        List elements1 = elementList.get(i + 1).elements();
//                        if (elements1.size() > 0 && elements1.get(0) instanceof Element) {
//                            Object o = elements1.get(0);
//                            System.out.println();
//                        }
//                        for (int j = 0; j < elements1.size(); j++) {
////                            List<Element> elements2 = elements1.get(j).elements();
////                            List attributes1 = elements2.get(0).attributes();
////                            if (attributes1.size() > 0 && attributes1.get(0).equals("java.util.HashMap")) {
////                               // elements2.get(1).elements()
////                            }
//
//                        }
//                        //return depth(elements,field);
//                    } else if (attributes.get(0).equals("java.util.HashMap")) {
//                        return elementList.get(i + 1).elements();
//                    }
//                    List elements = elementList.get(i + 1).elements();
//
//                } else {
//                    String text = elementList.get(i + 1).getText();
//                    list.add(text);
//                }
//            } else if (elementList.get(i + 1).getName().equals("object")) {
//
//            }
//        }


        return resultsList;
    }


}
