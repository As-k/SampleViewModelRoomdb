package com.myapplication.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.myapplication.BR;
import com.myapplication.R;
import com.myapplication.dao.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {
    //    List<Note> allNotes = new ArrayList<>();
    private OnItemClickListener listener;

//    protected NoteAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback) {
//        super(diffCallback);
//    }

    public NoteAdapter(OnItemClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<Note> diffCallback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldNote, @NonNull Note newNote) {
            return oldNote.getId() == newNote.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldNote, @NonNull Note newNote) {
            return oldNote.getTitle().equals(newNote.getTitle()) &&
                    oldNote.getDescription().equals(newNote.getDescription()) &&
                    oldNote.getPriority() == newNote.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.note_item, viewGroup, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int position) {
        Note note = getItem(position);
        noteViewHolder.binding.setVariable(BR.note, note);
        noteViewHolder.binding.setVariable(BR.clicker, listener);
        noteViewHolder.binding.executePendingBindings();
    }

    /*@Override
    public int getItemCount() {
        return allNotes.size();
    }*/

   /* public void setAllNotes(List<Note> notes) {
        this.allNotes = notes;
        notifyDataSetChanged();
    }*/

    public Note getNoteAt(int postion) {
        return getItem(postion);
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        public NoteViewHolder(@NonNull ViewDataBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

}
