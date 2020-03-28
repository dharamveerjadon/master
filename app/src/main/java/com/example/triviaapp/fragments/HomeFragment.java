package com.example.triviaapp.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.triviaapp.R;
import com.example.triviaapp.activities.MainActivity;
import com.example.triviaapp.model.UserInfo;

public class HomeFragment extends Fragment {

    private EditText mEtEnterName;
    private MainActivity activity;
    private UserInfo item;
    /*it will recieve intent when game is finished*/
    private BroadcastReceiver gameCompletedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            activity.setActionBar(false, getString(R.string.app_name));
            mEtEnterName.setText("");
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Register local broadcast to handle game completion fire
        LocalBroadcastManager.getInstance(activity).registerReceiver(gameCompletedReceiver,
                new IntentFilter("GAME_COMPLETE"));
        findViewById(view);
    }

    private void findViewById(View view) {
        item = new UserInfo();
        mEtEnterName = view.findViewById(R.id.et_entername);
        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(mEtEnterName.getText().toString())) {
                    mEtEnterName.setError("Please enter your name..");
                    return;
                } else {
                    mEtEnterName.setError(null);
                }
                SecondFragment fragment = new SecondFragment();
                item.setName(mEtEnterName.getText().toString().trim());
                fragment.setUserInfo(item);
                activity.addFragment(fragment);

            }
        });
    }

    @Override
    public void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(activity).unregisterReceiver(gameCompletedReceiver);
        super.onDestroy();
    }
}
