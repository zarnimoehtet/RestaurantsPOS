package abc.home.zarni.evermore.database.model;

public class Recent {

    public static final String TABLE_NAME = "RecentTable";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_COUNT = "count";


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_TYPE + " TEXT,"
                    + COLUMN_PRICE + " TEXT,"
                    + COLUMN_COUNT + " TEXT"
                    + ")";

    private String name,type,price,count;
    private int id;

    public Recent() {
    }


    public Recent(String name, String type, String price, String count, int id) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
        this.id = id;
    }

    public Recent(String name, String type, String price, String count) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
