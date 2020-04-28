package abc.home.zarni.evermore.activity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import abc.home.zarni.evermore.BuildConfig;
import abc.home.zarni.evermore.R;
import abc.home.zarni.evermore.adapter.main_adapter.Main_Coffee_Adapter;
import abc.home.zarni.evermore.adapter.main_adapter.Main_Drink_Adapter;
import abc.home.zarni.evermore.adapter.main_adapter.Main_Food_Adapter;
import abc.home.zarni.evermore.adapter.main_adapter.Main_Grill_Adapter;
import abc.home.zarni.evermore.adapter.main_adapter.Main_Salad_Adapter;
import abc.home.zarni.evermore.adapter.main_adapter.Main_Soup_Adapter;
import abc.home.zarni.evermore.database.DatabaseHelper;
import abc.home.zarni.evermore.database.model.Coffee;
import abc.home.zarni.evermore.database.model.Drink;
import abc.home.zarni.evermore.database.model.Food;
import abc.home.zarni.evermore.database.model.Grill;
import abc.home.zarni.evermore.database.model.Salad;
import abc.home.zarni.evermore.database.model.Soup;
import abc.home.zarni.evermore.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import me.myatminsoe.mdetect.MDetect;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CircleImageView coffee,drink,food,soup,salad,grill;
    RecyclerView recyclerView;
    TextView title;

    String DB_NAME = "evermore.db";
    String DB_PATH = "/data/data/abc.home.zarni.evermore/databases/";

    List<Coffee> coffeeList = new ArrayList<>();
    List<Drink> drinkList = new ArrayList<>();
    List<Food> foodList = new ArrayList<>();
    List<Soup> soupList = new ArrayList<>();
    List<Salad> saladList = new ArrayList<>();
    List<Grill> grillList = new ArrayList<>();


    Main_Coffee_Adapter mainCoffeeAdapter;
    Main_Drink_Adapter mainDrinkAdapter;
    Main_Food_Adapter mainFoodAdapter;
    Main_Soup_Adapter mainSoupAdapter;
    Main_Salad_Adapter mainSaladAdapter;
    Main_Grill_Adapter mainGrillAdapter;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(MDetect.INSTANCE.getText("မီနူးများ"));
        Log.i("DATABASE EXIST : ", "" + checkDataBase());
        if (!checkDataBase() && CreateDir_NotExists(this.DB_PATH)) {
            copyDataBase();
        }

        db = new DatabaseHelper(this);

        coffeeList.addAll(db.getAllCoffee());
        drinkList.addAll(db.getAllDrink());
        foodList.addAll(db.getAllFood());
        soupList.addAll(db.getAllSoup());
        saladList.addAll(db.getAllSalad());
        grillList.addAll(db.getAllGrill());

        mainCoffeeAdapter = new Main_Coffee_Adapter(this,coffeeList);

        recyclerView = findViewById(R.id.main_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) mLayoutManager).setReverseLayout(true);
        ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mainCoffeeAdapter);

        coffee = findViewById(R.id.coffee);
        drink = findViewById(R.id.drink);
        food = findViewById(R.id.food);
        soup = findViewById(R.id.soup);
        salad = findViewById(R.id.salad);
        grill = findViewById(R.id.grill);

        title = findViewById(R.id.title);
        title.setText(MDetect.INSTANCE.getText("ကော်ဖီ"));


        coffee.setOnClickListener(this);
        drink.setOnClickListener(this);
        food.setOnClickListener(this);
        soup.setOnClickListener(this);
        salad.setOnClickListener(this);
        grill.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.coffee:
                coffee.setBorderColor(getResources().getColor(R.color.holo_orange));
                drink.setBorderColor(getResources().getColor(R.color.holo_green));
                food.setBorderColor(getResources().getColor(R.color.holo_green));
                soup.setBorderColor(getResources().getColor(R.color.holo_green));
                salad.setBorderColor(getResources().getColor(R.color.holo_green));
                grill.setBorderColor(getResources().getColor(R.color.holo_green));


                title.setText(MDetect.INSTANCE.getText("ကော်ဖီ"));

                coffeeList.clear();
                coffeeList.addAll(db.getAllCoffee());
                mainCoffeeAdapter = new Main_Coffee_Adapter(this,coffeeList);
                recyclerView.setAdapter(mainCoffeeAdapter);

                break;

            case R.id.drink:
                coffee.setBorderColor(getResources().getColor(R.color.holo_green));
                drink.setBorderColor(getResources().getColor(R.color.holo_orange));
                food.setBorderColor(getResources().getColor(R.color.holo_green));
                soup.setBorderColor(getResources().getColor(R.color.holo_green));
                salad.setBorderColor(getResources().getColor(R.color.holo_green));
                grill.setBorderColor(getResources().getColor(R.color.holo_green));


                title.setText(MDetect.INSTANCE.getText("အအေး"));

                drinkList.clear();
                drinkList.addAll(db.getAllDrink());
                mainDrinkAdapter = new Main_Drink_Adapter(this,drinkList);
                recyclerView.setAdapter(mainDrinkAdapter);
                break;

            case R.id.food:
                coffee.setBorderColor(getResources().getColor(R.color.holo_green));
                drink.setBorderColor(getResources().getColor(R.color.holo_green));
                food.setBorderColor(getResources().getColor(R.color.holo_orange));
                soup.setBorderColor(getResources().getColor(R.color.holo_green));
                salad.setBorderColor(getResources().getColor(R.color.holo_green));
                grill.setBorderColor(getResources().getColor(R.color.holo_green));


                title.setText(MDetect.INSTANCE.getText("စားစရာ"));

                foodList.clear();
                foodList.addAll(db.getAllFood());
                mainFoodAdapter = new Main_Food_Adapter(this,foodList);
                recyclerView.setAdapter(mainFoodAdapter);
                break;

            case R.id.soup:
                coffee.setBorderColor(getResources().getColor(R.color.holo_green));
                drink.setBorderColor(getResources().getColor(R.color.holo_green));
                food.setBorderColor(getResources().getColor(R.color.holo_green));
                soup.setBorderColor(getResources().getColor(R.color.holo_orange));
                salad.setBorderColor(getResources().getColor(R.color.holo_green));
                grill.setBorderColor(getResources().getColor(R.color.holo_green));


                title.setText(MDetect.INSTANCE.getText("ဟင်းချို"));

                soupList.clear();
                soupList.addAll(db.getAllSoup());
                mainSoupAdapter = new Main_Soup_Adapter(this,soupList);
                recyclerView.setAdapter(mainSoupAdapter);
                break;

            case R.id.salad:
                coffee.setBorderColor(getResources().getColor(R.color.holo_green));
                drink.setBorderColor(getResources().getColor(R.color.holo_green));
                food.setBorderColor(getResources().getColor(R.color.holo_green));
                soup.setBorderColor(getResources().getColor(R.color.holo_green));
                salad.setBorderColor(getResources().getColor(R.color.holo_orange));
                grill.setBorderColor(getResources().getColor(R.color.holo_green));


                title.setText(MDetect.INSTANCE.getText("အသုပ်"));

                saladList.clear();
                saladList.addAll(db.getAllSalad());
                mainSaladAdapter = new Main_Salad_Adapter(this,saladList);
                recyclerView.setAdapter(mainSaladAdapter);
                break;

            case R.id.grill:
                coffee.setBorderColor(getResources().getColor(R.color.holo_green));
                drink.setBorderColor(getResources().getColor(R.color.holo_green));
                food.setBorderColor(getResources().getColor(R.color.holo_green));
                soup.setBorderColor(getResources().getColor(R.color.holo_green));
                salad.setBorderColor(getResources().getColor(R.color.holo_green));
                grill.setBorderColor(getResources().getColor(R.color.holo_orange));


                title.setText(MDetect.INSTANCE.getText("အကင်"));

                grillList.clear();
                grillList.addAll(db.getAllGrill());
                mainGrillAdapter = new Main_Grill_Adapter(this,grillList);
                recyclerView.setAdapter(mainGrillAdapter);
                break;
        }
    }


    private void copyDataBase() {
        IOException e;
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        Log.i("Database", "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        try {
            InputStream myInput = getAssets().open(this.DB_NAME);
            OutputStream myOutput = new FileOutputStream(this.DB_PATH + this.DB_NAME);
            while (true) {
                OutputStream outputStream;
                try {
                    int length = myInput.read(buffer);
                    if (length > 0) {
                        myOutput.write(buffer, 0, length);
                    } else {
                        myOutput.close();
                        myOutput.flush();
                        myInput.close();
                        Log.i("Database", "New database has been copied to device!");
                        outputStream = myOutput;
                        return;
                    }
                } catch (IOException e2) {
                    e = e2;
                    outputStream = myOutput;
                }
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
        }
    }


    public boolean checkDataBase() {
        return new File(this.DB_PATH + this.DB_NAME).exists();
    }

    public static boolean CreateDir_NotExists(String path) {
        File file = new File(path);
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        Log.e("CREATE DIR : ", "Problem creating folder");
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.settings: startActivity(new Intent(MainActivity.this,Settings.class));
            return true;

            case R.id.recipe: startActivity(new Intent(MainActivity.this,Recipets.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
