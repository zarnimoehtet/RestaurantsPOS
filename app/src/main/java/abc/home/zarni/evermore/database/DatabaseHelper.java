package abc.home.zarni.evermore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import abc.home.zarni.evermore.database.model.Coffee;
import abc.home.zarni.evermore.database.model.Drink;
import abc.home.zarni.evermore.database.model.Food;
import abc.home.zarni.evermore.database.model.Grill;
import abc.home.zarni.evermore.database.model.Recent;
import abc.home.zarni.evermore.database.model.TempOrder;
import abc.home.zarni.evermore.database.model.Salad;
import abc.home.zarni.evermore.database.model.Soup;

public class DatabaseHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "evermore.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Coffee.CREATE_TABLE);
        db.execSQL(Drink.CREATE_TABLE);
        db.execSQL(Food.CREATE_TABLE);
        db.execSQL(Soup.CREATE_TABLE);
        db.execSQL(Salad.CREATE_TABLE);
        db.execSQL(Grill.CREATE_TABLE);
        db.execSQL(TempOrder.CREATE_TABLE);
        db.execSQL(Recent.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Coffee.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Drink.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Food.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Soup.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Salad.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Grill.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TempOrder.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Recent.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

//Insert
/**************/
    public long insertCoffee(String name, String type, String price) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Coffee.COLUMN_NAME, name);
        values.put(Coffee.COLUMN_TYPE, type);
        values.put(Coffee.COLUMN_PRICE, price);

        // insert row
        long id = db.insert(Coffee.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertDrink(String name, String type, String price) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Drink.COLUMN_NAME, name);
        values.put(Drink.COLUMN_TYPE, type);
        values.put(Drink.COLUMN_PRICE, price);

        // insert row
        long id = db.insert(Drink.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertFood(String name, String type, String price) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Food.COLUMN_NAME, name);
        values.put(Food.COLUMN_TYPE, type);
        values.put(Food.COLUMN_PRICE, price);

        // insert row
        long id = db.insert(Food.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertSoup(String name, String type, String price) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Soup.COLUMN_NAME, name);
        values.put(Soup.COLUMN_TYPE, type);
        values.put(Soup.COLUMN_PRICE, price);

        // insert row
        long id = db.insert(Soup.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public long insertSalad(String name, String type, String price) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Salad.COLUMN_NAME, name);
        values.put(Salad.COLUMN_TYPE, type);
        values.put(Salad.COLUMN_PRICE, price);

        // insert row
        long id = db.insert(Salad.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertGrill(String name, String type, String price) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Grill.COLUMN_NAME, name);
        values.put(Grill.COLUMN_TYPE, type);
        values.put(Grill.COLUMN_PRICE, price);

        // insert row
        long id = db.insert(Grill.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertOrder(String name, String type, String price, String count) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(TempOrder.COLUMN_NAME, name);
        values.put(TempOrder.COLUMN_TYPE, type);
        values.put(TempOrder.COLUMN_PRICE, price);
        values.put(TempOrder.COLUMN_COUNT,count);

        // insert row
        long id = db.insert(TempOrder.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertRecent(List<TempOrder> order) {
        // get writable database as we want to write data
        long id = 1L;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them

        for(int i = 0 ; i< order.size(); i++) {

            values.put(Recent.COLUMN_NAME, order.get(i).getName());
            values.put(Recent.COLUMN_TYPE, order.get(i).getType());
            values.put(Recent.COLUMN_PRICE, order.get(i).getPrice());
            values.put(Recent.COLUMN_COUNT, order.get(i).getCount());

            // insert row
             id = db.insert(Recent.TABLE_NAME, null, values);

        }

        db.close();

        // return newly inserted row id
        return id;
    }
    /********************************/


    //Select
    /*******************************/


    public List<Coffee> getAllCoffee() {
        List<Coffee> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Coffee.TABLE_NAME + " ORDER BY " +
                Coffee.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Coffee note = new Coffee();
                note.setId(cursor.getInt(cursor.getColumnIndex(Coffee.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(Coffee.COLUMN_NAME)));
                note.setType(cursor.getString(cursor.getColumnIndex(Coffee.COLUMN_TYPE)));
                note.setPrice(cursor.getString(cursor.getColumnIndex(Coffee.COLUMN_PRICE)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public List<Drink> getAllDrink() {
        List<Drink> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Drink.TABLE_NAME + " ORDER BY " +
                Drink.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor1.moveToFirst()) {
            do {
                Drink note = new Drink();
                note.setId(cursor1.getInt(cursor1.getColumnIndex(Drink.COLUMN_ID)));
                note.setName(cursor1.getString(cursor1.getColumnIndex(Drink.COLUMN_NAME)));
                note.setType(cursor1.getString(cursor1.getColumnIndex(Drink.COLUMN_TYPE)));
                note.setPrice(cursor1.getString(cursor1.getColumnIndex(Drink.COLUMN_PRICE)));

                notes.add(note);
            } while (cursor1.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }


    public List<Food> getAllFood() {
        List<Food> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Food.TABLE_NAME + " ORDER BY " +
                Food.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor2 = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor2.moveToFirst()) {
            do {
                Food note = new Food();
                note.setId(cursor2.getInt(cursor2.getColumnIndex(Food.COLUMN_ID)));
                note.setName(cursor2.getString(cursor2.getColumnIndex(Food.COLUMN_NAME)));
                note.setType(cursor2.getString(cursor2.getColumnIndex(Food.COLUMN_TYPE)));
                note.setPrice(cursor2.getString(cursor2.getColumnIndex(Food.COLUMN_PRICE)));

                notes.add(note);
            } while (cursor2.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public List<Soup> getAllSoup() {
        List<Soup> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Soup.TABLE_NAME + " ORDER BY " +
                Soup.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor3 = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor3.moveToFirst()) {
            do {
                Soup note = new Soup();
                note.setId(cursor3.getInt(cursor3.getColumnIndex(Soup.COLUMN_ID)));
                note.setName(cursor3.getString(cursor3.getColumnIndex(Soup.COLUMN_NAME)));
                note.setType(cursor3.getString(cursor3.getColumnIndex(Soup.COLUMN_TYPE)));
                note.setPrice(cursor3.getString(cursor3.getColumnIndex(Soup.COLUMN_PRICE)));

                notes.add(note);
            } while (cursor3.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }


    public List<Salad> getAllSalad() {
        List<Salad> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Salad.TABLE_NAME + " ORDER BY " +
                Salad.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor4 = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor4.moveToFirst()) {
            do {
                Salad note = new Salad();
                note.setId(cursor4.getInt(cursor4.getColumnIndex(Salad.COLUMN_ID)));
                note.setName(cursor4.getString(cursor4.getColumnIndex(Salad.COLUMN_NAME)));
                note.setType(cursor4.getString(cursor4.getColumnIndex(Salad.COLUMN_TYPE)));
                note.setPrice(cursor4.getString(cursor4.getColumnIndex(Salad.COLUMN_PRICE)));

                notes.add(note);
            } while (cursor4.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public List<Grill> getAllGrill() {
        List<Grill> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Grill.TABLE_NAME + " ORDER BY " +
                Grill.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor5 = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor5.moveToFirst()) {
            do {
                Grill note = new Grill();
                note.setId(cursor5.getInt(cursor5.getColumnIndex(Grill.COLUMN_ID)));
                note.setName(cursor5.getString(cursor5.getColumnIndex(Grill.COLUMN_NAME)));
                note.setType(cursor5.getString(cursor5.getColumnIndex(Grill.COLUMN_TYPE)));
                note.setPrice(cursor5.getString(cursor5.getColumnIndex(Grill.COLUMN_PRICE)));

                notes.add(note);
            } while (cursor5.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }


    public List<TempOrder> getAllOrder() {
        List<TempOrder> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TempOrder.TABLE_NAME + " ORDER BY " +
                TempOrder.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TempOrder note = new TempOrder();
                note.setId(cursor.getInt(cursor.getColumnIndex(TempOrder.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(TempOrder.COLUMN_NAME)));
                note.setType(cursor.getString(cursor.getColumnIndex(TempOrder.COLUMN_TYPE)));
                note.setPrice(cursor.getString(cursor.getColumnIndex(TempOrder.COLUMN_PRICE)));
                note.setCount(cursor.getString(cursor.getColumnIndex(TempOrder.COLUMN_COUNT)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }


    public List<Recent> getAllRecent() {
        List<Recent> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Recent.TABLE_NAME + " ORDER BY " +
                Recent.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recent note = new Recent();
                note.setId(cursor.getInt(cursor.getColumnIndex(TempOrder.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(TempOrder.COLUMN_NAME)));
                note.setType(cursor.getString(cursor.getColumnIndex(TempOrder.COLUMN_TYPE)));
                note.setPrice(cursor.getString(cursor.getColumnIndex(TempOrder.COLUMN_PRICE)));
                note.setCount(cursor.getString(cursor.getColumnIndex(TempOrder.COLUMN_COUNT)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }
   /************************/



//Update

   /***********************/
    public int updateCoffee(Coffee note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Coffee.COLUMN_NAME, note.getName());
        values.put(Coffee.COLUMN_TYPE, note.getType());
        values.put(Coffee.COLUMN_PRICE, note.getPrice());

        // updating row
        return db.update(Coffee.TABLE_NAME, values, Coffee.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public int updateDrink(Drink note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Drink.COLUMN_NAME, note.getName());
        values.put(Drink.COLUMN_TYPE, note.getType());
        values.put(Drink.COLUMN_PRICE, note.getPrice());

        // updating row
        return db.update(Drink.TABLE_NAME, values, Drink.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public int updateFood(Food note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Food.COLUMN_NAME, note.getName());
        values.put(Food.COLUMN_TYPE, note.getType());
        values.put(Food.COLUMN_PRICE, note.getPrice());

        // updating row
        return db.update(Food.TABLE_NAME, values, Food.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public int updateSoup(Soup note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Soup.COLUMN_NAME, note.getName());
        values.put(Soup.COLUMN_TYPE, note.getType());
        values.put(Soup.COLUMN_PRICE, note.getPrice());

        // updating row
        return db.update(Soup.TABLE_NAME, values, Soup.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public int updateSalad(Salad note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Salad.COLUMN_NAME, note.getName());
        values.put(Salad.COLUMN_TYPE, note.getType());
        values.put(Salad.COLUMN_PRICE, note.getPrice());

        // updating row
        return db.update(Salad.TABLE_NAME, values, Salad.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public int updateGrill(Grill note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Grill.COLUMN_NAME, note.getName());
        values.put(Grill.COLUMN_TYPE, note.getType());
        values.put(Grill.COLUMN_PRICE, note.getPrice());

        // updating row
        return db.update(Grill.TABLE_NAME, values, Grill.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public int updateOrder(TempOrder note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TempOrder.COLUMN_NAME, note.getName());
        values.put(TempOrder.COLUMN_TYPE, note.getType());
        values.put(TempOrder.COLUMN_PRICE, note.getPrice());
        values.put(TempOrder.COLUMN_COUNT, note.getPrice());


        // updating row
        return db.update(TempOrder.TABLE_NAME, values, TempOrder.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public int updateRecent(Recent note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Recent.COLUMN_NAME, note.getName());
        values.put(Recent.COLUMN_TYPE, note.getType());
        values.put(Recent.COLUMN_PRICE, note.getPrice());
        values.put(Recent.COLUMN_COUNT, note.getPrice());


        // updating row
        return db.update(Recent.TABLE_NAME, values, Recent.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    /******************************/


//Delete
    /********************************/

    public void deleteCoffee(Coffee note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Coffee.TABLE_NAME, Coffee.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteDrink(Drink note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Drink.TABLE_NAME, Drink.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteFood(Food note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Food.TABLE_NAME, Food.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteSoup(Soup note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Soup.TABLE_NAME, Soup.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteSalad(Salad note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Salad.TABLE_NAME, Salad.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteGrill(Grill note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Grill.TABLE_NAME, Grill.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteOrderSingle(TempOrder note) {
        SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TempOrder.TABLE_NAME, TempOrder.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(note.getId())});

        db.close();
    }
    public void deleteOrder(List<TempOrder> note) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0 ; i < note.size() ; i++) {
            db.delete(TempOrder.TABLE_NAME, TempOrder.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(note.get(i).getId())});
        }
        db.close();
    }

    public void deleteRecent(List<Recent> note) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0 ; i< note.size() ; i++) {
            db.delete(Recent.TABLE_NAME, Recent.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(note.get(i).getId())});
        }
        db.close();
    }

    public void deleteAll(List<Coffee> coffees,List<Drink> drinks,List<Food> foods,List<Soup> soups,List<Salad> salads,List<Grill> grills){
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0 ; i< coffees.size() ; i++) {
            db.delete(Coffee.TABLE_NAME, Coffee.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(coffees.get(i).getId())});
        }

        for (int j = 0 ; j< drinks.size() ; j++) {
            db.delete(Drink.TABLE_NAME, Drink.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(drinks.get(j).getId())});
        }

        for (int k = 0 ; k< foods.size() ; k++) {
            db.delete(Food.TABLE_NAME, Food.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(foods.get(k).getId())});
        }

        for (int l = 0 ; l< soups.size() ; l++) {
            db.delete(Soup.TABLE_NAME, Soup.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(soups.get(l).getId())});
        }

        for (int m = 0 ; m< salads.size() ; m++) {
            db.delete(Salad.TABLE_NAME, Salad.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(salads.get(m).getId())});
        }

        for (int n = 0 ; n< grills.size() ; n++) {
            db.delete(Grill.TABLE_NAME, Grill.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(grills.get(n).getId())});
        }
        db.close();
    }

    /*****************************/
}
