package com.example.triviaapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.triviaapp.R;
import com.example.triviaapp.activities.MainActivity;
import com.example.triviaapp.model.UserInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    private MainActivity activity;
    private RadioGroup mRadioGroup;
    private UserInfo item;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity.setActionBar(true, getString(R.string.question_2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
    }

    /*bind view by IDs*/
    private void findViewById(View view) {

        mRadioGroup = view.findViewById(R.id.radio_group);

        view.findViewById(R.id.btn_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(getCricketerName())) {
                    Toast.makeText(activity, "Please select your answer...", Toast.LENGTH_SHORT).show();
                    return;
                }
                ThirdFragment thirdFragment = new ThirdFragment();
                item.setAnswer1(getCricketerName());
                thirdFragment.setUserInfo(item);
                activity.addFragment(thirdFragment);
            }
        });
    }

    /*it will return selected name */
    public String getCricketerName() {

        String name = "";
        int selectedId = mRadioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.radio_1) {
            name = "Sachin Tendulkar";
        } else if (selectedId == R.id.radio_2) {
            name = "Virat Kohli";
        } else if (selectedId == R.id.radio_3) {
            name = "Adam Grilchrist";
        } else if (selectedId == R.id.radio_4) {
            name = "Jacques Kallis";
        }
        return name;
    }

    // bind previous fragment data
    public void setUserInfo(UserInfo item) {
        this.item = item;
    }
}
