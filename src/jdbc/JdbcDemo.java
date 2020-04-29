package jdbc;

import base64.ImageToBase64;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.Instant;
import java.util.UUID;

public class JdbcDemo {

    //注意编码
    public static final String URL = "jdbc:mysql://39.105.108.226:3306/county_garden?characterEncoding=utf-8&serverTimezone=GMT%2b8&useSSL=false";
    //    public static final String USER = "root";
//    public static final String PASSWORD = ";P4oT9}h69";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        File file = new File("/data/test6.log");
        //mysqlToFile();
        txt2String(file);

    }

    public static void insert2() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

    }

    public static void insert() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        String sql = "INSERT INTO `day_count` (id) VALUES(?)";

        Instant instant = Instant.now();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, instant.toString().substring(0, 10));
            ps.executeUpdate();
            //执行sql语句
            System.out.println("插入成功(*￣︶￣)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 1500; i++) {
            instant = instant.plusMillis(3600 * 1000 * 24);
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, instant.toString().substring(0, 10));
                ps.executeUpdate();
                //执行sql语句
                System.out.println("插入成功(*￣︶￣)");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        instant = Instant.now();
        for (int i = 0; i < 1500; i++) {
            instant = instant.plusMillis(-1 * 3600 * 1000 * 24);
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, instant.toString().substring(0, 10));
                ps.executeUpdate();
                //执行sql语句
                System.out.println("插入成功(*￣︶￣)");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


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
                    ImageToBase64.base64ToImage(rsEntity.getString("base64"), "/data/image/" + rsEntity.getString("id") + ".jpg");
                }
            }
        }
    }

    public static void txt2String(File file) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO `data` (id , start_time , end_time) VALUES(?,?,?)";
        StringBuilder result = new StringBuilder();
        try {
            InputStreamReader inp = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br = new BufferedReader(inp);
            String chapter = "";
            String s = null;
            Data data = new Data();
            while ((s = br.readLine()) != null) {
                s = s.trim();
                if (s.length() > 0) {
                    if (s.contains("start")) {
                        s = s.substring(s.length() - 24);
                        long ins = Instant.parse(s).toEpochMilli();

                        data.start = ins;
                    } else if (s.contains("end")) {
                        s = s.substring(s.length() - 24);
                        long ins = Instant.parse(s).toEpochMilli();
                        data.end = ins;
                    } else {
                        data.name = UUID.randomUUID().toString();
                            try {
                                PreparedStatement ps = conn.prepareStatement(sql);
                                ps.setString(1, data.name);
                                ps.setLong(2, data.start);
                                ps.setLong(3, data.end);

                                ps.executeUpdate();
                                //执行sql语句
                                System.out.println("插入成功(*￣︶￣)");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Data {
    long start;
    long end;
    String name;
}
