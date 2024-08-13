package com.bakhdev.notesapp.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bakhdev.notesapp.databinding.NoteItemBinding;
import com.bakhdev.notesapp.domain.model.Note;

public class NotesAdapter extends ListAdapter<Note, NotesAdapter.ViewHolder> {
    private OnItemClickListener itemListener;

    public NotesAdapter(OnItemClickListener onItemClickListener) {
        super(NoteDiffCallback);
        this.itemListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final NoteItemBinding binding;

        ViewHolder(NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Note note) {
            binding.titleTv.setText(note.getTitle());
            binding.descTv.setText(note.getDesc());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListener.onItemClick(note);
                }
            });
        }
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NoteItemBinding binding = NoteItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.bind(note);
    }

    private static final DiffUtil.ItemCallback<Note> NoteDiffCallback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.equals(newItem);
        }
    };
}


