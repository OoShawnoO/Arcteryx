package swu.edu.hzd;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class ReadExcel {


    public static void Read(String path) throws IOException, SQLException, ClassNotFoundException {
        FileInputStream fileInputStream;
        HSSFWorkbook hssfWorkbook;
        fileInputStream = new FileInputStream(path);
        hssfWorkbook =new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        SQLtool sqLtool = new SQLtool();
        ArrayList<Goods> arrayList;
        for(Row row:sheet){
            String Name = row.getCell(0).getStringCellValue();
            float price = (float) row.getCell(1).getNumericCellValue();
            float cost = (float) row.getCell(2).getNumericCellValue();
            String uploader = row.getCell(3).getStringCellValue();
            String intro = row.getCell(4).getStringCellValue();
            String imgsrc = row.getCell(5).getStringCellValue();
            arrayList = sqLtool.Select(Name.replace("'",""));
            int flag = -1;
            int index = 0;
            for (Goods goods : arrayList) {
                //System.out.println(arrayList.get(x).getName()+"：：：：：："+Name.replace("'",""));
                if (goods.getName().equals(Name.replace("'", ""))) {
                    if (goods.getCost() == cost && goods.getPrice() == price) {
                        flag = -2;
                    } else {
                        flag = 1;
                    }
                    break;

                }
            }
            if(flag == 1){
                update(arrayList.get(index).getId(),Name,price,cost,arrayList.get(index).getName(),arrayList.get(index).getPrice(),arrayList.get(index).getCost());
                //System.out.println("update------------->"+Name);
            }
            else if(flag == -1){
                add(Name,price,cost,uploader,intro,imgsrc);
            }
            else{
                System.out.println("跳过！");
            }
        }

    }

    public static void add(String name,float price,float cost,String uploader,String intro,String imgsrc){
        SQLtool sqLtool = new SQLtool();
        try {
            sqLtool.Insert("record",name,uploader,price,cost,intro,imgsrc);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void update(int id,String name,float price,float cost,String old_name,float old_price,float old_cost){
        SQLtool sqLtool = new SQLtool();
        try{
            sqLtool.Update(id,name,price,cost,old_name,"python-spider",old_price,old_cost);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}


