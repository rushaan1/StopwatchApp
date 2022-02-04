package com.example.simplestopwatchtimer;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    boolean started = false;
    int time = 0;
    Button button;
    Button reset;
    TextView textView;
    EditText editText;
    ProgressBar progressBar;
    int updatedTime = 0;
    Switch notify;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
    int fixedTime = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag2_layout, container, false);
        button = view.findViewById(R.id.startTimer);
        editText = view.findViewById(R.id.seconds);
        textView = view.findViewById(R.id.timer);
        reset = view.findViewById(R.id.resetTimer);
        progressBar = view.findViewById(R.id.bar);
        notify = view.findViewById(R.id.notify);
        progressBar.setProgress(100);

        ConstraintLayout f2 = view.findViewById(R.id.frag2);
        AnimationDrawable anim = (AnimationDrawable) f2.getBackground();
        anim.setEnterFadeDuration(3000);
        anim.setExitFadeDuration(4000);
        anim.start();
        setHasOptionsMenu(true);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (time==0){
                    if (editText.getText().toString().equals("") || Integer.parseInt(editText.getText().toString())<3){
                        Toast.makeText(getContext(), "Minimum value must be 3 seconds", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if (Integer.parseInt(editText.getText().toString())>82800){
                        Toast.makeText(getContext(), "Maximum value must be less than 82800 seconds", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    time = Integer.parseInt(editText.getText().toString());
                }
                else {
                    time = updatedTime;
                }
                if (started){
                    button.setText("Resume");
                    button.setBackgroundColor(Color.RED);
                    started = false;
                    return;
                }
                if (editText.isEnabled()){
                    fixedTime = Integer.parseInt(editText.getText().toString());
                }
                editText.setEnabled(false);
                started = true;
                runTimer();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                button.setText("Start");
                button.setBackgroundColor(Color.RED);
                started = false;
                time = 0;
                updatedTime = 0;
                editText.setText("");
                textView.setText("00:00:00");
                editText.setEnabled(true);
                progressBar.setProgress(100);
            }
        });
        return view;
    }
    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void run() {
                if (!started){
                    return;
                }
                time--;
                updatedTime = time;
                progressBar.setProgress(100*updatedTime/fixedTime);
                if (time<1 || updatedTime<1){
                    button.setText("Start");
                    button.setBackgroundColor(Color.RED);
                    started = false;
                    time = 0;
                    updatedTime = 0;
                    editText.setText("");
                    textView.setText("00:00:00");
                    editText.setEnabled(true);
                    progressBar.setProgress(0);
                    if (notify.isChecked()){
                        createNotificationChannel(getContext());
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(),"Timer");
                        builder.setContentTitle("Timer Ended !");
                        builder.setContentText("Your timer for "+fixedTime+" seconds has ended!!");
                        builder.setSmallIcon(R.drawable.clocc);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat compat = NotificationManagerCompat.from(requireContext());
                        compat.notify(1, builder.build());
                    }

                    return;
                }


                int hours = time / 3600;
                int minutes = (time % 3600) / 60;
                int seconds = time % 60;
                @SuppressLint("DefaultLocale")
                String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                if (time<3600){
                    timeString = String.format("   "+"%02d:%02d", minutes, seconds);
                }
                if (time<60){
                    timeString = String.format("     "+"%02d", seconds);
                }
                textView.setText(timeString);
                button.setText("Stop");
                button.setBackgroundColor(Color.GREEN);
                handler.postDelayed(this,1000);
            }
        });
    }

    private void createNotificationChannel(Context ctx) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Timer", "Timer Ended", importance);
            channel.setDescription("Notification gets triggered whenever the timer ends.");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = ctx.getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }
}
