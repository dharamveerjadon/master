package com.example.triviaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaapp.R;
import com.example.triviaapp.model.UserInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuizHistoryAdapter extends RecyclerView.Adapter<QuizHistoryAdapter.ViewHolder> {
    private List<UserInfo> items;
    private int itemLayout;

    public QuizHistoryAdapter(List<UserInfo> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserInfo item = items.get(position);
        holder.txtGameName.setText("Game"+item.getId()+" : " + item.getDateTime());
        holder.txtName.setText("Name:  " + item.getName());
        holder.txtAnswer1.setText("Answer1: " + item.getAnswer1());
        holder.txtAnswer2.setText("Answer2: " + item.getAnswer2());
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtGameName, txtName, txtQuestion1, txtAnswer1, txtQuestion2, txtAnswer2;

        public ViewHolder(View itemView) {
            super(itemView);
            txtGameName = itemView.findViewById(R.id.txt_game_name);
            txtName = itemView.findViewById(R.id.txt_name);
            txtQuestion1 = itemView.findViewById(R.id.txt_question1);
            txtAnswer1 = itemView.findViewById(R.id.txt_answer1);
            txtQuestion2 = itemView.findViewById(R.id.txt_question2);
            txtAnswer2 = itemView.findViewById(R.id.txt_answer2);
        }
    }
}
