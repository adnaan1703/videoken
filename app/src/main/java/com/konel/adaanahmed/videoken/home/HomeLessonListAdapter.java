package com.konel.adaanahmed.videoken.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.db.Lesson;
import com.konel.adaanahmed.videoken.db.Note;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 7:28 PM
 */


class HomeLessonListAdapter extends RecyclerView.Adapter<HomeLessonListAdapter.ViewHolder> {

    private ArrayList<Lesson> lessons;
    private HomeNotesListAdapter.HomeNotesInteractionListener listener;

    public HomeLessonListAdapter(@NonNull ArrayList<Lesson> lessons,
                                 @NonNull HomeNotesListAdapter.HomeNotesInteractionListener listener) {
        this.lessons = lessons;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_home_lesson_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateUI(lessons.get(position));
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lesson_item_title)
        TextView tvTitle;
        @BindView(R.id.lesson_item_counter)
        TextView tvCounter;
        @BindView(R.id.lesson_item_notes_list)
        RecyclerView rvNotes;
        @BindView(R.id.lesson_item_no_notes)
        TextView tvNoNotes;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void updateUI(Lesson lesson) {
            tvTitle.setText(lesson.getVideoId());
            tvCounter.setText(String.valueOf(lesson.getNotes().size()));
            if (lesson.getNotes().size() > 0) {
                rvNotes.setVisibility(View.VISIBLE);
                tvNoNotes.setVisibility(View.GONE);
                rvNotes.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
                        LinearLayoutManager.HORIZONTAL, false));
                rvNotes.setAdapter(new HomeNotesListAdapter(lesson.getVideoId(),
                        transMutateToArrayList(lesson.getNotes()), listener));
            } else {
                rvNotes.setVisibility(View.GONE);
                tvNoNotes.setVisibility(View.VISIBLE);
            }
        }

        @NonNull
        private ArrayList<Note> transMutateToArrayList(RealmList<Note> notes) {
            ArrayList<Note> noteArrayList = new ArrayList<>();
            for (Note note : notes)
                noteArrayList.add(note);
            return noteArrayList;

        }
    }
}
