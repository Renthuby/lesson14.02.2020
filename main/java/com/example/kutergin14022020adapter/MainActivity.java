package com.example.kutergin14022020adapter;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    EditText editText;
    final String [] countries = {"Россия","Фрация","Япония","США","Германия","Италия","Егор"};
    LinkedList<String> mylist = new LinkedList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> selectedCountry = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.edittext);
        mylist.addAll(Arrays.asList(countries));
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice, mylist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*SparseBooleanArray sp = listView.getCheckedItemPositions();
                String selectedItems = "";
                for (int i = 0; i < mylist.size(); i++) {
                    if (sp.get(i)) {
                        selectedItems += mylist.get(i) + " ";
                    }
                }
                textView.setText(selectedItems);*/

                String itemcountr = adapter.getItem(position);
                if(listView.isItemChecked(position)){
                    selectedCountry.add(itemcountr);
                } else {
                    selectedCountry.remove(itemcountr);
                }


            }
        });
    }

    public void MyClickAdd(View view) {
        try {
            String country = editText.getText().toString();
            if(!country.isEmpty() && !mylist.contains(country)) {
                adapter.add(country);
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Пустое поле ввода", Toast.LENGTH_SHORT).show();
        }
    }

    public void MyClickDel(View view) {

        for (int i = 0; i < selectedCountry.size(); i++) {
            adapter.remove(selectedCountry.get(i));
        }

        listView.clearChoices();
        selectedCountry.clear();
        adapter.notifyDataSetChanged();
    }
}
