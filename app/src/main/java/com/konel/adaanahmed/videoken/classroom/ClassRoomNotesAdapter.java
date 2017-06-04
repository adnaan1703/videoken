package com.konel.adaanahmed.videoken.classroom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.konel.adaanahmed.videoken.CodeUtil;
import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.db.Note;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 2:40 PM
 */


class ClassRoomNotesAdapter extends RecyclerView.Adapter<ClassRoomNotesAdapter.ViewHolder> {

    private ArrayList<Note> notes;
    private ClassRoomNotesInteractionListener listener;

    ClassRoomNotesAdapter(ArrayList<Note> notes, ClassRoomNotesInteractionListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateUI(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    void addNote(@NonNull Note note) {
        notes.add(note);
        notifyDataSetChanged();
    }

    interface ClassRoomNotesInteractionListener {
        void onNoteClicked(int startTime);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.note_list_item_text)
        TextView noteText;
        @BindView(R.id.note_list_item_time)
        TextView noteTime;

        private Note note;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void updateUI(Note note) {
            this.note = note;
            noteText.setText(note.getText());
            noteTime.setText(CodeUtil.millisToString(note.getTime()));
        }

        @Override
        public void onClick(View view) {
            if (listener != null)
                listener.onNoteClicked(note.getTime());
        }
    }
}
