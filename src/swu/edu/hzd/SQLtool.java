package swu.edu.hzd;

import com.sun.source.tree.NewArrayTree;

import java.sql.*;
import java.util.ArrayList;

public class SQLtool {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/record?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";


    public static Statement Connect(){
        Connection conn = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            //System.out.println("链接数据库中...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //System.out.println("链接数据库成功！");
            //System.out.println("实例化对象中...");
            statement = conn.createStatement();
            return statement;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean Select_Users(String username,String password) throws SQLException {
        Statement statement;

        if((statement=Connect())!=null){
            String sql=String.format("Select password from users where username='%s'",username);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String ps = resultSet.getString("password");
                if(String.valueOf(ps).equals(String.valueOf(password))){
                    return true;
                }
            }
            statement.close();
        }
        return false;
    }


    public ArrayList<Goods> Select() throws SQLException {
        Statement statement;
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if((statement=Connect())!=null){
            String sql = "Select id,name,price,cost from record";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Goods goods = new Goods();
                goods.setCost(resultSet.getFloat("cost"));
                goods.setPrice(resultSet.getFloat("price"));
                goods.setName(resultSet.getString("name"));
                goods.setId(resultSet.getInt("id"));
                goodsList.add(goods);
            }
            resultSet.close();
            statement.close();
        }
        return goodsList;
    }

    public void Insert(String name,String uploader,float price,float cost) throws SQLException {
        Statement statement;
        if((statement = Connect())!=null){
            String sql = String.format("INSERT INTO record(name,uploader,price,cost) VALUES('%s','%s',%f,%f)",name,uploader,price,cost);
            //System.out.println(sql);
            statement.execute(sql);
        }
    }

    public void Delete(int id) throws SQLException {
        Statement statement;
        if((statement = Connect())!=null){
            String sql = String.format("delete from record where id=%d;",id);
            statement.execute(sql);
        }
    }

    public void Update(int id,String name,float price,float cost) throws SQLException {
        Statement statement;
        if((statement = Connect())!=null){
            String sql = String.format("update record set name='%s',price=%f,cost=%f where id=%d",name,price,cost,id);
            statement.execute(sql);
        }

    }

}

