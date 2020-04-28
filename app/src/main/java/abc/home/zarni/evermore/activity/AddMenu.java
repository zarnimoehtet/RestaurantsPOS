package abc.home.zarni.evermore.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import abc.home.zarni.evermore.R;
import abc.home.zarni.evermore.adapter.Coffee_Adapter;
import abc.home.zarni.evermore.adapter.Drink_Adapter;
import abc.home.zarni.evermore.adapter.Food_Adapter;
import abc.home.zarni.evermore.adapter.Grill_Adapter;
import abc.home.zarni.evermore.adapter.Salad_Adapter;
import abc.home.zarni.evermore.adapter.Soup_Adapter;
import abc.home.zarni.evermore.database.DatabaseHelper;
import abc.home.zarni.evermore.database.model.Coffee;
import abc.home.zarni.evermore.database.model.Drink;
import abc.home.zarni.evermore.database.model.Food;
import abc.home.zarni.evermore.database.model.Grill;
import abc.home.zarni.evermore.database.model.Salad;
import abc.home.zarni.evermore.database.model.Soup;
import abc.home.zarni.evermore.utils.RecyclerTouchListener;
import abc.home.zarni.evermore.utils.Utils;
import me.myatminsoe.mdetect.MDetect;

public class AddMenu extends AppCompatActivity {

    private DatabaseHelper db;
    private List<Coffee> coffeeList = new ArrayList<>();
    private List<Drink> drinkList = new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();
    private List<Soup> soupList = new ArrayList<>();
    private List<Salad> saladList = new ArrayList<>();
    private List<Grill> grillList = new ArrayList<>();

    Coffee_Adapter mAdapter;
    Drink_Adapter DAdapter;
    Food_Adapter FAdapter;
    Soup_Adapter SoAdatper;
    Salad_Adapter SaAdapter;
    Grill_Adapter GAdapter;

    CardView add_dialog,body;
    ImageView dialog_close;
    TextView textView,dialog_title,table_name,table_type,table_price;
    RecyclerView recyclerView;
    Button btn_add,dialog_add_btn;
    EditText name_edt,type_edt,price_edt;
    int cat_pos ;
    boolean edit = false;
    int pos = 0;
    Spinner sp;
    String[] menu = new String[]{MDetect.INSTANCE.getText("ကော်ဖီ"),MDetect.INSTANCE.getText("အအေး"),MDetect.INSTANCE.getText("စားစရာ"),
            MDetect.INSTANCE.getText("ဟင်းချို"),MDetect.INSTANCE.getText("အသုပ်"),MDetect.INSTANCE.getText("အကင်")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        getSupportActionBar().setTitle(MDetect.INSTANCE.getText("မီနူးပြင်မယ်"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);

        textView = findViewById(R.id.text);

        textView.setText(MDetect.INSTANCE.getText("ပြင်မယ့်မီနူးရွေးပါ"));

        coffeeList.addAll(db.getAllCoffee());


       ReloadList();


        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) mLayoutManager).setReverseLayout(true);
        ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new Coffee_Adapter(this,coffeeList);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position,cat_pos);
            }
        }));

        table_name = findViewById(R.id.table_name);
        table_type = findViewById(R.id.table_type);
        table_price = findViewById(R.id.table_price);

        table_name.setTypeface(Utils.getMMTypeface(this));
        table_type.setTypeface(Utils.getMMTypeface(this));
        table_price.setTypeface(Utils.getMMTypeface(this));

        table_name.setText("အမည်");
        table_type.setText("အမျိုးအစား");
        table_price.setText("စျေးနှုန်း");

        sp = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, menu);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cat_pos = i;
                switch (cat_pos){
                    case 0 :

                        mAdapter = new Coffee_Adapter(getApplicationContext(),coffeeList);
                        recyclerView.setAdapter(mAdapter);

                        break;

                    case 1 :
                        DAdapter = new Drink_Adapter(getApplicationContext(),drinkList);
                        recyclerView.setAdapter(DAdapter);

                        break;

                    case 2 :
                        FAdapter = new Food_Adapter(getApplicationContext(),foodList);
                        recyclerView.setAdapter(FAdapter);

                        break;

                    case 3 :
                        SoAdatper = new Soup_Adapter(getApplicationContext(),soupList);
                        recyclerView.setAdapter(SoAdatper);

                        break;

                    case 4 :
                        SaAdapter = new Salad_Adapter(getApplicationContext(),saladList);
                        recyclerView.setAdapter(SaAdapter);

                        break;

                    case 5 :
                        GAdapter = new Grill_Adapter(getApplicationContext(),grillList);
                        recyclerView.setAdapter(GAdapter);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_add = findViewById(R.id.add_button);
        btn_add.setText("အသစ်ထည့်မယ်");
        btn_add.setTypeface(Utils.getMMTypeface(this));

        body = findViewById(R.id.body);

        add_dialog = findViewById(R.id.add_dialog);
        dialog_close = findViewById(R.id.dialog_close);
        dialog_add_btn = findViewById(R.id.dialog_add_btn);
        dialog_add_btn.setTypeface(Utils.getMMTypeface(this));
        dialog_add_btn.setText("ထည့်မယ်");
        dialog_title = findViewById(R.id.dialog_title);
        dialog_title.setTypeface(Utils.getMMTypeface(this));
        dialog_title.setText("မီနူးအသစ်ထည့်ပါ");

        name_edt = findViewById(R.id.dialog_name);
        type_edt = findViewById(R.id.dialog_type);
        price_edt = findViewById(R.id.dialog_price);

        dialog_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = name_edt.getText().toString().trim();
                String type = type_edt.getText().toString().trim();
                String price = price_edt.getText().toString().trim();

                if (cat_pos == 0){

                    if (edit){
                        updateData(name,type,price,pos,0);
                    }else {
                        db.insertCoffee(name, type, price);
                        addData(0);
                    }
                }else if (cat_pos == 1){

                    if (edit){
                        updateData(name,type,price,pos,1);
                    }else {
                        db.insertDrink(name, type, price);
                        addData(1);
                    }
                }else if (cat_pos == 2){

                    if (edit){
                        updateData(name,type,price,pos,2);
                    }else {
                        db.insertFood(name, type, price);
                        addData(2);
                    }
                }else if (cat_pos == 3){

                    if (edit){
                        updateData(name,type,price,pos,3);
                    }else {
                        db.insertSoup(name, type, price);
                        addData(3);
                    }
                }else if (cat_pos == 4){

                    if (edit){
                        updateData(name,type,price,pos,4);
                    }else {
                        db.insertSalad(name, type, price);
                        addData(4);
                    }
                }else if (cat_pos == 5){

                    if (edit){
                        updateData(name,type,price,pos,5);
                    }else {
                        db.insertGrill(name, type, price);
                        addData(5);
                    }
                }

                name_edt.setText("");
                type_edt.setText("");
                price_edt.setText("");

                add_dialog.setVisibility(View.GONE);
                body.setVisibility(View.VISIBLE);
            }
        });



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_dialog.setVisibility(View.VISIBLE);
                body.setVisibility(View.GONE);



            }
        });

        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_dialog.setVisibility(View.GONE);
                body.setVisibility(View.VISIBLE);
            }
        });



    }

    private void ReloadList() {
        drinkList.addAll(db.getAllDrink());
        foodList.addAll(db.getAllFood());
        soupList.addAll(db.getAllSoup());
        saladList.addAll(db.getAllSalad());
        grillList.addAll(db.getAllGrill());
    }

    private void addData(int pos) {

        switch (pos){
            case 0:
                coffeeList.clear();
                coffeeList.addAll(db.getAllCoffee());
                mAdapter.notifyDataSetChanged();
                break;

            case 1:
                drinkList.clear();
                drinkList.addAll(db.getAllDrink());
                DAdapter.notifyDataSetChanged();
                break;

            case 2:
                foodList.clear();
                foodList.addAll(db.getAllFood());
                FAdapter.notifyDataSetChanged();
                break;

            case 3:
                soupList.clear();
                soupList.addAll(db.getAllSoup());
                SoAdatper.notifyDataSetChanged();
                break;

            case 4:
                saladList.clear();
                saladList.addAll(db.getAllSalad());
                SaAdapter.notifyDataSetChanged();
                break;

            case 5:
                grillList.clear();
                grillList.addAll(db.getAllGrill());
                GAdapter.notifyDataSetChanged();
                break;
        }

    }




    private void updateData(String name,String type,String price, int position,int cat_pos) {

        switch (cat_pos){
            case 0:
                Coffee n = coffeeList.get(position);
                // updating note text
                n.setName(name);
                n.setType(type);
                n.setPrice(price);

                // updating note in db
                db.updateCoffee(n);

                // refreshing the list
                coffeeList.set(position, n);
                mAdapter.notifyItemChanged(position);
                break;

            case 1:
                Drink n1 = drinkList.get(position);
                // updating note text
                n1.setName(name);
                n1.setType(type);
                n1.setPrice(price);

                // updating note in db
                db.updateDrink(n1);

                // refreshing the list
                drinkList.set(position, n1);
                DAdapter.notifyItemChanged(position);
                break;

            case 2:
                Food n2 = foodList.get(position);
                // updating note text
                n2.setName(name);
                n2.setType(type);
                n2.setPrice(price);

                // updating note in db
                db.updateFood(n2);

                // refreshing the list
                foodList.set(position, n2);
                FAdapter.notifyItemChanged(position);
                break;

            case 3:
                Soup n3 = soupList.get(position);
                // updating note text
                n3.setName(name);
                n3.setType(type);
                n3.setPrice(price);

                // updating note in db
                db.updateSoup(n3);

                // refreshing the list
                soupList.set(position, n3);
                SoAdatper.notifyItemChanged(position);
                break;

            case 4:
                Salad n4 = saladList.get(position);
                // updating note text
                n4.setName(name);
                n4.setType(type);
                n4.setPrice(price);

                // updating note in db
                db.updateSalad(n4);

                // refreshing the list
                saladList.set(position, n4);
                SaAdapter.notifyItemChanged(position);
                break;

            case 5:
                Grill n5 = grillList.get(position);
                // updating note text
                n5.setName(name);
                n5.setType(type);
                n5.setPrice(price);

                // updating note in db
                db.updateGrill(n5);

                // refreshing the list
                grillList.set(position, n5);
                GAdapter.notifyItemChanged(position);
                break;

        }

    }

    private void showActionsDialog(final int position, final int cat_pos) {
        CharSequence colors[] = new CharSequence[]{MDetect.INSTANCE.getText("ပြင်ဆင်မယ်"), MDetect.INSTANCE.getText("ဖျက်မယ်")};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(MDetect.INSTANCE.getText("ရွေးချယ်ပါ"));
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    edit = true;
                    pos = position;
                    add_dialog.setVisibility(View.VISIBLE);
                    body.setVisibility(View.GONE);

                    switch (cat_pos){
                        case 0:

                            name_edt.setText(coffeeList.get(position).getName());
                            type_edt.setText(coffeeList.get(position).getType());
                            price_edt.setText(coffeeList.get(position).getPrice());

                            break;

                        case 1:

                            name_edt.setText(drinkList.get(position).getName());
                            type_edt.setText(drinkList.get(position).getType());
                            price_edt.setText(drinkList.get(position).getPrice());

                            break;

                        case 2:

                            name_edt.setText(foodList.get(position).getName());
                            type_edt.setText(foodList.get(position).getType());
                            price_edt.setText(foodList.get(position).getPrice());

                            break;

                        case 3:

                            name_edt.setText(soupList.get(position).getName());
                            type_edt.setText(soupList.get(position).getType());
                            price_edt.setText(soupList.get(position).getPrice());

                            break;

                        case 4:

                            name_edt.setText(saladList.get(position).getName());
                            type_edt.setText(saladList.get(position).getType());
                            price_edt.setText(saladList.get(position).getPrice());

                            break;

                        case 5:

                            name_edt.setText(grillList.get(position).getName());
                            type_edt.setText(grillList.get(position).getType());
                            price_edt.setText(grillList.get(position).getPrice());

                            break;
                    }

                    dialog_add_btn.setText("သေချာပြီ");

                } else {
                    deleteCoffee(position,cat_pos);
                }
            }
        });
        builder.show();
    }

    private void deleteCoffee(int position,int cat_pos) {

        switch (cat_pos){
            case 0:
                // deleting the note from db
                db.deleteCoffee(coffeeList.get(position));

                // removing the note from the list
                coffeeList.remove(position);
                mAdapter.notifyItemRemoved(position);
                break;

            case 1:
                // deleting the note from db
                db.deleteDrink(drinkList.get(position));

                // removing the note from the list
                drinkList.remove(position);
                DAdapter.notifyItemRemoved(position);
                break;

            case 2:
                // deleting the note from db
                db.deleteFood(foodList.get(position));

                // removing the note from the list
                foodList.remove(position);
                FAdapter.notifyItemRemoved(position);
                break;

            case 3:
                // deleting the note from db
                db.deleteSoup(soupList.get(position));

                // removing the note from the list
                soupList.remove(position);
                SoAdatper.notifyItemRemoved(position);
                break;

            case 4:
                // deleting the note from db
                db.deleteSalad(saladList.get(position));

                // removing the note from the list
                saladList.remove(position);
                SaAdapter.notifyItemRemoved(position);
                break;

            case 5:
                // deleting the note from db
                db.deleteGrill(grillList.get(position));

                // removing the note from the list
                grillList.remove(position);
                GAdapter.notifyItemRemoved(position);
                break;
        }


    }

}
