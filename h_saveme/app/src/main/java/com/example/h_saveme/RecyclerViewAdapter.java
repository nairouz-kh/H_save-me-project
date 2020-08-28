package com.example.h_saveme;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    Context context;
    List<Item> items;
    List<Item> itemsFinal;
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//
//                String searchKeyWord = constraint.toString().toLowerCase();
//
//                if (searchKeyWord.isEmpty())
//                {
//                    itemsFinal = items;
//                }
//                else
//                {
//                    List<Item> itemsFiltered = new ArrayList<>();
//                    for (Item item : items) {
//                        if (item.getName().toLowerCase().contains(searchKeyWord) ||
//                                item.getAddress().toLowerCase().contains(searchKeyWord)) {
//                            itemsFiltered.add(item);
//                        }
//                    }
//                    itemsFinal = itemsFiltered;
//                }
//          //      FilterResults filterResults = new FilterResults();
//           //     filterResults.values = itemsFinal;
//
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                itemsFinal=(ArrayList<Item>) results.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String searchKeyWord = constraint.toString().toLowerCase();

                if (searchKeyWord.isEmpty())
                {
                    itemsFinal = items;
                }
                else
                {
                    List<Item> itemsFiltered = new ArrayList<>();
                    for (Item item : items) {
                        if (item.getName().toLowerCase().contains(searchKeyWord) ||
                                item.getName().toLowerCase().contains(searchKeyWord)) {
                            itemsFiltered.add(item);
                        }
                    }
                    itemsFinal = itemsFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = itemsFinal;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemsFinal = (ArrayList<Item>) results.values;
                notifyDataSetChanged();

            }
        } ;
    }

    public RecyclerViewAdapter(List<Item> items) {
        this.items = items;
        itemsFinal=items;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row,parent,false);
        //context = parent.getContext();

//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view = layoutInflater.inflate(R.layout.item_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemsFinal.get(position);
        // final Item item = items.get(position);
        holder.nameTextView.setText(item.getName());
        //  holder.statusTextView.setText(item.getStatus());
        //  holder.addressTextView.setText(item.getAddress());

    }



//    public void refreshList(){
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return itemsFinal.size();
    }

    public   class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        // TextView statusTextView;
        //   TextView addressTextView;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            //  statusTextView = itemView.findViewById(R.id.status);
            // addressTextView = itemView.findViewById(R.id.address);

        }
    }



}
