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
        String theString = IOUtils.toString(inputStream,"GBK");
        System.out.println(theString);
        getXmlFieldData(theString,"fd_373bc8ca062a22");

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
     * 解析extend_data_xml中的xml节点，根据指定的节点获得值
     *
     * @param xmlStr xml字符串
     * @param field  节点id
     * @return 节点值
     * @throws DocumentException
     */
    public static List getXmlFieldData(String xmlStr, String field)
            throws DocumentException {
        List<String> list = new ArrayList<>();
        Document document = DocumentHelper.parseText(xmlStr);

        Element javaelement = document.getRootElement();
        String name = javaelement.getName();
        System.out.println(name);
        List<Element> objelements = javaelement.elements();
        List<Element> voidelement = objelements.get(0).elements();
        String elementval = "";
        for (Element voids : voidelement) {

            List<Element> strelement = voids.elements();

            depth(strelement,field);
        }
        return list;
    }

    private static boolean booleanNext(Element element){
       return element.elements().size() > 0;
    }

    private static List depth(List<Element> elementList,String field) {
        if (elementList.size() == 0) {
            return null;
        }
        ArrayList list = new ArrayList();
        for (int i = 0; i < elementList.size(); i++) {

            //当前节点中含有那个字段
            if (elementList.get(i).getText().equals(field)) {
                List attributes = elementList.get(i + 1).attributes();
                if (attributes.size() > 0) {
                    if (attributes.get(0).equals("java.util.ArrayList")) {
                        List elements1 = elementList.get(i+1).elements();
                        if (elements1.size() > 0 && elements1.get(0) instanceof Element) {
                            Object o = elements1.get(0);
                            System.out.println();
                        }
                        for (int j = 0; j < elements1.size(); j++) {
//                            List<Element> elements2 = elements1.get(j).elements();
//                            List attributes1 = elements2.get(0).attributes();
//                            if (attributes1.size() > 0 && attributes1.get(0).equals("java.util.HashMap")) {
//                               // elements2.get(1).elements()
//                            }

                        }
                        //return depth(elements,field);
                    } else if (attributes.get(0).equals("java.util.HashMap")) {
                            return elementList.get(i+1).elements();
                    }
                    List elements = elementList.get(i + 1).elements();
                    return depth(elements, field);
                } else {
                    String text = elementList.get(i + 1).getText();
                    list.add(text);
                }
            } else if (elementList.get(i+1).getName().equals("object")){

            }
        }


        return list;
    }


}
