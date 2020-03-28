package com.example.triviaapp.fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.triviaapp.Database.DBManager;
import com.example.triviaapp.R;
import com.example.triviaapp.activities.MainActivity;
import com.example.triviaapp.adapters.QuizHistoryAdapter;
import com.example.triviaapp.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MainActivity activity;
    private QuizHistoryAdapter adapter;
    private DBManager dbManager;
    private ImageView noRecordFound;
    private List<UserInfo> mGameList = new ArrayList<>();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity.setActionBar(true, getString(R.string.history));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(view);
    }

    private void findViewById(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        noRecordFound = view.findViewById(R.id.no_record_found);
        dbManager = new DBManager(getActivity());
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        if (cursor != null) {
            // move cursor to first row
            if (cursor.moveToFirst()) {
                do {
                    // Get version from Cursor
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String answer1 = cursor.getString(cursor.getColumnIndex("answer1"));
                    String answer2 = cursor.getString(cursor.getColumnIndex("answer2"));
                    String dateTime = cursor.getString(cursor.getColumnIndex("datetime"));

                    // add the bookName into the bookTitles ArrayList
                    mGameList.add(new UserInfo(String.valueOf(id),name, answer1, answer2,dateTime));
                    // move to next row
                } while (cursor.moveToNext());
            }
        }

        adapter = new QuizHistoryAdapter(mGameList,R.layout.item_game_layout);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(mGameList.size() > 0) {
            noRecordFound.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }else{
            noRecordFound.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }

    }


}
