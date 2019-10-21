package com.nguyenoanh.downloadfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemUserAdapter extends ArrayAdapter {

    List list = new ArrayList ();


    public ItemUserAdapter(@NonNull Context context, int resource) {
        super (context, resource);
    }

    public void add(ItemUser object) {
        super.add (object);
        list.add (object);
    }

    @Override
    public int getCount() {
        return list.size ();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get (position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        view = convertView;

        ItemUserAdapterHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext ().getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate (R.layout.item_list, parent, false);

            holder = new ItemUserAdapterHolder ();
            holder.tv_id = view.findViewById (R.id.tv_id);
            holder.tv_name = view.findViewById (R.id.tv_name);
            holder.tv_email = view.findViewById (R.id.tv_email);
            holder.tv_address = view.findViewById (R.id.tv_address);
            holder.tv_phone = view.findViewById (R.id.tv_phone);

            view.setTag (holder);
        } else {
            holder = (ItemUserAdapterHolder) view.getTag ();
        }

        ItemUser itemUser = (ItemUser) this.getItem (position);

        holder.tv_id.setText (itemUser.getId ());
        holder.tv_name.setText (itemUser.getName ());
        holder.tv_email.setText (itemUser.getEmail ());
        holder.tv_address.setText (itemUser.getAddress ());
        holder.tv_phone.setText (itemUser.getPhone ());

        return view;
    }

    static class ItemUserAdapterHolder{
        TextView tv_id, tv_name, tv_email,tv_address, tv_phone;

    }
}
