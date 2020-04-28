package abc.home.zarni.evermore.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constants {

    public static final DatabaseReference FIREBASE_DB_REF =
            FirebaseDatabase.getInstance().getReference();

    public static final String CHILD_SOLD= "Sold";

    public static final String CHILD_MENU= "Menu";

    public static final String CHILD_TOTAL= "Total";
}
