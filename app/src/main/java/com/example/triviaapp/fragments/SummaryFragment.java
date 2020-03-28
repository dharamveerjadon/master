package com.example.triviaapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.triviaapp.Database.DBManager;
import com.example.triviaapp.R;
import com.example.triviaapp.activities.MainActivity;
import com.example.triviaapp.model.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    private TextView txtName, txtAnswer1, txtAnswer2;
    private UserInfo item;
    private DBManager dbManager;
    private MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity.setActionBar(true, getString(R.string.summary));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
    }

    /*bind view with id*/
    private void findViewById(View view) {
        dbManager = new DBManager(getActivity());
        dbManager.open();

        txtName = view.findViewById(R.id.txt_name);
        txtAnswer1 = view.findViewById(R.id.txt_answer1);
        txtAnswer2 = view.findViewById(R.id.txt_answer2);

        txtName.setText(getString(R.string.name) + " " + item.getName());
        txtAnswer1.setText(getString(R.string.answer) + " " + item.getAnswer1());
        txtAnswer2.setText(getString(R.string.answer) + " " + item.getAnswer2());
        view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLocalBroadcast();
                dbManager.insert(item.getName(), item.getAnswer1(), item.getAnswer2(), getCurrentDateTime());
                activity.removeAllFragments(activity.getSupportFragmentManager());
            }
        });
    }

    public void setUserInfo(UserInfo item) {
        this.item = item;

    }

    /*it will return current date and time*/
    private String getCurrentDateTime() {
        String pattern = "dd MMMM yyyy hh:mm:ss a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        return date;
    }

    //send broadcast to notify game is finished
    private void sendLocalBroadcast() {
        Intent intent = new Intent("GAME_COMPLETE");
        intent.putExtra("FINISHED", "finished");
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
    }
}
