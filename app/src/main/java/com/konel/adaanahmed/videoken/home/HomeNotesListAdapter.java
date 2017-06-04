package com.konel.adaanahmed.videoken.home;

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
 * @since : 04 Jun 2017 6:35 PM
 */


class HomeNotesListAdapter extends RecyclerView.Adapter<HomeNotesListAdapter.ViewHolder> {

    private String videoId;
    private ArrayList<Note> notes;
    private HomeNotesInteractionListener interactionListener;

    HomeNotesListAdapter(@NonNull String videoId,
                         @NonNull ArrayList<Note> notes,
                         @NonNull HomeNotesInteractionListener interactionListener) {
        this.videoId = videoId;
        this.notes = notes;
        this.interactionListener = interactionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_note_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateUI(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    interface HomeNotesInteractionListener {
        void onNoteSelected(String videoId, Note note);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.home_note_item_text)
        TextView tvText;
        @BindView(R.id.home_note_item_time)
        TextView tvTime;

        private Note note;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void updateUI(Note note) {
            this.note = note;
            tvText.setText(note.getText());
            tvTime.setText(CodeUtil.millisToString(note.getTime()));
        }

        @Override
        public void onClick(View view) {
            if (interactionListener != null)
                interactionListener.onNoteSelected(videoId, note);
        }
    }
}
