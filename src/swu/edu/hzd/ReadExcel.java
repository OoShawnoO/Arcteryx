package swu.edu.hzd;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.output.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReadExcel {


    public static void Read(String path) throws IOException, SQLException, ClassNotFoundException {
        FileInputStream fileInputStream = null;
        HSSFWorkbook hssfWorkbook =null;
        fileInputStream = new FileInputStream(path);
        hssfWorkbook =new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        SQLtool sqLtool = new SQLtool();
        ArrayList<Goods> arrayList = new ArrayList<>();
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
            for(int x=0;x<arrayList.size();x++){
                //System.out.println(arrayList.get(x).getName()+"：：：：：："+Name.replace("'",""));
                if(arrayList.get(x).getName().equals(Name.replace("'","")))
                {
                    if(arrayList.get(x).getCost()==cost&&arrayList.get(x).getPrice()==price){
                        flag = -2;
                        break;
                    }
                    else{
                        flag= 1;
                        break;
                    }

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void update(int id,String name,float price,float cost,String old_name,float old_price,float old_cost){
        SQLtool sqLtool = new SQLtool();
        try{
            sqLtool.Update(id,name,price,cost,old_name,"python-spider",old_price,old_cost);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


