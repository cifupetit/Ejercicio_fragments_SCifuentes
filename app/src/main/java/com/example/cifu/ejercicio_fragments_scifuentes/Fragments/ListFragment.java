package com.example.cifu.ejercicio_fragments_scifuentes.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cifu.ejercicio_fragments_scifuentes.Adapters.ListAdapter;
import com.example.cifu.ejercicio_fragments_scifuentes.Interfaces.OnItemSelectionChangeListener;
import com.example.cifu.ejercicio_fragments_scifuentes.Models.ItemModel;
import com.example.cifu.ejercicio_fragments_scifuentes.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<ItemModel> data;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        createDataModel();

        ListView listView = this.getActivity().findViewById(R.id.list_view);

        ListAdapter listAdapter = new ListAdapter(this.getActivity(), data, R.layout.list_item);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        /*Toast.makeText(this.getActivity(),"Objeto "+String.valueOf(position+1),Toast.LENGTH_LONG).show();*/

        OnItemSelectionChangeListener listener = (OnItemSelectionChangeListener) getActivity();
        listener.OnSelectionChanged(position);
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
