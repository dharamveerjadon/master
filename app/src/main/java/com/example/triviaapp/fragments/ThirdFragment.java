package com.example.triviaapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.triviaapp.R;
import com.example.triviaapp.activities.MainActivity;
import com.example.triviaapp.model.UserInfo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    private MainActivity activity;
    private CheckBox mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4;
    private UserInfo item;
    private ArrayList<String> colorAnswerList;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity.setActionBar(true,getString(R.string.question_3));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
    }

    private void findViewById(View view) {

        colorAnswerList = new ArrayList<>();
        mCheckBox1 = view.findViewById(R.id.checkbox_white);
        mCheckBox2 = view.findViewById(R.id.checkbox_yellow);
        mCheckBox3 = view.findViewById(R.id.checkbox_orange);
        mCheckBox4 = view.findViewById(R.id.checkbox_green);



        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSelectedItem();
                if (colorAnswerList.size() == 0) {
                    Toast.makeText(activity, "Please select multiple choice...", Toast.LENGTH_SHORT).show();
                    return;
                }
                SummaryFragment summaryFragment = new SummaryFragment();
                item.setAnswer2(TextUtils.join(",", colorAnswerList));
                summaryFragment.setUserInfo(item);
                activity.addFragment(summaryFragment);
            }
        });

        view.findViewById(R.id.btn_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }
    // bind previous fragment data
    public void setUserInfo(UserInfo item) {
        this.item = item;
    }

    // handle and insert checkbox selection into Listview
    private void checkSelectedItem() {
        if (mCheckBox1.isChecked())
            colorAnswerList.add("White");
        else
            colorAnswerList.remove("White");

        if (mCheckBox2.isChecked())
            colorAnswerList.add("Yellow");
        else
            colorAnswerList.remove("Yellow");

        if (mCheckBox3.isChecked())
            colorAnswerList.add("Orange");
        else
            colorAnswerList.remove("Orange");

        if (mCheckBox4.isChecked())
            colorAnswerList.add("Green");
        else
            colorAnswerList.remove("Green");


    }
}
