package abc.home.zarni.evermore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import abc.home.zarni.evermore.R;
import abc.home.zarni.evermore.database.model.Coffee;
import abc.home.zarni.evermore.database.model.Drink;
import abc.home.zarni.evermore.utils.Utils;

public class Drink_Adapter extends RecyclerView.Adapter<Drink_Adapter.MyViewHolder> {

    private Context context;
    private List<Drink> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView type;
        public TextView price;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.table_name);
            type = view.findViewById(R.id.table_type);
            price = view.findViewById(R.id.table_price);

            name.setTypeface(Utils.getMMZawGyiTypeface(context));
            type.setTypeface(Utils.getMMZawGyiTypeface(context));
            price.setTypeface(Utils.getMMZawGyiTypeface(context));
        }
    }


    public Drink_Adapter(Context context, List<Drink> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public Drink_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_item, parent, false);

        return new Drink_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Drink_Adapter.MyViewHolder holder, int position) {
        Drink note = notesList.get(position);

        holder.name.setText(note.getName());
        holder.type.setText(note.getType());
        holder.price.setText(note.getPrice());

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

}