package abc.home.zarni.evermore.adapter.main_adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import abc.home.zarni.evermore.BuildConfig;
import abc.home.zarni.evermore.R;
import abc.home.zarni.evermore.database.DatabaseHelper;
import abc.home.zarni.evermore.database.model.Grill;
import abc.home.zarni.evermore.database.model.Soup;
import abc.home.zarni.evermore.utils.Counter;
import abc.home.zarni.evermore.utils.Utils;
import me.myatminsoe.mdetect.MDetect;

public class Main_Grill_Adapter  extends RecyclerView.Adapter<Main_Grill_Adapter.MyViewHolder> {

    private Context context;
    private List<Grill> notesList;
    private DatabaseHelper db;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView type;
        public TextView price;
        public ImageView img;
        public Button add;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            type = view.findViewById(R.id.type);
            price = view.findViewById(R.id.price);
            img = view.findViewById(R.id.image);
            add = view.findViewById(R.id.add);

            db = new DatabaseHelper(context);
            Glide.with(context).load(R.drawable.skewer).into(img);

//            name.setTypeface(Utils.getMMZawGyiTypeface(context));
//            type.setTypeface(Utils.getMMZawGyiTypeface(context));
//            price.setTypeface(Utils.getMMZawGyiTypeface(context));
//            add.setTypeface(Utils.getMMTypeface(context));
//            add.setText("ထည့်မယ်");
            add.setText(MDetect.INSTANCE.getText("ထည့်မယ်"));
        }
    }


    public Main_Grill_Adapter(Context context, List<Grill> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public Main_Grill_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item, parent, false);

        return new Main_Grill_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Main_Grill_Adapter.MyViewHolder holder, int position) {
        final Grill note = notesList.get(position);

        holder.name.setText(note.getName());
        holder.type.setText(note.getType());
        holder.price.setText(note.getPrice());

        final Dialog d = new Dialog(context);
        d.setContentView(R.layout.order_dialog);
        d.setCancelable(false);

        FloatingActionButton minus = d.findViewById(R.id.minus);
        FloatingActionButton plus = d.findViewById(R.id.plus);
        final TextView num = d.findViewById(R.id.num);
        Button ok = d.findViewById(R.id.okay);
        ok.setText(MDetect.INSTANCE.getText("သေချာပြီ"));

        ImageView close = d.findViewById(R.id.close);
        TextView title = d.findViewById(R.id.title);
        title.setText(MDetect.INSTANCE.getText("အရေအတွက်ထည့်ပါ"));

        final Counter count = new Counter(0);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count.getValue() == 0){
                    num.setText("0");
                    return;
                }
                count.minus();
                num.setText(count.getValue() + BuildConfig.FLAVOR);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count.pulus();
                num.setText(count.getValue() + BuildConfig.FLAVOR);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
                count.reset();
                num.setText("0");

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count.getValue() != 0){
                    db.insertOrder(note.getName(),note.getType(),note.getPrice(),num.getText().toString());
                    d.dismiss();
                    count.reset();
                    num.setText("0");
                }
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

}