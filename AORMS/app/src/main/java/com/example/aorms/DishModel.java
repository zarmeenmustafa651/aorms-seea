
package com.example.aorms;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class DishModel implements Parcelable {
    String name;
    Drawable img;
    String type;
    int DishID;
    int DishCost;
    int PrepTime;
    boolean selected =false;
    int amount = 1;

    public DishModel(String n, String t){
        this.name = n;
        this.type = t;
    }

    public DishModel() {

    }

    public DishModel(Parcel in) {
        name = in.readString();
        DishID = in.readInt();
        DishCost = in.readInt();
        type = in.readString();
        PrepTime = in.readInt();
        this.amount = in.readInt();
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        selected = selected;
    }
    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        // TODO Auto-generated method stub
        out.writeString(name);
        out.writeInt(DishID);
        out.writeInt(DishCost);
        out.writeString(type);
        out.writeInt(PrepTime);
        out.writeInt(amount);

    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Creator <DishModel> CREATOR = new Creator<DishModel>() {
        public DishModel createFromParcel(Parcel in) {
            return new DishModel(in);
        }

        public DishModel[] newArray(int size) {
            return new DishModel[size];
        }
    };
}