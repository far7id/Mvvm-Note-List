package com.example.mvvmrecycler.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmrecycler.R;
import com.example.mvvmrecycler.Service.Model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder>
{
    private List<Note> notes = new ArrayList<>();
    private OnItemclickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position)
    {
        Note currentNote = notes.get(position);
        holder.txtTitle.setText(currentNote.getTitle());
        holder.txtDescription.setText(currentNote.getDescription());
        holder.txtPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount()
    {
        return notes.size();
    }

    public void setNotes(List<Note> notes)
    {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position)
    {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder
    {
        private TextView txtTitle;
        private TextView txtDescription;
        private TextView txtPriority;

        public NoteHolder(View itemView)
        {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_view_title);
            txtDescription = itemView.findViewById(R.id.txt_view_description);
            txtPriority = itemView.findViewById(R.id.txt_view_priority);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(notes.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemclickListener
    {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemclickListener listener)
    {
        this.listener = listener;
    }
}
