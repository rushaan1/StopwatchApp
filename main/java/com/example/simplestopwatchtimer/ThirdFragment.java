package com.example.simplestopwatchtimer;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView time;
    TextView date;
    TextView year;
    String currentDate;
    String currentTime;
    Spinner spn;
    String format = "";
    TextView ampm;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */


    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.frag3_layout, container, false);
        ampm = view.findViewById(R.id.ampm);

        spn = (Spinner) view.findViewById(R.id.choose);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.format, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);

        ConstraintLayout f3 = view.findViewById(R.id.frag3);
        AnimationDrawable anim = (AnimationDrawable) f3.getBackground();
        anim.setEnterFadeDuration(3000);
        anim.setExitFadeDuration(4000);
        anim.start();

        time = view.findViewById(R.id.clock);
        date = view.findViewById(R.id.date);
        year = view.findViewById(R.id.year);
        timeUpdate();
        setHasOptionsMenu(true);


        return view;
    }


    private void timeUpdate(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                currentDate = new SimpleDateFormat("EEEE, d, MMM", Locale.getDefault()).format(new Date());
                if (format.equals("24-Hour Format")){
                    currentTime = new SimpleDateFormat("H:mm:ss", Locale.forLanguageTag(Time.getCurrentTimezone())).format(new Date());
                    time.setText("  "+currentTime);
                    time.setTextSize(65);
                    ampm.setVisibility(View.INVISIBLE);
                }
                else{
                    currentTime = new SimpleDateFormat("h:mm:ss", Locale.forLanguageTag(Time.getCurrentTimezone())).format(new Date());
                    time.setText("   "+currentTime);
                    ampm.setText(new SimpleDateFormat("a",Locale.forLanguageTag(Time.getCurrentTimezone())).format(new Date()));
                    ampm.setVisibility(View.VISIBLE);
                }
                System.out.println("1"+Locale.getDefault().toString());
                System.out.println("2"+Time.getCurrentTimezone().toString());
                date.setText(" "+currentDate);
                year.setText("   "+new SimpleDateFormat("yyyy", Locale.forLanguageTag(Time.getCurrentTimezone())).format(new Date()));
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        format = (String) parent.getItemAtPosition(position);
        System.out.println(format);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}