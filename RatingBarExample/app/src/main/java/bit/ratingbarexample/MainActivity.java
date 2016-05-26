package bit.ratingbarexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((RatingBar) findViewById(
                R.id.ratingBar1)).setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float rating,
                                                boolean fromUser) {
                        ((TextView) findViewById(R.id.textView)).setText(
                                "rating" + rating);
                    }
                }
        );
    }
}
