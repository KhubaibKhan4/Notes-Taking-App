package com.example.notestaking.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notestaking.Common.DatabaseHelper;
import com.example.notestaking.Models.Notes;
import com.example.notestaking.NotesDetailedActivity;
import com.example.notestaking.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("RecyclerView")
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.viewHolder> implements Filterable {
    Context context;
    List<Notes> notesList;
    List<Notes> backup;
    private AlertDialog dialog;

    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
        backup = new ArrayList<>(notesList);
    }

    public void setFilteredList(List<Notes> filteredList) {
        this.notesList = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.sample_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(notesList.get(position).getTitle());
        holder.desc.setText(notesList.get(position).getDesc());
        holder.date.setText(notesList.get(position).getDate());
        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NotesDetailedActivity.class);
                intent.putExtra("title", notesList.get(position).getTitle());
                intent.putExtra("description", notesList.get(position).getDesc());
                intent.putExtra("date", notesList.get(position).getDate());
                intent.putExtra("id", notesList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Note").setMessage("Do You Want to Delete this Note Permanently?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseHelper databaseHelper = DatabaseHelper.getDB(context);
                        databaseHelper.notesDao().deleteNotes(new Notes(notesList.get(position).getId(), notesList.get(position).getTitle(), notesList.get(position).getDesc(), notesList.get(position).getDate()));
                        notesList.remove(position);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_notes, null);
                        builder.setView(dialogView);

                        // get references to the EditText views and button
                        EditText editTextTitle = dialogView.findViewById(R.id.edittext_title);
                        EditText editTextDescription = dialogView.findViewById(R.id.edittext_description);
                        Button buttonUpdate = dialogView.findViewById(R.id.button_update);

                        // set the current note data to the EditText views
                        editTextTitle.setText(notesList.get(position).getTitle());
                        editTextDescription.setText(notesList.get(position).getDesc());

                        // set the click listener for the update button
                        buttonUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                DatabaseHelper databaseHelper = DatabaseHelper.getDB(context);

                                // get the updated note data from the EditText views
                                String updatedTitle = editTextTitle.getText().toString();
                                String updatedDescription = editTextDescription.getText().toString();

                                // update the current note with the new data
                                databaseHelper.notesDao().updateNotes(new Notes(notesList.get(position).getId(), updatedTitle, updatedDescription, notesList.get(position).getDate()));

                                // update the UI to reflect the changes
                                notifyDataSetChanged();
                                dialog.dismiss();

                            }
                        });

                        // show the dialog
                        dialog = builder.create();
                        dialog.show();
                    }
                }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            List<Notes> filtereddata = new ArrayList<>();
            if (keyword.toString().isEmpty()) {
                filtereddata.addAll(backup);
            } else {
                for (Notes notes : backup) {
                    if (notes.getTitle().toString().toLowerCase().contains(keyword.toString().toLowerCase())) {
                        filtereddata.add(notes);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filtereddata;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notesList.clear();
            notesList.addAll((List<Notes>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    class viewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, date;
        CardView notes_container;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_txt);
            desc = itemView.findViewById(R.id.desc_txt);
            date = itemView.findViewById(R.id.textView_date);
            notes_container = itemView.findViewById(R.id.notes_container);
        }
    }


}
