package blankthings.musicthing.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import blankthings.musicthing.R;

public class MainActivity extends AppCompatActivity implements ViewContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
