package com.example.notestaking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notestaking.Models.Notes;
import com.example.notestaking.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.viewHolder> {
    Context context;
    List<Notes> notesList;

    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
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
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

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
