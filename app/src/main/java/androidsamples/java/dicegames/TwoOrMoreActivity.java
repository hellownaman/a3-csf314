package androidsamples.java.dicegames;

import static androidsamples.java.dicegames.WalletActivity.KEY_BALANCE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class TwoOrMoreActivity extends AppCompatActivity {


  static final String KEY_BALANCE_RETURN = "KEY_BALANCE_RETURN";

  private TextView mTxtBalance;
  private EditText mTxtWager;
  private RadioGroup mRadioGroup;
  private TwoOrMoreViewModel mTwoOrMoreVM;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    initializeViews();
    int balance = savedInstanceState == null
            ? getIntent().getIntExtra(KEY_BALANCE, 0)
            : savedInstanceState.getInt(KEY_BALANCE, 0);

    mTwoOrMoreVM.setBalance(balance);
    mTxtBalance.setText(Integer.toString(mTwoOrMoreVM.balance()));
    if (!mTwoOrMoreVM.diceValues().isEmpty()) updateDiceValues();
  }

  private void initializeViews() {
    mTxtBalance = findViewById(R.id.txt_balance_twoormore);
    mTxtWager = findViewById(R.id.edit_wager);
    mRadioGroup = findViewById(R.id.radioGroup);

    mTwoOrMoreVM = new ViewModelProvider(this).get(TwoOrMoreViewModel.class);

    Button mButtonGo = findViewById(R.id.btn_go);
    mButtonGo.setOnClickListener(v -> onGoButtonClick());

    Button mButtonBack = findViewById(R.id.btn_back);
    mButtonBack.setOnClickListener(this::returnToWallet);

    Button mButtonInfo = findViewById(R.id.btn_info);
    mButtonInfo.setOnClickListener(this::launchInfoActivity);
  }

  @SuppressLint("SetTextI18n")
  private void updateDiceValues() {
    TextView mTxtDie1 = findViewById(R.id.txt_die1);
    TextView mTxtDie2 = findViewById(R.id.txt_die2);
    TextView mTxtDie3 = findViewById(R.id.txt_die3);
    TextView mTxtDie4 = findViewById(R.id.txt_die4);

    mTxtDie1.setText(Integer.toString(mTwoOrMoreVM.diceValues().get(0)));
    mTxtDie2.setText(Integer.toString(mTwoOrMoreVM.diceValues().get(1)));
    mTxtDie3.setText(Integer.toString(mTwoOrMoreVM.diceValues().get(2)));
    mTxtDie4.setText(Integer.toString(mTwoOrMoreVM.diceValues().get(3)));
  }

  private void onGoButtonClick() {
    if (!setWager()) {
      showToast("Please enter a valid wager amount.");
      return;
    }

    setGameTypeFromRadioButton();
    addAllDice();
    GameResult gameResult = mTwoOrMoreVM.play();

    if (gameResult == GameResult.UNDECIDED) showToast("Please enter a valid wager amount.");
    else if(gameResult == GameResult.WIN)
      showToast("You Won.");
    else if(gameResult == GameResult.LOSS)
      showToast("You Lost.");
    mTxtBalance.setText(Integer.toString(mTwoOrMoreVM.balance()));
    updateDiceValues();
  }

  private boolean setWager() {
    try {
      mTwoOrMoreVM.setWager(Integer.parseInt(mTxtWager.getText().toString()));
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private void setGameTypeFromRadioButton() {
    int checkedRadioId = mRadioGroup.getCheckedRadioButtonId();
    RadioButton checkedRadioButton = mRadioGroup.findViewById(checkedRadioId);
    String gameType = checkedRadioButton.getText().toString();

    switch (gameType) {
      case "2 Alike":
        mTwoOrMoreVM.setGameType(GameType.TWO_ALIKE);
        break;
      case "3 alike":
        mTwoOrMoreVM.setGameType(GameType.THREE_ALIKE);
        break;
      case "4 alike":
        mTwoOrMoreVM.setGameType(GameType.FOUR_ALIKE);
        break;
      default:
        throw new IllegalStateException("Unexpected Radio Value: " + gameType);
    }
  }

  private void addAllDice() {
    for (int i = 0; i < 4; i++) {
      mTwoOrMoreVM.addDie(new Die6());
    }
  }

  private void showToast(String message) {
    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;
    Toast.makeText(context, message, duration).show();
  }

  public void returnToWallet(View view) {
    Intent resultIntent = new Intent();
    resultIntent.putExtra(KEY_BALANCE_RETURN, mTwoOrMoreVM.balance());
    setResult(RESULT_OK, resultIntent);
    finish();
  }

  public void launchInfoActivity(View view) {
    Intent resultIntent = new Intent(this, InfoActivity.class);
    startActivity(resultIntent);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(KEY_BALANCE, mTwoOrMoreVM.balance());
  }

  @Override
  public void onBackPressed() {
    Intent resultIntent = new Intent();
    resultIntent.putExtra(KEY_BALANCE_RETURN, mTwoOrMoreVM.balance());
    setResult(RESULT_CANCELED, resultIntent);
    super.onBackPressed();
  }
}
