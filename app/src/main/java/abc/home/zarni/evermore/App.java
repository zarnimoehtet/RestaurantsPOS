package abc.home.zarni.evermore;

import android.app.Application;

import com.google.firebase.FirebaseApp;

import me.myatminsoe.mdetect.MDetect;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.getApps(this);
        FirebaseApp.getInstance();
        MDetect.INSTANCE.init(this);
    }
}
