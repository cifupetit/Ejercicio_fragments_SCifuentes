package com.example.cifu.ejercicio_fragments_scifuentes.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cifu.ejercicio_fragments_scifuentes.Models.ItemModel;
import com.example.cifu.ejercicio_fragments_scifuentes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {

    private int currentPosition;
    private List<ItemModel> data;

    private ImageView itemImg;
    private TextView itemNombre;
    private TextView itemStock;
    private TextView itemDesc;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createDataModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(getResources().getString(R.string.clave_item_position));
        }

        View view =  inflater.inflate(R.layout.fragment_description, container, false);

        itemImg = view.findViewById(R.id.img_desc);
        itemNombre = view.findViewById(R.id.nombre_desc);
        itemStock = view.findViewById(R.id.stock_desc);
        itemDesc = view.findViewById(R.id.desc_desc);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            setItemDescription(args.getInt(getResources().getString(R.string.clave_item_position)));
        } else if (currentPosition != -1) {
            setItemDescription(currentPosition);
        }
    }

    public void setItemDescription (int itemIndex) {
        ItemModel item = data.get(itemIndex);

        itemImg.setImageResource(item.getImg());
        itemNombre.setText(item.getNombre());
        itemStock.setText(String.valueOf(item.getStock()));
        itemDesc.setText(getResources().getString(R.string.descTest, item.getNombre().toLowerCase()));
        currentPosition = itemIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(getResources().getString(R.string.clave_item_position), currentPosition);
    }

    private void createDataModel() {
        data = new ArrayList<ItemModel>();
        String[] nombres = this.getResources().getStringArray(R.array.nombres_productos);
        int[] stocks = this.getResources().getIntArray(R.array.stocks_productos);

        ItemModel item;
        for (int i = 0; i < nombres.length; i++) {
            item = new ItemModel(this.getResources().getIdentifier(getResources().getString(R.string.nombre_imagen, nombres[i].toLowerCase()), getResources().getString(R.string.drawable), this.getActivity().getPackageName()),
                    nombres[i],
                    stocks[i],
                    0);
            data.add(item);
        }
    }
}
