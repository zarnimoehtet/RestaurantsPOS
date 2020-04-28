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
import abc.home.zarni.evermore.database.model.Food;
import abc.home.zarni.evermore.utils.Utils;

public class Food_Adapter  extends RecyclerView.Adapter<Food_Adapter.MyViewHolder> {

    private Context context;
    private List<Food> notesList;

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


    public Food_Adapter(Context context, List<Food> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public Food_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_item, parent, false);

        return new Food_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Food_Adapter.MyViewHolder holder, int position) {
        Food note = notesList.get(position);

        holder.name.setText(note.getName());
        holder.type.setText(note.getType());
        holder.price.setText(note.getPrice());

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

}