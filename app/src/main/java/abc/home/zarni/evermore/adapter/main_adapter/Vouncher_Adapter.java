package abc.home.zarni.evermore.adapter.main_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import abc.home.zarni.evermore.R;
import abc.home.zarni.evermore.adapter.Coffee_Adapter;
import abc.home.zarni.evermore.database.model.Coffee;
import abc.home.zarni.evermore.database.model.TempOrder;
import abc.home.zarni.evermore.utils.Utils;

public class Vouncher_Adapter extends RecyclerView.Adapter<Vouncher_Adapter.MyViewHolder> {

    private Context context;
    private List<TempOrder> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView type;
        public TextView qty;
        public TextView op_price;
        public TextView mul_pirce;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            qty = view.findViewById(R.id.count);
            type = view.findViewById(R.id.type);
            op_price = view.findViewById(R.id.price);
            mul_pirce = view.findViewById(R.id.amount);

//            name.setTypeface(Utils.getMMZawGyiTypeface(context));
//            type.setTypeface(Utils.getMMZawGyiTypeface(context));
//            qty.setTypeface(Utils.getMMZawGyiTypeface(context));
//            op_price.setTypeface(Utils.getMMZawGyiTypeface(context));
//            mul_pirce.setTypeface(Utils.getMMZawGyiTypeface(context));
        }
    }


    public Vouncher_Adapter(Context context, List<TempOrder> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public Vouncher_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vouncher_item, parent, false);

        return new Vouncher_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Vouncher_Adapter.MyViewHolder holder, int position) {
        TempOrder note = notesList.get(position);

        int total = Integer.parseInt(note.getCount()) * Integer.parseInt(note.getPrice());
         holder.name.setText(note.getName());
         holder.type.setText(note.getType());
         holder.qty.setText(note.getCount());
         holder.op_price.setText(note.getPrice());
         holder.mul_pirce.setText(total + "");

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

}