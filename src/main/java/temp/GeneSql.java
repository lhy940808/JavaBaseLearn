package temp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GeneSql {

    static String numberContent = "[{\\\"unit\\\":\\\"个\\\",\\\"num\\\":1,\\\"desc\\\":\\\"号码数量\\\"}]";
    static String callContentHubei = "[{\\\"unit\\\":\\\"分钟\\\",\\\"num\\\":3000,\\\"desc\\\":\\\"通话\\\"}]";
    static String callContentNonHubei = "[{\\\"unit\\\":\\\"分钟\\\",\\\"num\\\":1000,\\\"desc\\\":\\\"通话\\\"}]";

    static String numberPattern = "INSERT INTO `tb_rights` (`app_id`, `org_id`, `type`, `product_id`, `rights_config_id`, `source_type`, `source_id`, `content`, `apply_exp_start_time`, `apply_exp_end_time`, `consume_exp_start_time`, `consume_exp_end_time`, `status`, `create_time`, `create_user_id`, `update_user_id`, `deleted`) VALUES (''generic'', ''{0}'', 1, 39, 9, 2, '''', ''{1}'', ''{5}'', ''2020-3-31 23:59:59'', ''1970-1-1 00:00:00'', ''1970-1-1 00:00:00'', 1, ''{2}'',  ''{3}:0'', ''{4}:0'', 0);";
    static String callPatternHubei = "INSERT INTO `tb_rights` (`app_id`, `org_id`, `type`, `product_id`, `rights_config_id`, `source_type`, `source_id`, `content`, `apply_exp_start_time`, `apply_exp_end_time`, `consume_exp_start_time`, `consume_exp_end_time`, `status`, `create_time`, `create_user_id`, `update_user_id`, `deleted`) VALUES (''generic'', ''{0}'', 2, 40, 10, 2, '''', ''{1}'', ''{5}'', ''2020-3-31 23:59:59'', ''1970-1-1 00:00:00'', ''1970-1-1 00:00:00'', 1, ''{2}'',  ''{3}:0'', ''{4}:0'', 0);";
    static String callPatternNonHubei = "INSERT INTO `tb_rights` (`app_id`, `org_id`, `type`, `product_id`, `rights_config_id`, `source_type`, `source_id`, `content`, `apply_exp_start_time`, `apply_exp_end_time`, `consume_exp_start_time`, `consume_exp_end_time`, `status`, `create_time`, `create_user_id`, `update_user_id`, `deleted`) VALUES (''generic'', ''{0}'', 2, 41, 11, 2, '''', ''{1}'', ''{5}'', ''2020-3-31 23:59:59'', ''1970-1-1 00:00:00'', ''1970-1-1 00:00:00'', 1, ''{2}'',  ''{3}:0'', ''{4}:0'', 0);";

    // 文件路径，根据自己存放位置替换
    static String filePath = "/Users/liuhaiyan05/zhiban/gongdu/rights.csv";

    public static void main(String[] args) {
        // false:非湖北地区，ture:湖北地区
        doGene(false);
    }

    public static void doGene(Boolean isHuBei) {
        LocalDateTime date = LocalDateTime.now();  //获取当前时间
        String now = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); //进行对应的格式转换
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            String orgId;
            while ((orgId = reader.readLine()) != null) {
                System.out.println(MessageFormat.format(numberPattern, orgId.trim(), numberContent, now, orgId, orgId, now));
                if (isHuBei) {
                    System.out.println(MessageFormat.format(callPatternHubei, orgId.trim(), callContentHubei, now, orgId, orgId, now));
                } else {
                    System.out.println(MessageFormat.format(callPatternNonHubei, orgId.trim(), callContentNonHubei, now, orgId, orgId, now));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}