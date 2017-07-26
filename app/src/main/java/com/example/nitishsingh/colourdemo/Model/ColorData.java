package com.example.nitishsingh.colourdemo.Model;

/**
 * Created by Nitish Singh on 26-07-2017.
 */

public class ColorData {
    String colorName;
    String id;
    String colorCode;


    //In here i'm generating getter and setter

    public String getColorName(){
        return colorName;
    }

    public void setColorName(String ColorName) {
        this.colorName = colorName;

    }
    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode= colorCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
