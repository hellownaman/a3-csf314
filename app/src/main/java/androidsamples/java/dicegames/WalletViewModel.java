package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {
  private int mWinValue, mInc, mBal;
  private int mDieValue = 0; // This is a locally declared member variable to maintain die value across orientation changes and process death scenario.
  private Die mDie;
  /**
   * The no argument constructor.
   */
  public WalletViewModel() {
    // TODO implement method
    mBal = 0;
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public int balance() {
    // TODO implement method
    return mBal;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param amount the new balance
   */
  public void setBalance(int amount) {
    // TODO implement method
    mBal = amount;
  }

  /**
   * Rolls the {@link Die} in the wallet.
   */
  public void rollDie() {
    // TODO implement method
    if(mDie == null)
      throw new IllegalStateException("Die does not exist");

    mDie.roll();

    if(mDie.value() == mWinValue)
      mBal += mInc;

  }

  /**
   * Reports the current value of the {@link Die}.
   *
   * @return current value of the die
   */
  public int dieValue() {
    // TODO implement method
    if(mDie.value() != 0) {
      mDieValue = mDie.value();
    }

    return mDieValue;
  }

  /**
   * Sets the increment value for earning in the wallet.
   *
   * @param increment amount to add to the balance
   */
  public void setIncrement(int increment) {
    // TODO implement method
    mInc = increment;
  }

  /**
   * Sets the value which when rolled earns the increment.
   *
   * @param winValue value to be set
   */
  public void setWinValue(int winValue) {
    // TODO implement method
    mWinValue = winValue;
  }

  /**
   * Sets the {@link Die} to be used in this wallet.
   *
   * @param d the Die to use
   */
  public void setDie(Die d) {
    // TODO implement method
    mDie = d;
  }
}
