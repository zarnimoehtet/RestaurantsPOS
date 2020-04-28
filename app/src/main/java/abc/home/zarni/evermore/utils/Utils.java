package abc.home.zarni.evermore.utils;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {

    public static Typeface getMMTypeface (Context context){
        return Typeface.createFromAsset(context.getAssets(),"MasterpieceUniRound.ttf");
    }

    public static Typeface getMMZawGyiTypeface (Context context){
        return Typeface.createFromAsset(context.getAssets(),"zawgyi.ttf");
    }


    public static Typeface getQickSandTypeface (Context context){
        return Typeface.createFromAsset(context.getAssets(),"QuicksandBold.ttf");
    }

    public static Typeface getPoppinsTypeface (Context context){
        return Typeface.createFromAsset(context.getAssets(),"PoppinsRegular.ttf");
    }

}
