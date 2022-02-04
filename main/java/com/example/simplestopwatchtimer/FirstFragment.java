package com.example.simplestopwatchtimer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button button;
    Button reset;
    TextView textView;
    int timer = 0;
    boolean started = false;
    int delay = 0;
    Spinner spn;
    int sameDelay = 0;
    Button lap;
    TextToSpeech tts;
    int lapcount = 0;
    TextView lapheader;

    TextView lap1;
    TextView lap2;
    TextView lap3;
    TextView lap4;
    TextView lap5;
    TextView lap6;
    TextView lap7;
    TextView lap8;
    TextView lap9;
    TextView lap10;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     **/
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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
        View view = inflater.inflate(R.layout.frag1_layout, container, false);
        button = (Button) view.findViewById(R.id.button);
        reset = (Button) view.findViewById(R.id.reset);
        textView = (TextView) view.findViewById(R.id.timer);
        spn = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.delay, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);
        setHasOptionsMenu(true);

        ConstraintLayout f1 = view.findViewById(R.id.frag1);
        AnimationDrawable anim = (AnimationDrawable) f1.getBackground();
        anim.setEnterFadeDuration(3000);
        anim.setExitFadeDuration(4000);
        anim.start();

        lap = view.findViewById(R.id.lap);
        lap.setEnabled(false);
        lapheader = view.findViewById(R.id.lapHeader);

        // lap madness
        lap1 = view.findViewById(R.id.lap1);
        lap2 = view.findViewById(R.id.lap2);
        lap3 = view.findViewById(R.id.lap3);
        lap4 = view.findViewById(R.id.lap4);
        lap5 = view.findViewById(R.id.lap5);
        lap6 = view.findViewById(R.id.lap6);
        lap7 = view.findViewById(R.id.lap7);
        lap8 = view.findViewById(R.id.lap8);
        lap9 = view.findViewById(R.id.lap9);
        lap10 = view.findViewById(R.id.lap10);

        lap1.setVisibility(View.INVISIBLE);
        lap2.setVisibility(View.INVISIBLE);
        lap3.setVisibility(View.INVISIBLE);
        lap4.setVisibility(View.INVISIBLE);
        lap5.setVisibility(View.INVISIBLE);
        lap6.setVisibility(View.INVISIBLE);
        lap7.setVisibility(View.INVISIBLE);
        lap8.setVisibility(View.INVISIBLE);
        lap9.setVisibility(View.INVISIBLE);
        lap10.setVisibility(View.INVISIBLE);

        lap.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                lapcount++;
                System.out.println("ON CLICK LISTENER WORKING");
                if (lapcount==1){
                    lapheader.setText("Laps: "+lapcount);
                    lap1.setVisibility(View.VISIBLE);
                    lap1.setText("Lap "+lapcount+" at: "+textView.getText());
                }

                if (lapcount==2){
                    lapheader.setText("Laps: "+lapcount);
                    lap2.setVisibility(View.VISIBLE);
                    lap2.setText("Lap "+lapcount+" at: "+textView.getText());
                }

                if (lapcount==3){
                    lapheader.setText("Laps: "+lapcount);
                    lap3.setVisibility(View.VISIBLE);
                    lap3.setText("Lap "+lapcount+" at: "+textView.getText());
                }

                if (lapcount==4){
                    lapheader.setText("Laps: "+lapcount);
                    lap4.setVisibility(View.VISIBLE);
                    lap4.setText("Lap "+lapcount+" at: "+textView.getText());
                }

                if (lapcount==5){
                    lapheader.setText("Laps: "+lapcount);
                    lap5.setVisibility(View.VISIBLE);
                    lap5.setText("Lap "+lapcount+" at: "+textView.getText());
                }

                if (lapcount==6){
                    lapheader.setText("Laps: "+lapcount);
                    lap6.setVisibility(View.VISIBLE);
                    lap6.setText("Lap "+lapcount+" at: "+textView.getText());
                }

                if (lapcount==7){
                    lapheader.setText("Laps: "+lapcount);
                    lap7.setVisibility(View.VISIBLE);
                    lap7.setText("Lap "+lapcount+" at: "+textView.getText());
                }
                if (lapcount==8){
                    lapheader.setText("Laps: "+lapcount);
                    lap8.setVisibility(View.VISIBLE);
                    lap8.setText("Lap "+lapcount+" at: "+textView.getText());
                }
                if (lapcount==9){
                    lapheader.setText("Laps: "+lapcount);
                    lap9.setVisibility(View.VISIBLE);
                    lap9.setText("Lap "+lapcount+" at: "+textView.getText());
                }
                if (lapcount==10){
                    lapheader.setText("Laps: "+lapcount);
                    lap10.setVisibility(View.VISIBLE);
                    lap10.setText("Lap "+lapcount+" at: "+textView.getText());
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (started){
                    button.setBackgroundColor(Color.YELLOW);
                    button.setText("Start");
                    started = false;
//                    textView.setText("00:00:00");
                    return;
                }
                spn.setEnabled(false);
                started = true;
                runTimer();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (timer==0){
                    Toast.makeText(getContext(), "Value is already default", Toast.LENGTH_SHORT).show();
                    textView.setText("00:00:00");
                    started = false;
                    delay = sameDelay;
                    spn.setEnabled(true);
                    lap.setEnabled(false);
                    lapcount = 0;
                    lapheader.setText("No Laps");
                    lap1.setVisibility(View.INVISIBLE);
                    lap2.setVisibility(View.INVISIBLE);
                    lap3.setVisibility(View.INVISIBLE);
                    lap4.setVisibility(View.INVISIBLE);
                    lap5.setVisibility(View.INVISIBLE);
                    lap6.setVisibility(View.INVISIBLE);
                    lap7.setVisibility(View.INVISIBLE);
                    lap8.setVisibility(View.INVISIBLE);
                    lap9.setVisibility(View.INVISIBLE);
                    lap10.setVisibility(View.INVISIBLE);
                    return;
                }
                lapcount = 0;
                lapheader.setText("No Laps");
                lap1.setVisibility(View.INVISIBLE);
                lap2.setVisibility(View.INVISIBLE);
                lap3.setVisibility(View.INVISIBLE);
                lap4.setVisibility(View.INVISIBLE);
                lap5.setVisibility(View.INVISIBLE);
                lap6.setVisibility(View.INVISIBLE);
                lap7.setVisibility(View.INVISIBLE);
                lap8.setVisibility(View.INVISIBLE);
                lap9.setVisibility(View.INVISIBLE);
                lap10.setVisibility(View.INVISIBLE);
                lap.setEnabled(false);
                spn.setEnabled(true);
                started = false;
                timer = 0;
                delay = sameDelay;
                button.setBackgroundColor(Color.YELLOW);
                button.setText("Start");
                textView.setText("00:00:00");
                Toast.makeText(getContext(), "Done !", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                if (!started){
                    return;
                }
                if (delay==0){
                    lap.setEnabled(true);
                    timer++;
                    int hours = timer / 3600;
                    int minutes = (timer % 3600) / 60;
                    int seconds = timer % 60;
                    @SuppressLint("DefaultLocale")
                    String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                    button.setBackgroundColor(Color.RED);
                    button.setText("Stop");
                    textView.setText(timeString);
                }
                else{
                    button.setBackgroundColor(Color.RED);
                    button.setText("Stop");
                    textView.setText("       "+Integer.toString(delay));
                    tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            tts.setLanguage(Locale.ENGLISH);
                            tts.speak(Integer.toString(delay+1),TextToSpeech.QUEUE_ADD,null);
                        }
                    });
                    delay--;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        delay = Integer.parseInt((String) parent.getItemAtPosition(position));
        sameDelay = Integer.parseInt((String) parent.getItemAtPosition(position));
        if (delay !=0 ){
            Toast.makeText(getContext(), "Delay has been set to "+Integer.toString(delay), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}