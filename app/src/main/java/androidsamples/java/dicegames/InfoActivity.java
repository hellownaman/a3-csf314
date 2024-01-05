package androidsamples.java.dicegames;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button backButton = findViewById(R.id.rbtn_back);     // Back button to return to TwoOrMoreActivity
        backButton.setOnClickListener(this::returnBack);

    }

    /**
     * Method to handle the action when going back to TwoOrMoreActivity
     */
    private void returnBack(View view) {
        finish();
    }

}
