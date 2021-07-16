package com.example.driver_nim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driver_nim.ui.main.GameFragment;
import com.example.driver_nim.ui.main.MainFragment;
import com.example.driver_nim.ui.main.NimGame;

public class MainActivity extends AppCompatActivity {
    private NimGame mViewModel;
    TextView sticks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mViewModel = new ViewModelProvider(this).get(NimGame.class);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    public void onClickTwoPlayer(View v) {
        mViewModel.setTwoPlayer(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, GameFragment.newInstance())
                .commitNow();
        sticks = findViewById(R.id.StickView);
        sticks.setText(mViewModel.displaySticks());
        displaySetup();
    }

    public void onClickComputerGame(View v) {
        mViewModel.setTwoPlayer(false);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, GameFragment.newInstance())
                .commitNow();
        displaySetup();

    }

    private void displaySetup() {
        sticks = findViewById(R.id.StickView);
        sticks.setText(mViewModel.displaySticks());
        Toast.makeText(this, "You go first!", Toast.LENGTH_SHORT).show();
    }

    public void pickOne(View v) {
        buttonAction(1);

    }

    public void pickTwo(View v) {
        buttonAction(2);

    }

    public void pickThree(View v) {
        buttonAction(3);
    }


    public void buttonAction(int choice) {
        mViewModel.playGame(choice);
        sticks.setText(mViewModel.displaySticks());
        toastLogic();
        if(mViewModel.Winner()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
            Toast.makeText(this, "Game Over! "+ mViewModel.getWinningPlayer() + " Wins!", Toast.LENGTH_SHORT).show();
            mViewModel = new NimGame();
        }
    }


    private void toastLogic() {
        if (!mViewModel.Winner()) {
            if (mViewModel.getTwoPlayer() && mViewModel.getPlayerOneTurn()) {
                Toast.makeText(this, "Player Two's Turn", Toast.LENGTH_SHORT).show();
            }
            if (mViewModel.getTwoPlayer() && !mViewModel.getPlayerOneTurn()) {
                Toast.makeText(this, "Player One's Turn", Toast.LENGTH_SHORT).show();
            }
            if (!mViewModel.getTwoPlayer() && mViewModel.getPlayerOneTurn()) {
                Toast.makeText(this, ("The Computer Picked Up " + +(char) mViewModel.getComputerChoice()), Toast.LENGTH_SHORT).show();

            }
            if (!mViewModel.getTwoPlayer() && !mViewModel.getPlayerOneTurn()) {
                Toast.makeText(this, "Your Turn!", Toast.LENGTH_SHORT).show();

            }

        }
    }




}