package abc.home.zarni.evermore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import abc.home.zarni.evermore.R;
import abc.home.zarni.evermore.database.model.Salad;
import abc.home.zarni.evermore.database.model.Soup;
import abc.home.zarni.evermore.utils.Utils;

public class Salad_Adapter extends RecyclerView.Adapter<Salad_Adapter.MyViewHolder> {

    private Context context;
    private List<Salad> notesList;

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


    public Salad_Adapter(Context context, List<Salad> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public Salad_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_item, parent, false);

        return new Salad_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Salad_Adapter.MyViewHolder holder, int position) {
        Salad note = notesList.get(position);

        holder.name.setText(note.getName());
        holder.type.setText(note.getType());
        holder.price.setText(note.getPrice());

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

}