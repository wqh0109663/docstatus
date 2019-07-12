package cn.eti.print;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
//import java.util.List;
import java.util.*;

/**
 * @author wqh
 * @date 2019-7-11
 */
public class CheckDocStatus extends QuartzJobBean {


    @Autowired
//	@Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

//	@Autowired
//	@Qualifier("secondaryJdbcTemplate")
//	protected JdbcTemplate jdbcTemplate2;


    public CheckDocStatus() {
        super();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {

        System.out.println("****************");
        System.out.println(new Date().toLocaleString());
        System.out.println("****************");

        String selectEkpString = "select s.fd_name,s.fd_id  as FDID,e.* from sys_org_element s ,ekp_16be562dfabd8867cded e where s.fd_id = e.fd_shenQingRen1";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.202:1521/topprod", "eti_sz01",
                    "Eti2019etI04");

            List<Map<String, Object>> list = jdbcTemplate1.queryForList(selectEkpString);
            for (Map<String, Object> map : list) {
                Object fd_shenQingDanJuBianHao = map.get("fd_shenQingDanJuBianHao");
                System.out.println(fd_shenQingDanJuBianHao);
                Object fd_baoXiaoRenBenYueYuS = map.get("fd_baoXiaoRenBenYueYuS");
                Object FDID = map.get("FDID");
                Random random = new Random(100);
                int rand = random.nextInt();
                System.out.println(FDID);
                String sql = "\n" +
                        "select fd_name from sys_org_element where fd_id = (select fd_parentid from sys_org_element where fd_id='" + FDID + "') ";
                List<Map<String, Object>> maps = jdbcTemplate1.queryForList(sql);
                Object o = maps.get(0).get("fd_name");
                System.out.println(o);
                String sql1 = " insert into  tc_oat_file(tc_oat000,tc_oat001,tc_oat003,tc_oat005,tc_oat007,tc_oatud001) values ('1','"+FDID+System.currentTimeMillis()+"','"+o+"','"+fd_shenQingDanJuBianHao+"','1','"+fd_baoXiaoRenBenYueYuS+"')";
                System.out.println(sql1);
                if (con != null) {

                    preparedStatement = con.prepareStatement(sql1);
                    //preparedStatement.setString(1,"1");
                    //preparedStatement.setObject(2,System.currentTimeMillis());
                    //preparedStatement.setObject(3,new Date());
                    //preparedStatement.setObject(4,o);
                    //preparedStatement.setObject(5,fd_shenQingDanJuBianHao);
                    // preparedStatement.setObject(6,'1');
                    //preparedStatement.setObject(7,fd_baoXiaoRenBenYueYuS);
                    preparedStatement.execute(sql1);


                } else {
                    System.out.println(con + "数据连接为空");
                }
//			String filed = "";
//			try {
//				filed = getXmlFieldData(xmlval, "fd_3743154ea0d3c8");
//				//OA单号
//				String fd_373c0c155d1ca8 = getXmlFieldData(xmlval,"fd_373c0c155d1ca8");


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


//			jdbcTemplate2.update(sql);

    }
//		DataSet dataSet = null;
//		DataSet dataSet2 = null;
//		ResultSet rs = null;
//		try {
////			dataSet = new DataSet("HRDB");
////			rs = dataSet.executeQuery(selectEkpString);
//			String xml = "";
//			while (rs.next()) {
//				xml = rs.getString("extend_data_xml");
//				String filed = getXmlFieldData(xml, "fd_3291c926b9940e");
//				String sql = "insert into insert into tc_oat_file(tc_oat000,tc_oat001,tc_oatud001) values (1,'"
//						+ System.currentTimeMillis() + "'," + filed + ")";
//				dataSet2 = new DataSet("ERPZSQ");
//				dataSet2.executeUpdate(sql);
//
//				// String htje = getXmlFieldData(xml, "fd_36c5222ad99932");
//				// dataParser.setFieldValue(model, "fd_371bbe525e4964", htje);
//			}
//
//		} catch (Exception e) {
//			throw new RuntimeException("数据库连接失败" + e);
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					throw new RuntimeException("关闭结果集失败" + e);
//				}
//			}
//			if (dataSet != null) {
//				dataSet.close();
//			}
//			if (dataSet2 != null) {
//				dataSet2.close();
//			}
//		}



    /**
     * 解析extend_data_xml中的xml节点，根据指定的节点获得值
     *
     * @param xmlStr xml字符串
     * @param field  节点id
     * @return 节点值
     * @throws DocumentException
     */
    public String getXmlFieldData(String xmlStr, String field)
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

}
