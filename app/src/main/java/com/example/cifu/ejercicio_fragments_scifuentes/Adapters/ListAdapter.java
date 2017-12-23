package com.example.cifu.ejercicio_fragments_scifuentes.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cifu.ejercicio_fragments_scifuentes.Models.ItemModel;
import com.example.cifu.ejercicio_fragments_scifuentes.R;

import java.util.List;

/**
 * Created by cifu on 13/12/2017.
 */

public class ListAdapter extends BaseAdapter {
    private Activity activity;
    private List<ItemModel> data;
    private LayoutInflater inflater;
    private int itemLayout;

    public ListAdapter(Activity activity, List<ItemModel> data, int itemLayout) {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ItemModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //set custom layout
        if (convertView == null) {convertView = this.inflater.inflate(itemLayout, null);}
        //get views from custom layout
        ImageView itemImg = (ImageView) convertView.findViewById(R.id.imagen_producto);
        TextView itemNombre = (TextView) convertView.findViewById(R.id.nombre_producto);
        TextView itemStock = (TextView) convertView.findViewById(R.id.stock);
        Spinner itemCantidad = (Spinner) convertView.findViewById(R.id.spinner_cantidad);
        //set values for item at position
        itemImg.setImageResource(data.get(position).getImg());
        itemNombre.setText(data.get(position).getNombre());
        itemStock.setText(String.valueOf(data.get(position).getStock()));

        Integer[] stocks = new Integer[data.get(position).getStock()+1];
        //en vez de coger el stock por getResources, se coge por el tamaño de stock, ya que no deberiamos coger datos de Resources aquí
        for (int i = 0; i < stocks.length; i++) {
            stocks[i] = i;
        }
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<>(activity.getApplicationContext(), android.R.layout.simple_list_item_1, stocks);
        //adapter.add(stocks); //ya que el spinner solo contiene una cantidad de número esteros, es decír, un array de ints
        itemCantidad.setAdapter(adapter); //utilizo la clase ArrayAdapter que ya está implementada para añadirle el adater al spinner
        // con el array de ints correspondiente, con el formato de layout de lista simple 1, que me sirve sin problema
        itemCantidad.setSelection(adapter.getPosition(data.get(position).getCantidad()));
        itemCantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position2, long id) {
                int value = (Integer) parent.getItemAtPosition(position2);
                data.get(position).setCantidad(value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return convertView;
    }
}
