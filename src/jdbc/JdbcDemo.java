package jdbc;

import base64.ImageToBase64;
import fileTest.FileToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDemo {

    //注意编码
    public static final String URL = "jdbc:mysql://localhost:3306/community?characterEncoding=utf-8&serverTimezone=GMT%2b8&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = ";P4oT9}h69";

    public static void main(String[] args) throws Exception {
        //mysqlToFile();

        System.out.println(System.getProperty("line.separator"));
    }

    public static void mysqlToFile() throws Exception {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        //st与rs是一一对应
        String sql1 = " SELECT id from probe  where type ='人脸识别' and community_id in\n" +
                " (\n" +
                " SELECT id from community where parent='05ffae6c-5d7e-4f02-b810-48e22fdd4adb'\n" +
                " )";
        Statement stmt1 = conn.createStatement();
        ResultSet rs1 = stmt1.executeQuery(sql1);
        while (rs1.next()) {
            String sql2 = "SELECT pro.property_value from entity_log " +
                    "join entity_property pro on pro.entity_id=entity_log.id " +
                    "where type ='人脸' and pro.property_key='base64' and " +
                    " probe_id ='" + rs1.getString("id") + "'";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql2);
            int i = 0;
            //如果有数据，rs.next()返回true
            while (rs2.next() && i < 500) {
                String entitySql = "select * from base64 where id ='" + rs2.getString("property_value") + "'";
                System.out.println(entitySql);
                Statement stmt3 = conn.createStatement();
                ResultSet rsEntity = stmt3.executeQuery(entitySql);
                while (rsEntity.next()) {
                    i++;
                    ImageToBase64.base64ToImage(rsEntity.getString("base64"), "/data/image/" + rsEntity.getString("id")+".jpg");
                }
            }
        }
    }

}
