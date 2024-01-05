package androidsamples.java.dicegames;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;



public class WalletActivity extends AppCompatActivity {
  private static final int TWO_OR_MORE_REQUEST_CODE = 2;
  static final String KEY_BALANCE = "KEY_BALANCE", KEY_DIE_VALUE = "KEY_DIE_VALUE";

  private Button mDieButton;
  private TextView mTxtBalance;
  private WalletViewModel mWalletVM;

  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wallet);

    mDieButton = findViewById(R.id.btn_die);
    Button mTwoOrMoreButton = findViewById(R.id.btn_launch_twoormore);
    mTxtBalance = findViewById(R.id.txt_balance);
    mWalletVM = new ViewModelProvider(this).get(WalletViewModel.class);

    Die6 mDie = new Die6();
    mWalletVM.setDie(mDie);
    mWalletVM.setIncrement(5);
    mWalletVM.setWinValue(6);

    if(savedInstanceState != null) {
      int balance = savedInstanceState.getInt(KEY_BALANCE, 0);
      mWalletVM.setBalance(balance);
    }

    updateUI();

    mDieButton.setOnClickListener(v -> {
      mWalletVM.rollDie();
      updateUI();
    });

    mTwoOrMoreButton.setOnClickListener(this::launchTwoOrMore);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(KEY_BALANCE, mWalletVM.balance());
  }

  @SuppressLint("SetTextI18n")
  private void updateUI() {
    mTxtBalance.setText(Integer.toString(mWalletVM.balance()));
    mDieButton.setText(Integer.toString(mWalletVM.dieValue()));
  }

  private final ActivityResultLauncher<Intent> twoOrMoreActivityResultLauncher =
          registerForActivityResult(
                  new ActivityResultContracts.StartActivityForResult(),
                  result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                      int balance = result.getData().getIntExtra(TwoOrMoreActivity.KEY_BALANCE_RETURN, 0);
                      mWalletVM.setBalance(balance);
                      updateUI();
                    }
                  }
          );

  public void launchTwoOrMore(View view) {
        Intent intent = new Intent(this, TwoOrMoreActivity.class);
        intent.putExtra(KEY_BALANCE, mWalletVM.balance());
        twoOrMoreActivityResultLauncher.launch(intent);
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == TWO_OR_MORE_REQUEST_CODE && data != null) {
      int balance = data.getIntExtra(TwoOrMoreActivity.KEY_BALANCE_RETURN, 0);
      mWalletVM.setBalance(balance);
      updateUI();
    }
  }
}
