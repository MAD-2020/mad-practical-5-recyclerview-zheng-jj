package sg.edu.np.mad.mad_recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterToDoList extends RecyclerView.Adapter<AdapterToDoList.ViewHolder>
{
    private static final String TAG = "MainAdapterToDoList";
    List<String> mItemList = new ArrayList<String>(){};
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        TextView itemname;
        CheckBox itemcheck;
        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.list_item);
            itemname = itemView.findViewById(R.id.item_name);
            itemcheck = itemView.findViewById(R.id.item_check);
        }
    }
    public AdapterToDoList(List<String> mItemList) {
        this.mItemList = mItemList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final AdapterToDoList.ViewHolder holder, final int position) {
        holder.itemname.setText(mItemList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int itemPosition = position;
                String item = mItemList.get(holder.getAdapterPosition());
                Log.v(TAG, "remove item "+ item);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete");
                builder.setIcon(R.drawable.icontestbuilder);
                builder.setMessage("Are you sure you want to delete \" " + item+ " \"");
                builder.setCancelable(true);
                Log.v(TAG, "total size of list items = "+mItemList.size());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.v(TAG,"user chose to remove");
                        mItemList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyItemRangeChanged(holder.getAdapterPosition(), mItemList.size());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.v(TAG,"user chose not to remove");
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
