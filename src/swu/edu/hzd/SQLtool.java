package swu.edu.hzd;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static swu.edu.hzd.ConnectPool.Connect;

public class SQLtool {

    public static Connection Connect() throws SQLException, ClassNotFoundException {
        return ConnectPool.Connect();
    }

    public static Connection  Connect_pre() throws ClassNotFoundException, SQLException {
        return ConnectPool.Connect();
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

    public boolean Select_Users(String username,String password) throws SQLException, ClassNotFoundException {
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

    public Goods Select_Update(String goodsname) throws SQLException, ClassNotFoundException {
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

    public ArrayList<Goods> Select(String search) throws SQLException, ClassNotFoundException {
        Statement statement;
        Connection conn;
        ArrayList<Goods> goodsList = new ArrayList<>();
        if((conn=Connect())!=null){
            statement = conn.createStatement();
            String sql = "Select * from record where name like '%"+search+"%'";
            ResultSet rs = statement.executeQuery(sql);
            goodsList = Distribute(rs);
            statement.close();
            conn.close();
        }
        return goodsList;
    }

    public void Insert(String tablename,String name,String user,float price,float cost,String intro,String imgsrc) throws SQLException, ClassNotFoundException {
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

    public void Delete(int id) throws SQLException, ClassNotFoundException {
        Statement statement;
        Connection conn;
        if((conn = Connect())!=null){
            statement = conn.createStatement();
            String sql = String.format("delete from record where id=%d;",id);
            statement.execute(sql);
            statement.close();
        }
    }

    public void Update(int id,String new_name,float new_price,float new_cost,String old_name,String updater,float old_price,float old_cost) throws SQLException, ClassNotFoundException {
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

    public ArrayList<Goods> PrepareSelect(int page,String search) throws SQLException, ClassNotFoundException {
        int s = page*7 -7;
        ArrayList<Goods> goodsList;
        Connection conn = Connect();
        if(conn!=null) {
            try {

                String sql = "select * from record where name like '%"+search+"%' limit ?,7";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, s);
                ResultSet resultSet = ps.executeQuery();
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
        if((conn=ConnectPool.Connect())!=null){
            Statement statement = conn.createStatement();
            String sql = "select * from record limit "+limit;
            ResultSet rs = statement.executeQuery(sql);
            arrayList = Distribute(rs);
            statement.close();
            conn.close();
        }
        return arrayList;
    }

    public ArrayList<Goods> ExecuteSQL(String sql) throws SQLException, ClassNotFoundException {
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

