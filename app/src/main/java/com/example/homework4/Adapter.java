package com.example.homework4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.RecyclerViewHolder> {

    public List<TaskModel> list = new ArrayList<>();
    private LayoutInflater inflater;

    public void setListener(Interface listener) {
        this.listener = listener;
    }

    private Interface listener;

    public Adapter (List<TaskModel> list, Context context){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    public void dataTry (int position, TaskModel model){
        list.set(position, model);
       notifyDataSetChanged();
    }

    public void delete (int position){
        list.remove(position);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new RecyclerViewHolder(view);
    }
    public  void addTask(TaskModel model){
        list.add(model);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.txt_title.setText(list.get(position).getTitle());
        holder.txt_description.setText(list.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title, txt_description;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.item_title);
            txt_description = itemView.findViewById(R.id.item_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(itemView.getContext()).create();
                    dialog.setTitle("ВНИМАНИЕ");
                    dialog.setMessage("ТОЧНО УДАЛИТЬ????");

                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delete(getAdapterPosition());
                        }
                    });
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                    return false;

                }
            });
        }

    }

}
