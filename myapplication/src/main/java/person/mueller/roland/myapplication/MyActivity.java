package person.mueller.roland.myapplication;

import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;


public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        setGreeting();
    }


    private void setGreeting()
    {
        Log.wtf(this.getClass().getCanonicalName(), "Setting Greeting");
        TextView greeting = (TextView)findViewById( R.id.greeting );
        Date tmStamp = new Date();
        greeting.setText( "Moi! " + tmStamp);
    }

}