package swu.edu.hzd;

import com.sun.source.tree.NewArrayTree;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public Goods Select_Update(String goodsname) throws SQLException {
        Statement statement;
        Goods goods = new Goods();
        goods.setName(goodsname);
        if((statement=Connect())!=null){
            String sql= String.format("select * from update_history");
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                if(rs.getString("old_name").equals(goodsname)){
                    goods.OldPrice.add(rs.getFloat("old_price"));
                    goods.OldCost.add(rs.getFloat("old_cost"));
                    goods.DateList.add(rs.getString("datetime"));


                }
                if(rs.isLast()){
                    goods.OldCost.add(rs.getFloat("new_cost"));
                    goods.OldPrice.add(rs.getFloat("new_price"));
                }
            }
            rs.close();
            statement.close();
        }
        return goods;
    }


    public ArrayList<Goods> Select() throws SQLException {
        Statement statement;
        ArrayList<Goods> goodsList = new ArrayList<>();
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

    public void Insert(String tablename,String name,String user,float price,float cost) throws SQLException {
        Statement statement;
        if((statement = Connect())!=null){
            String sql=";";
            if(tablename.equals("delete_history")){
                sql = String.format("INSERT INTO %s(name,deleter,price,cost) VALUES('%s','%s',%f,%f)",tablename,name,user,price,cost);
            }
            if(tablename.equals("record")){
                sql = String.format("INSERT INTO %s(name,uploader,price,cost) VALUES('%s','%s',%f,%f)",tablename,name,user,price,cost);
            }

            System.out.println(sql);
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

    public void Update(int id,String new_name,float new_price,float new_cost,String old_name,String updater,float old_price,float old_cost) throws SQLException {
        Statement statement;
        if((statement = Connect())!=null){
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String sql1 = String.format("insert into update_history(new_name,updater,new_price,new_cost,old_name,old_price,old_cost,datetime) values('%s','%s','%f','%f','%s','%f','%f','%s')",new_name,updater,new_price,new_cost,old_name,old_price,old_cost,format.format(date));
            statement.execute(sql1);
            String sql = String.format("update record set name='%s',price=%f,cost=%f where id=%d",new_name,new_price,new_cost,id);
            statement.execute(sql);
            statement.close();
        }

    }

}

