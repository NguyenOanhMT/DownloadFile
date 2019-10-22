package com.nguyenoanh.downloadfile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenoanh.downloadfile.Activity.MainActivity;
import com.nguyenoanh.downloadfile.Model.ItemUser;

import java.util.List;

public class ItemUserAdapter extends RecyclerView.Adapter<ItemUserAdapter.ViewHolder> {

    private Context context;
    private List<ItemUser> list;

    public ItemUserAdapter(Context context, List<ItemUser> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from (parent.getContext ());
        view = inflater.inflate (R.layout.item_list, parent, false);
        final ViewHolder viewHolder = new ViewHolder (view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("id",list.get(viewHolder.getAdapterPosition()).getId ());
                i.putExtra("name",list.get(viewHolder.getAdapterPosition()).getName ());
                i.putExtra("email",list.get(viewHolder.getAdapterPosition()).getEmail ());
                i.putExtra("address",list.get(viewHolder.getAdapterPosition()).getAddress ().toString ());
                i.putExtra("phone",list.get(viewHolder.getAdapterPosition()).getPhone ());

                context.startActivity(i);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_id.setText ("ID: " + list.get (position).getId ());
        if ( list.get (position).getName ().length () >= 12) {
            holder.tv_name.setText ("Name: " + list.get (position).getName ().substring (0,12) + " ...");
        } else {
            holder.tv_name.setText ("Name: " + list.get (position).getName ());
        }
        if ( list.get (position).getEmail ().length () >= 12) {
            holder.tv_email.setText ("Email: " + list.get (position).getEmail ().substring (0,11) + " ...");
        } else {
            holder.tv_email.setText ("Email: " + list.get (position).getEmail ());
        }
        if ( list.get (position).getEmail ().length () >= 12) {
            holder.tv_address.setText ("Address: " + list.get (position).getAddress ().toString ().substring (0,11) + " ...");
        } else {
            holder.tv_address.setText ("Address: " + list.get (position).getAddress ().toString ());
        }
        holder.tv_phone.setText ("Phone: " + list.get (position).getPhone ());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id, tv_name, tv_email, tv_address, tv_phone;
        LinearLayout view_container;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            view_container = itemView.findViewById (R.id.item_user);
            tv_id = itemView.findViewById (R.id.tv_id);
            tv_name = itemView.findViewById (R.id.tv_name);
            tv_email = itemView.findViewById (R.id.tv_email);
            tv_address = itemView.findViewById (R.id.tv_address);
            tv_phone = itemView.findViewById (R.id.tv_phone);
        }
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

}
