package com.example.practicalista;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.practicalista.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private Button btn_add;
    private EditText txt_1;
    private ListView ls_view1;
    private List<String> arrayList;
    private int pos;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        txt_1 = (EditText) binding.text1;
        btn_add = (Button) binding.btnAdd;
        ls_view1 = (ListView) binding.listView1;

        arrayList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, arrayList);
        ls_view1.setAdapter(adapter);
        ls_view1.setBackgroundColor(Color.DKGRAY);


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.add(txt_1.getText().toString());
                adapter.notifyDataSetChanged();
                Toast toast1 = Toast.makeText(getContext(), "Agregado", Toast.LENGTH_SHORT);
                toast1.show();

            }
        });


        binding.listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;

                new AlertDialog.Builder(getContext()).setIcon(android.R.drawable.ic_delete).setTitle("¿Estás seguro?").setMessage("¿Desear eliminar este dato?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.remove(which_item);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("No", null).show();

                return false;
            }
        });

        binding.listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }
        });

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String dato = txt_1.getText().toString();
                arrayList.set(pos, txt_1.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}