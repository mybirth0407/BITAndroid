package bit.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        findViewById(R.id.button_back).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }
}
