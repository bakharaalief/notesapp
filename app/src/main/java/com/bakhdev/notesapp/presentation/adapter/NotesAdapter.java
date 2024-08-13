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
    private final OnItemClickListener itemListener;
    private final OnItemClickListener deleteListener;

    public NotesAdapter(
            OnItemClickListener onItemClickListener,
            OnItemClickListener onDeleteClickListener
    ) {
        super(NoteDiffCallback);
        this.itemListener = onItemClickListener;
        this.deleteListener = onDeleteClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final NoteItemBinding binding;

        ViewHolder(NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int index, Note note) {
            //handle change card
            if (note.isShowDelete()) {
                binding.noteCard.setVisibility(View.GONE);
                binding.noteDeleteCard.setVisibility(View.VISIBLE);
            } else {
                binding.noteCard.setVisibility(View.VISIBLE);
                binding.noteDeleteCard.setVisibility(View.GONE);
            }
            binding.titleTv.setText(note.getTitle());
            binding.descTv.setText(note.getDesc());
            binding.noteCard.setOnClickListener(view -> itemListener.onItemClick(note));
            binding.noteCard.setOnLongClickListener(view -> {
                note.setShowDelete(true);
                notifyItemChanged(index);
                return true;
            });
            binding.deleteBtn.setOnClickListener(view -> {
                deleteListener.onItemClick(note);
            });
            binding.cancelBtn.setOnClickListener(view -> {
                note.setShowDelete(false);
                notifyItemChanged(index);
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
        holder.bind(position, note);
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


