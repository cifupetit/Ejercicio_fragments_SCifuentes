package com.example.cifu.ejercicio_fragments_scifuentes.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cifu.ejercicio_fragments_scifuentes.Fragments.DescriptionFragment;
import com.example.cifu.ejercicio_fragments_scifuentes.Interfaces.OnItemSelectionChangeListener;
import com.example.cifu.ejercicio_fragments_scifuentes.R;

public class MainActivity extends AppCompatActivity implements OnItemSelectionChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.mainListFragment) != null) {
            if (savedInstanceState != null) {
                return;
            }

            ListFragment listFragment = new ListFragment();
            listFragment.setArguments(getIntent().getExtras());

            FragmentManager fragmentManager = this.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainListFragment, listFragment);
            fragmentTransaction.commit();
        }

    }

    @Override //comprueba si caben los 2 fragments o no y modifica el contenido del DescriptionFragment y sino cambia de fragment a este
    public void OnSelectionChanged(int itemIndex) {
        FragmentManager fragmentManager = getFragmentManager();
        DescriptionFragment descriptionFragment = (DescriptionFragment) fragmentManager.findFragmentById(R.id.mainDescriptionFragmentL);

        if (descriptionFragment != null) { //si no es nulo, es decir, que ya esta el fragment2 con contenido, solo modifica los datos de este
            descriptionFragment.setItemDescription(itemIndex);
        } else { //sino, ya que el fragment2 es nulo, hace un repleace del fragment para poner el de la descripción
            DescriptionFragment newDescriptionFragment = new DescriptionFragment();
            Bundle args = new Bundle();

            args.putInt(getResources().getString(R.string.clave_item_position), itemIndex);
            newDescriptionFragment.setArguments(args);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainListFragment, newDescriptionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
