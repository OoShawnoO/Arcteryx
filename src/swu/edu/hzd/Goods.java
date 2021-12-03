package swu.edu.hzd;

import java.util.ArrayList;
import java.util.ArrayList;

public class Goods {
    private String name;
    private float price;
    private float cost;
    private int id;
    private float profit;



    private String intro;

    public ArrayList<Float> OldPrice = new ArrayList<>();
    public ArrayList<Float>  OldCost = new ArrayList<>();
    public ArrayList<String> DateList = new ArrayList<>();

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    private String updatetime;

    public float getProfit() {
        return price-cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }



    private String uploader;

}
