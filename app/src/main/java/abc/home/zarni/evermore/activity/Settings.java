package abc.home.zarni.evermore.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import abc.home.zarni.evermore.R;
import abc.home.zarni.evermore.database.DatabaseHelper;
import abc.home.zarni.evermore.database.model.Coffee;
import abc.home.zarni.evermore.database.model.Drink;
import abc.home.zarni.evermore.database.model.Food;
import abc.home.zarni.evermore.database.model.Grill;
import abc.home.zarni.evermore.database.model.Recent;
import abc.home.zarni.evermore.database.model.Salad;
import abc.home.zarni.evermore.database.model.Soup;
import abc.home.zarni.evermore.utils.Utils;
import me.myatminsoe.mdetect.MDetect;

import static abc.home.zarni.evermore.utils.Constants.CHILD_MENU;
import static abc.home.zarni.evermore.utils.Constants.CHILD_SOLD;
import static abc.home.zarni.evermore.utils.Constants.CHILD_TOTAL;
import static abc.home.zarni.evermore.utils.Constants.FIREBASE_DB_REF;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout add_menu;
    TextView add_text,show_text,send_text,restore_text,backup_text;

    CardView show,send,restore,backup;
    DatabaseHelper db;


    List<Recent> recents = new ArrayList<>();


    List<Coffee> coffeeList = new ArrayList<>();
    List<Drink> drinkList = new ArrayList<>();
    List<Food> foodList = new ArrayList<>();
    List<Soup> soupList = new ArrayList<>();
    List<Salad> saladList = new ArrayList<>();
    List<Grill> grillList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle(MDetect.INSTANCE.getText("ချိန်ညှိမယ်"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        recents.addAll(db.getAllRecent());
        getAllMenu();


        add_menu = findViewById(R.id.add_menu);
        add_text = findViewById(R.id.add_text);

        add_text.setText(MDetect.INSTANCE.getText("မီနူးပြင်မယ်"));

        show_text = findViewById(R.id.show_text);

        show_text.setText(MDetect.INSTANCE.getText("ယနေ့ရောင်းပြီးသောစာရင်း"));

        send_text = findViewById(R.id.send_text);

        send_text.setText(MDetect.INSTANCE.getText("စာရင်းပို့မယ်"));

//        restore_text = findViewById(R.id.restore_text);
//
//        restore_text.setText(MDetect.INSTANCE.getText("မီးနူးများပြန်ယူမယ်"));
//
//        backup_text = findViewById(R.id.backup_text);
//
//        backup_text.setText(MDetect.INSTANCE.getText("မီးနူးများသိမ်းထားမယ်"));

        add_menu.setOnClickListener(this);

        show = findViewById(R.id.show);
        send = findViewById(R.id.send_menu_card);
//        restore = findViewById(R.id.restore_menu_card);
//        backup = findViewById(R.id.backup_menu_card);

        show.setOnClickListener(this);
        send.setOnClickListener(this);
//        restore.setOnClickListener(this);
//        backup.setOnClickListener(this);
//




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_menu: startActivity(new Intent(Settings.this,AddMenu.class));
                break;

            case R.id.show:
                String s = "";
                for (int i = 0 ; i < recents.size(); i++){
                    int price = Integer.parseInt(recents.get(i).getCount()) * Integer.parseInt(recents.get(i).getPrice());
                    s += recents.get(i).getName()+ " " + recents.get(i).getType() + " " + recents.get(i).getCount() + " " + price + "\n";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(MDetect.INSTANCE.getText("Data"));
                builder.setMessage(s);
                builder.show();
                break;

            case R.id.send_menu_card:

                SendData();
                break;
//
//            case R.id.restore_menu_card:
//                RestoreData();
//                break;
//
//            case R.id.backup_menu_card:
//                BackupData();
//                break;



        }
    }

    private void BackupData() {

        FIREBASE_DB_REF.child(CHILD_MENU).removeValue();


            for (int a = 0; a < coffeeList.size(); a++) {
                Coffee coffee = new Coffee(coffeeList.get(a).getId(), coffeeList.get(a).getName(), coffeeList.get(a).getType(), coffeeList.get(a).getPrice());
                FIREBASE_DB_REF.child(CHILD_MENU).child("Coffee").child(coffeeList.get(a).getId() + "").setValue(coffee);
            }


            for (int b = 0; b < drinkList.size(); b++) {
                Drink drink = new Drink(drinkList.get(b).getId(), drinkList.get(b).getName(), drinkList.get(b).getType(), drinkList.get(b).getPrice());
                FIREBASE_DB_REF.child(CHILD_MENU).child("Drink").child(drinkList.get(b).getId() + "").setValue(drink);
            }



            for (int c = 0; c < foodList.size(); c++) {
                Food food = new Food(foodList.get(c).getId(), foodList.get(c).getName(), foodList.get(c).getType(), foodList.get(c).getPrice());
                FIREBASE_DB_REF.child(CHILD_MENU).child("Food").child(foodList.get(c).getId() + "").setValue(food);
            }



            for (int d = 0; d < soupList.size(); d++) {
                Soup soup = new Soup(soupList.get(d).getId(), soupList.get(d).getName(), soupList.get(d).getType(), soupList.get(d).getPrice());
                FIREBASE_DB_REF.child(CHILD_MENU).child("Soup").child(soupList.get(d).getId() + "").setValue(soup);
            }



            for (int e = 0; e < saladList.size(); e++) {
                Salad salad = new Salad(saladList.get(e).getId(), saladList.get(e).getName(), saladList.get(e).getType(), saladList.get(e).getPrice());
                FIREBASE_DB_REF.child(CHILD_MENU).child("Salad").child(saladList.get(e).getId() + "").setValue(salad);
            }



            for (int f = 0; f < grillList.size(); f++) {
                Grill grill = new Grill(grillList.get(f).getId(), grillList.get(f).getName(), grillList.get(f).getType(), grillList.get(f).getPrice());
                FIREBASE_DB_REF.child(CHILD_MENU).child("Grill").child(grillList.get(f).getId() + "").setValue(grill);

        }
        final AlertDialog.Builder success = new AlertDialog.Builder(this);
        success.setMessage(MDetect.INSTANCE.getText("မီးနူးများသိမ်းခြင်း အောင်မြင်ပါသည်။"));
        success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        success.show();
    }

    private void getAllMenu() {
        coffeeList.addAll(db.getAllCoffee());
        drinkList.addAll(db.getAllDrink());
        foodList.addAll(db.getAllFood());
        soupList.addAll(db.getAllSoup());
        saladList.addAll(db.getAllSalad());
        grillList.addAll(db.getAllGrill());
    }

    private void RestoreData() {

        db.deleteAll(coffeeList,drinkList,foodList,soupList,saladList,grillList);
        coffeeList.clear();
        drinkList.clear();
        foodList.clear();
        soupList.clear();
        saladList.clear();
        grillList.clear();

        FIREBASE_DB_REF.child(CHILD_MENU).child("Coffee").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Coffee coffee = ds.getValue(Coffee.class);
                        db.insertCoffee(coffee.getName(),coffee.getType(),coffee.getPrice());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FIREBASE_DB_REF.child(CHILD_MENU).child("Drink").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Drink drink = ds.getValue(Drink.class);
                        db.insertDrink(drink.getName(),drink.getType(),drink.getPrice());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FIREBASE_DB_REF.child(CHILD_MENU).child("Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Food food = ds.getValue(Food.class);
                        db.insertFood(food.getName(),food.getType(),food.getPrice());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FIREBASE_DB_REF.child(CHILD_MENU).child("Soup").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Soup soup = ds.getValue(Soup.class);
                        db.insertSoup(soup.getName(),soup.getType(),soup.getPrice());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FIREBASE_DB_REF.child(CHILD_MENU).child("Salad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Salad salad = ds.getValue(Salad.class);
                        db.insertSalad(salad.getName(),salad.getType(),salad.getPrice());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FIREBASE_DB_REF.child(CHILD_MENU).child("Grill").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Grill grill = ds.getValue(Grill.class);
                        db.insertGrill(grill.getName(),grill.getType(),grill.getPrice());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final AlertDialog.Builder success = new AlertDialog.Builder(this);
        success.setMessage(MDetect.INSTANCE.getText("မီနူးများရယူခြင်း အောင်မြင်ပါသည်။"));
        success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        success.show();
    }

    private void SendData() {

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("M");
        SimpleDateFormat formatter3 = new SimpleDateFormat("d");
        Date y = new Date();
        String year = formatter1.format(y);
        String month = formatter2.format(y);
        String day = formatter3.format(y);

        int a = 0 ;

       for (int i = 0 ; i<recents.size(); i++){

           Recent r = new Recent(recents.get(i).getName(),recents.get(i).getType(),recents.get(i).getPrice(),recents.get(i).getCount());
           FIREBASE_DB_REF.child(CHILD_SOLD).child(year).child(month).child(day).child(i+"").setValue(r);
           int b = Integer.parseInt(recents.get(i).getPrice()) * Integer.parseInt(recents.get(i).getCount());
           a += b;
       }

       FIREBASE_DB_REF.child(CHILD_TOTAL).child(year).child(month).child(day).setValue(a+"");

        final AlertDialog.Builder success = new AlertDialog.Builder(this);
        success.setMessage(MDetect.INSTANCE.getText("ပို့ပေးခြင်း အောင်မြင်ပါသည်။"));
        success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteRecent(recents);
                dialogInterface.dismiss();
            }
        });

        success.show();

    }

}

