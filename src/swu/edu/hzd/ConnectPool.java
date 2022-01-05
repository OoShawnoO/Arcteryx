package swu.edu.hzd;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectPool{
    private static DruidDataSource dataSource1;
    static String OS;
    static {
            Properties proper = System.getProperties();
            OS = proper.getProperty("os.name");

            try{
                if(OS.equals("Linux")){
                    dataSource1 = new DruidDataSource();
                    dataSource1.setUrl("jdbc:mariadb://localhost:3306/record");
                    dataSource1.setDriverClassName("org.mariadb.jdbc.Driver");
                    dataSource1.setUsername("administrator");
                    dataSource1.setPassword("very_strong_password");
                    dataSource1.setInitialSize(5);
                    dataSource1.setMaxActive(10);
                    dataSource1.setMaxWait(3000);
                    dataSource1.setMinIdle(3);
                    dataSource1.setTestWhileIdle(true);
                    dataSource1.setTestOnBorrow(true);
                    dataSource1.setTestOnReturn(true);
                }
                else{
                    dataSource1 = new DruidDataSource();
                    dataSource1.setUrl("jdbc:mysql://localhost:3306/record?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
                    dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
                    dataSource1.setUsername("root");
                    dataSource1.setPassword("123456");
                    dataSource1.setInitialSize(5);
                    dataSource1.setMaxActive(10);
                    dataSource1.setMaxWait(3000);
                    dataSource1.setMinIdle(3);
                    dataSource1.setTestWhileIdle(true);
                    dataSource1.setTestOnBorrow(true);
                    dataSource1.setTestOnReturn(true);
                }


            } catch (Exception e) {

            }
    }

    public static Connection Connect() throws SQLException {
        return dataSource1.getConnection();
    }

    public static void close(Object o, Statement statement, Connection connection){
        close(null,statement,connection);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getDataSource(){
        return dataSource1;
    }

}
