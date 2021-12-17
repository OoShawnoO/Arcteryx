package swu.edu.hzd;



import javax.xml.transform.Result;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SQLtool {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/record?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";


    public static Connection Connect() throws SQLException {
        Connection conn;
        try{
            Class.forName(JDBC_DRIVER);
            //System.out.println("链接数据库中...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //System.out.println("链接数据库成功！");
            //System.out.println("实例化对象中...");

            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection  Connect_pre(){
        Connection conn;
        try{
            Class.forName(JDBC_DRIVER);
            //System.out.println("链接数据库中...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //System.out.println("链接数据库成功！");
            //System.out.println("实例化对象中...");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Goods> Distribute(ResultSet rs) throws SQLException {
        ArrayList<Goods> arrayList = new ArrayList<>();
        while(rs.next())
        {
            Goods goods = new Goods();
            goods.setCost(rs.getFloat("cost"));
            goods.setPrice(rs.getFloat("price"));
            goods.setName(rs.getString("name"));
            goods.setId(rs.getInt("id"));
            goods.setIntro(rs.getString("intro"));
            goods.setImgsrc(rs.getString("imgsrc"));
            arrayList.add(goods);
        }
        rs.close();
        return arrayList;
    }

    public boolean Select_Users(String username,String password) throws SQLException {
        Connection conn;
        Statement statement;

        if((conn=Connect())!=null){
            statement = conn.createStatement();
            String sql=String.format("Select password from users where username='%s'",username);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String ps = resultSet.getString("password");
                if(String.valueOf(ps).equals(String.valueOf(password))){
                    return true;
                }
            }
            statement.close();
            conn.close();
        }
        return false;
    }

    public Goods Select_Update(String goodsname) throws SQLException {
        Statement statement;
        Connection conn;
        Goods goods = new Goods();
        goods.setName(goodsname);
        if((conn=Connect())!=null){
            statement = conn.createStatement();
            String sql= String.format("select * from update_history where new_name='%s'",goods.getName());
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                goods.OldPrice.add(rs.getFloat("old_price"));
                goods.OldCost.add(rs.getFloat("old_cost"));
                goods.DateList.add(rs.getString("datetime"));
                if(rs.isLast()){
                    goods.OldCost.add(rs.getFloat("new_cost"));
                    goods.OldPrice.add(rs.getFloat("new_price"));
                }
            }
            rs.close();
            statement.close();
            conn.close();
        }
        return goods;
    }

    public ArrayList<Goods> Select(String search) throws SQLException {
        Statement statement;
        Connection conn;
        ArrayList<Goods> goodsList = new ArrayList<>();
        if((conn=Connect())!=null){
            statement = conn.createStatement();
            String sql = "Select id,name,price,cost from record where name like '%"+search+"%'";
            ResultSet rs = statement.executeQuery(sql);
//            while(rs.next())
//            {
//                Goods goods = new Goods();
//                goods.setCost(rs.getFloat("cost"));
//                goods.setPrice(rs.getFloat("price"));
//                goods.setName(rs.getString("name"));
//                goods.setId(rs.getInt("id"));
//                goods.setImgsrc(rs.getString("imgsrc"));
//                goodsList.add(goods);
//            }
//            rs.close();
            goodsList = Distribute(rs);
            statement.close();
            conn.close();
        }
        return goodsList;
    }

    public void Insert(String tablename,String name,String user,float price,float cost,String intro,String imgsrc) throws SQLException {
        Statement statement;
        Connection conn;
        if((conn = Connect())!=null){
            statement = conn.createStatement();
            String sql="";
            if(tablename.equals("delete_history")){
                sql = String.format("INSERT INTO %s(name,deleter,price,cost) VALUES('%s','%s',%f,%f)",tablename,name,user,price,cost);
            }
            if(tablename.equals("record")){
                sql = String.format("INSERT INTO %s(name,uploader,price,cost,intro,imgsrc) VALUES('%s','%s',%f,%f,'%s','%s')",tablename,name.replace("'",""),user,price,cost,intro.replace("'",""),imgsrc.replace("'",""));
            }

            //System.out.println(sql);
            statement.execute(sql);
            statement.close();
            conn.close();
        }

    }

    public void Delete(int id) throws SQLException {
        Statement statement;
        Connection conn;
        if((conn = Connect())!=null){
            statement = conn.createStatement();
            String sql = String.format("delete from record where id=%d;",id);
            statement.execute(sql);
            statement.close();
        }
    }

    public void Update(int id,String new_name,float new_price,float new_cost,String old_name,String updater,float old_price,float old_cost) throws SQLException {
        Statement statement;
        Connection conn;
        if((conn = Connect())!=null){
            statement = conn.createStatement();
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if(old_cost!=new_cost||old_price!=new_price) {
                String sql1 = String.format("insert into update_history(new_name,updater,new_price,new_cost,old_name,old_price,old_cost,datetime) values('%s','%s','%f','%f','%s','%f','%f','%s')", new_name.replace("'",""), updater, new_price, new_cost, old_name.replace("'",""), old_price, old_cost, format.format(date));
                statement.execute(sql1);
            }
            String sql = String.format("update record set name='%s',price=%f,cost=%f where id=%d",new_name.replace("'",""),new_price,new_cost,id);
            statement.execute(sql);
            statement.close();
            conn.close();
        }

    }

    public ArrayList<Goods> PrepareSelect(int page,String search){
        int s = page*7 -7;
        ArrayList<Goods> goodsList = new ArrayList<>();
        Connection conn = Connect_pre();
        if(conn!=null) {
            try {

                String sql = "select * from record where name like '%"+search+"%' limit ?,7";
                PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setString(1,search);
                ps.setInt(1, s);
                ResultSet resultSet = ps.executeQuery();
//                while (resultSet.next()) {
//                    Goods goods = new Goods();
//                    goods.setCost(resultSet.getFloat("cost"));
//                    goods.setPrice(resultSet.getFloat("price"));
//                    goods.setName(resultSet.getString("name"));
//                    goods.setId(resultSet.getInt("id"));
//                    goods.setIntro(resultSet.getString("intro"));
//                    goods.setImgsrc(resultSet.getString("imgsrc"));
//                    goodsList.add(goods);
//                }
//                resultSet.close();
                goodsList = Distribute(resultSet);
                ps.close();
                conn.close();
                return goodsList;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Goods> LimitSelect(int limit) throws SQLException {
        ArrayList<Goods> arrayList = new ArrayList<>();
        Connection conn;
        if((conn=Connect())!=null){
            Statement statement = conn.createStatement();
            String sql = "select * from record limit "+limit;
            ResultSet rs = statement.executeQuery(sql);
            arrayList = Distribute(rs);
            statement.close();
            conn.close();
        }
        return arrayList;
    }

    public ArrayList<Goods> ExecuteSQL(String sql) throws SQLException {
        ArrayList<Goods> arrayList = new ArrayList<>();
        Connection conn;
        if((conn=Connect())!=null){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            arrayList = Distribute(rs);
            statement.close();
            conn.close();
        }
        return arrayList;
    }

}

