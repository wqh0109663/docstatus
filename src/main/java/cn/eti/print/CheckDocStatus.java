package cn.eti.print;

import cn.eti.print.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author wqh
 * @date 2019-7-11
 */
@Slf4j
public class CheckDocStatus extends QuartzJobBean {

    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;


    public CheckDocStatus() {
        super();
    }

    @Override
    @Transactional
    protected void executeInternal(JobExecutionContext arg0) {
//        String templateSql = "select * from km_review_main where fd_template_id='16a0a0d89f5930578903eca436e9103f' and (doc_status  = '30' or doc_status = '20')";
//        List<Map<String, Object>> maps1 = jdbcTemplate1.queryForList(templateSql);
//        try {
//            for (Map<String, Object> m : maps1) {
//                String doc_status = (String) m.get("doc_status");
//                log.info("doc_status:----" + doc_status);
//                String fd_id = (String) m.get("fd_id");
//                log.info("fd_id---" + fd_id);
//                Set<String> res = redisUtil.smembers(fd_id + doc_status);
//                log.info(res.toString());
//                if (res.size()<=0) {
//                    log.info("in -----");
//                    // 如果redis中有这个值就不管
//                    //如果在redis中有这个值，而且状态不是一样的话就update，也走这个地方
//                    //没有就把值先存到oracle中，然后加到redis中
//
//                    if (doc_status.equals("30")) {
//                        //通过
//                        String passsql = "select * from ekp_16be562dfabd8867cded main ,ekp_16be562dfab95078953a se ,km_review_main re \n" +
//                                " where  se.fd_parent_id = main.fd_id and re.fd_id = main.fd_id and main.fd_id='" + fd_id + "'";
//                        List<Map<String, Object>> forList = jdbcTemplate1.queryForList(passsql);
//                        for (Map<String, Object> m1 : forList) {
//                            Object fd_zhaiYaoShuoMing = m1.get("fd_zhaiYaoShuoMing") == null ? "" : m1.get("fd_zhaiYaoShuoMing").toString();//摘要说明
//                            Object fd_shenQingRen1 = m1.get("fd_shenQingRen1");//申请人fdid
//                            Object fd_feiYongBaoXiaoRen3 = m1.get("fd_feiYongBaoXiaoRen3");//申请报销人
////                            Object fd_baoXiaoRenBenYu = m1.get("fd_baoXiaoRenBenYu");//预算金额
//                            Object fd_number = m1.get("fd_number");//OA单号
//                            String fd_gangWei = m1.get("fd_gangWei") == null ? "" : m1.get("fd_gangWei").toString();
//                            Object fd_shenQingDanJuBianHao = m1.get("fd_shenQingDanJuBianHao") == null ? "" : m1.get("fd_shenQingDanJuBianHao").toString();//danju
//                            Object fd_yuSuanNianFen3 = m1.get("fd_yuSuanNianFen3");
//                            Object fd_yuSuanYueFen3 = m1.get("fd_yuSuanYueFen3");
//                            Object fd_yuSuanXiangMu3 = m1.get("fd_yuSuanXiangMu3");//预算项目
//                            Object fd_baoXiaoRenBenYu = m1.get("fd_baoXiaoRenBenYu");//报销人本月实际报销
//                            String budget = fd_yuSuanNianFen3.toString() + fd_yuSuanYueFen3.toString(); //预算年月
//                            String sql = "select * from sys_org_element where fd_id='" + fd_shenQingRen1 + "'";
//                            List<Map<String, Object>> maps = jdbcTemplate1.queryForList(sql);
//                            Object o = "";
//                            if (maps.size() > 0) {
//                                o = maps.get(0).get("fd_name");
//                            }
//                            String sql1 = " insert into  tc_oat_file(tc_oat000,tc_oat001,tc_oat003,tc_oat004 ," +
//                                    "tc_oat005,tc_oat006,tc_oat007,tc_oat008,tc_oatud001,tc_oat010,tc_oat011,tc_oat009) values " +
//                                    "('1','" + UUID.randomUUID().toString() + System.currentTimeMillis() + "','" + o.toString() + "','" + fd_gangWei + "','" +
//                                    "" + fd_shenQingDanJuBianHao + "'," + fd_baoXiaoRenBenYu.toString() + ",'1','" + fd_zhaiYaoShuoMing + "','" + fd_yuSuanXiangMu3 + "'," + fd_yuSuanNianFen3.toString().substring(0, 4) + "," + fd_yuSuanYueFen3.toString().replace("月", "") + ",'" + fd_number + "')";
//                            log.info(sql1);
//                            jdbcTemplate2.update(sql1);
//                            redisUtil.sadd(fd_id + doc_status, fd_id + doc_status);
//                            redisUtil.persist(fd_id+doc_status);
//
//                        }
//
//
//                    } else if (doc_status.equals("20")) {
//                        String passsql = "select * from ekp_16be562dfabd8867cded main ,ekp_16be562dfab7ee4c07a3 se ,km_review_main re \n" +
//                                " where  se.fd_parent_id = main.fd_id and re.fd_id = main.fd_id and main.fd_id='" + fd_id + "'";
//                        List<Map<String, Object>> forList = jdbcTemplate1.queryForList(passsql);
//                        for (Map<String, Object> m1 : forList) {
//                            Object fd_yuSuanNianFen = m1.get("fd_yuSuanNianFen");
//                            Object fd_yuSuanYueFen = m1.get("fd_yuSuanYueFen");
//                            Object fd_gangWei = m1.get("fd_gangWei");//OA单号
////                            String yearAndMonth = fd_yuSuanNianFen.toString()+ fd_yuSuanYueFen.toString();//年月
//                            Object fd_yuSuanXiangMu = m1.get("fd_yuSuanXiangMu");//预算项目
//                            Object fd_yuSuanShenQingRen = m1.get("fd_yuSuanShenQingRen");//预算申请人
//                            Object fd_baoXiaoRenBenYueYu = m1.get("fd_baoXiaoRenBenYueYu");//报销本月预算金额
//                            Object fd_shenQingDanJuBianHao = m1.get("fd_shenQingDanJuBianHao");
//                            Object fd_number = m1.get("fd_number");//OA单号
//                            String sql = "select * from sys_org_element where fd_id='" + fd_yuSuanShenQingRen + "'";
//                            List<Map<String, Object>> maps = jdbcTemplate1.queryForList(sql);
//                            Object o = maps.get(0).get("fd_name");
//                            log.info(o.toString());
//
//                            String sql1 = " insert into  tc_oat_file(tc_oat000,tc_oat001,tc_oat003,tc_oat004 ," +
//                                    "tc_oat005,tc_oat006,tc_oat007,tc_oat008,tc_oatud001,tc_oat010,tc_oat011,tc_oat009) values " +
//                                    "('1','" + UUID.randomUUID().toString() + System.currentTimeMillis() + "','" + o.toString() + "','" + fd_gangWei + "','" +
//                                    "" + fd_shenQingDanJuBianHao + "'," + fd_baoXiaoRenBenYueYu.toString() + ",'1','" + "','" + fd_yuSuanXiangMu + "'," + fd_yuSuanNianFen.toString().substring(0, 4) + "," + fd_yuSuanYueFen.toString().replace("月", "") + ",'" + fd_number + "')";
//                            jdbcTemplate2.update(sql1);
//                            redisUtil.sadd(fd_id + doc_status, fd_id + doc_status);
//                            redisUtil.persist(fd_id + doc_status);
//                            log.info(sql1);
//
//                        }
//
//                    }
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
   }
}
