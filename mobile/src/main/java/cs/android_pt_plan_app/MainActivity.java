package cs.android_pt_plan_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    static String[] warmups = {"Warm-up 1\n3 rounds\n5 x Push-ups\n10 x Sit-ups\n15 x Squats",
            "Warm-up 2\n4 Rounds\nRun 400m\n10 x Burpees",
            "Warm-up 3\n3 Rounds\n6 x DL @ 95 lbs.\n6 x Hang Clean @ 95 lbs.\n6 x Front Squats @ 95 lbs.\n6 x Push Press @ 95 lbs.\n6 x Push-ups"};
    static int[] warmupSchedule = {9,0,2,1,0,0}; //Schedule must be -1 of the warmup it actually is.  9 is a placeholder workaround
    static String[] workouts = {"Workout 1"};
    int currentDay=0;
    int status=0; //0 = warmup 1=Workout

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Button button1=(Button)findViewById(R.id.button1);

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    button1.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(warmups[currentDay]);
                    button1.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    button1.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(status==0) {
                    mTextMessage.setText(workouts[currentDay]);
                    button1.setText("Return to Warmup");

                    status = 1;
                }else if(status==1){
                    mTextMessage.setText(warmups[currentDay]);
                    button1.setText("Done With Warmup");
                    status = 0;
                }else{
                    status=0;
                }

            }
        });

    }

}
