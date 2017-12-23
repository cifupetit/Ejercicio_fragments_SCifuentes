package com.example.cifu.ejercicio_fragments_scifuentes.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cifu on 13/12/2017.
 */

public class ItemModel implements Parcelable{
    private int img;
    private String nombre;
    private int stock, cantidad;

    public ItemModel(int img, String nombre, int stock, int cantidad) {
        this.img = img;
        this.nombre = nombre;
        this.stock = stock;
        this.cantidad = cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getImg() {
        return img;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.img);
        dest.writeString(this.nombre);
        dest.writeInt(this.stock);
        dest.writeInt(this.cantidad);
    }

    public static final Parcelable.Creator<ItemModel> CREATOR = new Parcelable.Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel source) {
            return new ItemModel(source);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    private ItemModel (Parcel source) {
        this.img = source.readInt();
        this.nombre = source.readString();
        this.stock = source.readInt();
        this.cantidad = source.readInt();
    }
}
