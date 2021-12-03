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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReadExcel {


    public static void Read(String path) throws IOException {
        FileInputStream fileInputStream = null;
        HSSFWorkbook hssfWorkbook =null;
        fileInputStream = new FileInputStream(path);
        hssfWorkbook =new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        int i=0;
        for(Row row:sheet){
            if(i==0){i++;continue;}
            i++;
            String Name = row.getCell(0).getStringCellValue();
            float price = (float) row.getCell(1).getNumericCellValue();
            float cost = (float) row.getCell(2).getNumericCellValue();
            String uploader = row.getCell(3).getStringCellValue();
            String intro = row.getCell(4).getStringCellValue();
            add(Name,price,cost,uploader,intro);
        }

    }

    public static void add(String name,float price,float cost,String uploader,String intro){
        SQLtool sqLtool = new SQLtool();
        try {
            sqLtool.Insert("record",name,uploader,price,cost,intro);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


