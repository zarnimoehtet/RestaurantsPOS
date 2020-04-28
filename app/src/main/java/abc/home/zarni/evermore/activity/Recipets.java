package abc.home.zarni.evermore.activity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.BitmapCallback;

import net.posprinter.posprinterface.IMyBinder;
import net.posprinter.posprinterface.ProcessData;
import net.posprinter.posprinterface.UiExecute;
import net.posprinter.service.PosprinterService;
import net.posprinter.utils.BitmapToByteData;
import net.posprinter.utils.DataForSendToPrinterPos80;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import abc.home.zarni.evermore.BuildConfig;
import abc.home.zarni.evermore.R;
import abc.home.zarni.evermore.adapter.main_adapter.Vouncher_Adapter;
import abc.home.zarni.evermore.database.DatabaseHelper;
import abc.home.zarni.evermore.database.model.TempOrder;
import abc.home.zarni.evermore.utils.RecyclerTouchListener;
import abc.home.zarni.evermore.utils.Utils;
import me.myatminsoe.mdetect.MDetect;

public class Recipets extends AppCompatActivity {

    //IMyBinder interface，All methods that can be invoked to connect and send data are encapsulated within this interface
    public static IMyBinder binder;


    //bindService connection
    ServiceConnection conn= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //Bind successfully
            binder= (IMyBinder) iBinder;
            Log.e("binder","connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("disbinder","disconnected");
        }
    };
    Bitmap b1;
    Bitmap   b2;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                   printpicCode(b2);
                    return;
            }
        }
    };

    public static boolean ISCONNECT;
    BluetoothAdapter bluetoothAdapter;


    NestedScrollView scrollView;
    private DatabaseHelper db;
    private List<TempOrder> orderList = new ArrayList<>();
    private Vouncher_Adapter vouncherAdapter;

    RecyclerView recyclerView;

    TextView shop_name,sub_title,address,phone,date,name,qty,op_price,mul_pirce,total,total_price,thank;

    EditText time,cost;
    TextView dialog_title,dialog_add;
    ImageView dialog_close;
    CardView dialog;
    ProgressDialog progressDialog,printingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipets);
        getSupportActionBar().setTitle(MDetect.INSTANCE.getText("ဘောင်ချာ"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=new Intent(this, PosprinterService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);

        Tiny.getInstance().init(getApplication());


        scrollView = findViewById(R.id.scroll);
        shop_name = findViewById(R.id.shop_name);
        sub_title = findViewById(R.id.sub_title);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        date = findViewById(R.id.date_header);
        name = findViewById(R.id.name);
        qty = findViewById(R.id.qty);
        op_price = findViewById(R.id.op_price);
        mul_pirce = findViewById(R.id.mul_price);
        total = findViewById(R.id.total);
        total_price = findViewById(R.id.total_price);
        thank = findViewById(R.id.thank);



        db = new DatabaseHelper(this);
        orderList.addAll(db.getAllOrder());
        vouncherAdapter = new Vouncher_Adapter(this,orderList);

        recyclerView = findViewById(R.id.vouncher_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) mLayoutManager).setReverseLayout(true);
        ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(MDetect.INSTANCE.getText("ချိတ်ဆက်နေသည်။ ခဏစောင့်ပါ..."));

        printingDialog = new ProgressDialog(this);
        printingDialog.setMessage(MDetect.INSTANCE.getText("ဘေစာရွက် ထုတ်နေပြီ..."));
        recyclerView.setAdapter(vouncherAdapter);

        getTotalCost();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));
        SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy");
        Date d = new Date();
        date.setTypeface(Utils.getMMZawGyiTypeface(this));
        date.setText(MDetect.INSTANCE.getText("နေ့စွဲ : "+ formatter.format(d)));

        shop_name.setTypeface(Utils.getQickSandTypeface(this));
        sub_title.setTypeface(Utils.getPoppinsTypeface(this));
        address.setText(MDetect.INSTANCE.getText("အမှတ်(၂၀၄)၊ ဗိုလ်ချုပ်အောင်ဆန်းလမ်း၊ ချောင်းဆုံမြို့။"));
        phone.setTypeface(Utils.getPoppinsTypeface(this));

        name.setText(MDetect.INSTANCE.getText("အမျိုးအမည်"));
        qty.setText(MDetect.INSTANCE.getText("ဦးရေ"));
        op_price.setText(MDetect.INSTANCE.getText("နှုန်း"));
        mul_pirce.setText(MDetect.INSTANCE.getText("သင့်ငွေ"));
        total_price.setTypeface(Utils.getMMZawGyiTypeface(this));
        total.setText(MDetect.INSTANCE.getText("စုစုပေါင်း"));
        thank.setText(MDetect.INSTANCE.getText("အားပေးသူများကို ကျေးဇူးတင်ရှိပါသည်။"));

        time = findViewById(R.id.dialog_time);
        cost = findViewById(R.id.dialog_cost);

        dialog = findViewById(R.id.dialog);

        dialog_title = findViewById(R.id.dialog_title);
        dialog_add = findViewById(R.id.dialog_add_btn);
        dialog_close = findViewById(R.id.dialog_close);

        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setVisibility(View.GONE);
            }
        });


        dialog_title.setText(MDetect.INSTANCE.getText("အခန်းကျသင့်ငွေထည့်ရန်"));

        dialog_add.setText(MDetect.INSTANCE.getText("ထည့်မယ်"));

        dialog_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    dialog.setVisibility(View.GONE);
                    String t = time.getText().toString();
                    String c = cost.getText().toString();
                    db.insertOrder("အခန္းခ","",c,t);
                orderList.clear();
                orderList.addAll(db.getAllOrder());
                getTotalCost();
                vouncherAdapter.notifyDataSetChanged();

            }
        });


    }

    private void getTotalCost() {
        int cost = 0;
        for(int i = 0 ; i<orderList.size(); i++){
            int total = Integer.parseInt(orderList.get(i).getCount()) * Integer.parseInt(orderList.get(i).getPrice());
            cost += total;
        }

        total_price.setText(cost + "");
    }

    private void deleteItem(int pos){
        // deleting the note from db
        db.deleteOrderSingle(orderList.get(pos));

        // removing the note from the list
        orderList.remove(pos);
        vouncherAdapter.notifyItemRemoved(pos);
        getTotalCost();
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{ MDetect.INSTANCE.getText("ဖျက်မယ်")};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(MDetect.INSTANCE.getText("ရွေးချယ်ပါ"));
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                    deleteItem(position);

            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.connect:
                   setBluetooth();
                   return true;



            case R.id.print:
                print();
                addRecent();

                return true;

            case R.id.add:
                dialog.setVisibility(View.VISIBLE);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void connetBle(){
        String bleAdrress="DC:0D:30:24:37:99";
        progressDialog.show();

        if (!bleAdrress.equals(null) && !bleAdrress.equals("")){

            binder.connectBtPort(bleAdrress, new UiExecute() {
                @Override
                public void onsucess() {
                    ISCONNECT=true;
                    binder.write(DataForSendToPrinterPos80.openOrCloseAutoReturnPrintState(0x1f), new UiExecute() {
                        @Override
                        public void onsucess() {
                           // Toast.makeText(getApplicationContext(),"BT Connected",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            binder.acceptdatafromprinter(new UiExecute() {
                                @Override
                                public void onsucess() {
                                    progressDialog.dismiss();
                             //   Toast.makeText(getApplicationContext(),"BT Connected",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onfailed() {
                                    ISCONNECT=false;

                                }
                            });
                        }

                        @Override
                        public void onfailed() {

                        }
                    });

                }

                @Override
                public void onfailed() {
                    ISCONNECT=false;

                }
            });
        }


    }



    public void setBluetooth() {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (this.bluetoothAdapter.isEnabled()) {
            connetBle();
        } else {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
        }
    }

    public static Bitmap resizeImage(Bitmap bitmap, int w, boolean ischecked) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        if (width <= w) {
            return bitmap;
        }
        Bitmap resizedBitmap = null;
        if (ischecked) {
            resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, w, height);
        } else {
            float scaleWidth = ((float) w) / ((float) width);
            float scaleHeight = ((float) ((height * w) / width)) / ((float) height);
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);
        }
        return resizedBitmap;
    }

    public Bitmap convertGreyImg(Bitmap img) {
        int width = img.getWidth();
        int height = img.getHeight();

        int[] pixels = new int[width * height];

        img.getPixels(pixels, 0, width, 0, 0, width, height);


        //The arithmetic average of a grayscale image; a threshold
        double redSum=0,greenSum=0,blueSun=0;
        double total=width*height;

        for(int i = 0; i < height; i++)  {
            for(int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey  & 0x00FF0000 ) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);



                redSum+=red;
                greenSum+=green;
                blueSun+=blue;


            }
        }
        int m=(int) (redSum/total);

       // Conversion monochrome diagram
        for(int i = 0; i < height; i++)  {
            for(int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int alpha1 = 0xFF << 24;
                int red = ((grey  & 0x00FF0000 ) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);


                if (red>=m) {
                    red=green=blue=255;
                }else{
                    red=green=blue=0;
                }
                grey = alpha1 | (red << 16) | (green << 8) | blue;
                pixels[width*i+j]=grey;


            }
        }
        Bitmap mBitmap=Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);



        return mBitmap;
    }



    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgdrawable = view.getBackground();
        if (bgdrawable != null) {
            bgdrawable.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return bitmap;
    }

    private void printpicCode(final Bitmap printBmp) {
        printingDialog.show();
       binder.writeDataByYouself(new UiExecute() {
            public void onsucess() {
                printingDialog.dismiss();
            }

            public void onfailed() {
                Log.i("Bluetooth print ==>", "Failed");
            }
        }, new ProcessData() {
            public List<byte[]> processDataBeforeSend() {
                List<byte[]> list = new ArrayList();
                list.add(DataForSendToPrinterPos80.initializePrinter());
                list.add(DataForSendToPrinterPos80.printRasterBmp(0, printBmp, BitmapToByteData.BmpType.Threshold, BitmapToByteData.AlignType.Center, 576));
                list.add(DataForSendToPrinterPos80.printAndFeedForward(1));
                list.add(DataForSendToPrinterPos80.selectCutPagerModerAndCutPager(66, 1));
                return list;
            }
        });
    }

    private void print() {
        try {
            b1 = convertGreyImg(getBitmapFromView(scrollView, scrollView.getChildAt(0).getHeight(), scrollView.getChildAt(0).getWidth()));
            Tiny.getInstance().source(b1).asBitmap().withOptions(new Tiny.BitmapCompressOptions()).compress(new BitmapCallback() {
                @Override
                public void callback(boolean isSuccess, Bitmap bitmap, Throwable t) {
                    if (isSuccess) {
                        b2 = resizeImage(b1, 550, false);
                        Message message = new Message();
                        message.what = 1;
                        handler.handleMessage(message);

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addRecent() {

            db.insertRecent(orderList);
            db.deleteOrder(orderList);
            orderList.clear();
            vouncherAdapter.notifyDataSetChanged();
            getTotalCost();

    }
}
