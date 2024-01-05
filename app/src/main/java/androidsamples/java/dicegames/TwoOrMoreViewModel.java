package androidsamples.java.dicegames;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

import androidx.lifecycle.ViewModel;

/**
 * A {@link ViewModel} for the gambling game that allows the user to choose a game type, set a wager, and then play.
 */
public class TwoOrMoreViewModel extends ViewModel {
  private int mBal;
  private Integer mWager;
  private GameType mGameType;
  private final List<Die> diceList;
  /**
   * No argument constructor.
   */
  public TwoOrMoreViewModel() {
    // TODO implement method
    mBal = 0;
    mGameType = GameType.NONE;
    diceList = new ArrayList<>();
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
   * @param balance the given amount
   */
  public void setBalance(int balance) {
    // TODO implement method
    mBal = balance;
  }

  /**
   * Reports the current game type as one of the values of the {@code enum} {@link GameType}.
   *
   * @return the current game type
   */
  public GameType gameType() {
    // TODO implement method
    return mGameType;
  }

  /**
   * Sets the current game type to the given value.
   *
   * @param gameType the game type to be set
   */
  public void setGameType(GameType gameType) {
    // TODO implement method
    mGameType = gameType;
  }

  /**
   * Reports the wager amount.
   *
   * @return the wager amount
   */
  public int wager() {
    // TODO implement method
    return mWager;
  }

  /**
   * Sets the wager to the given amount.
   *
   * @param wager the amount to be set
   */
  public void setWager(int wager) {
    // TODO implement method
    mWager = wager;
  }

  /**
   * Reports whether the wager amount is valid for the given game type and current balance.
   * For {@link GameType#TWO_ALIKE}, the balance must be at least twice as much, for {@link GameType#THREE_ALIKE}, at least thrice as much, and for {@link GameType#FOUR_ALIKE}, at least four times as much.
   * The wager must also be more than 0.
   *
   * @return {@code true} iff the wager set is valid
   */
  public boolean isValidWager() {
    int wagerValue = wager();

    if (wagerValue <= 0) {
      return false;
    }

    GameType currentGameType = gameType();

    if (currentGameType == GameType.NONE) {
      return false;
    }

    return balance() >= (getGameRatio(currentGameType) * wagerValue);

  }

  /**
   * Returns the current values of all the dice.
   *
   * @return the values of dice
   */
  public List<Integer> diceValues() {
    // TODO implement method
    List<Integer> diceValue = new ArrayList<>();
    for (int i = 0; i < diceList.size(); i++)
      diceValue.add(diceList.get(i).value());

    return diceValue;
  }

  /**
   * Adds the given {@link Die} to the game.
   *
   * @param d the Die to be added
   */
  public void addDie(Die d) {
    // TODO implement method
    diceList.add(d);
  }

  /**
   * Simulates playing the game based on the type and the wager and reports the result as one of the values of the {@code enum} {@link GameResult}.
   *
   * @return result of the current game
   * @throws IllegalStateException if the wager or the game type was not set to a proper value.
   */
  public GameResult play() throws IllegalStateException {
    // TODO implement method
    if(mWager == null)
      throw new IllegalStateException("Wager not set, can't play!");

    if(mGameType == GameType.NONE)
      throw new IllegalStateException("Game Type not set, can't play!");

    if(diceList.size() < 4)
      throw new IllegalStateException("Not enough dice, can't play!");

    if(isValidWager())
    {
      for (Die die : diceList) {
        die.roll();
      }
      List<Integer> values = diceValues().subList(0, 4);
      int maxFreq = maxMatch(values);

      switch (gameType())
      {
        case TWO_ALIKE:
          if(maxFreq == 2)
          {
            setBalance(balance() + 2 * wager());
            return GameResult.WIN;
          }
          setBalance(balance() - 2 * wager());
          break;

        case THREE_ALIKE:
          if(maxFreq == 3)
          {
            setBalance(balance() + 3 * wager());
            return GameResult.WIN;
          }
          setBalance(balance() - 3 * wager());
          break;

        case FOUR_ALIKE:
          if(maxFreq == 4)
          {
            setBalance(balance() + 4 * wager());
            return GameResult.WIN;
          }
          setBalance(balance() - 4 * wager());
          break;

        default:
          throw new IllegalStateException("Invalid Game Type : " + gameType());
      }

      return GameResult.LOSS;
    }

    return GameResult.UNDECIDED;
  }

  /**
   * Checks for maximum matching Dice faces and returns the number
   *
   * @param values the list of Dice values
   * @return maximum number of same Die values
   */
  private int maxMatch(List<Integer> values) {
    if (values.isEmpty()) {
      return 0;
    }

    HashMap<Integer, Integer> freqMap = new HashMap<>();

    for(int value : values) {
      Integer count = freqMap.get(value);
      if (count != null) {
        freqMap.put(value, count + 1);
      } else {
        freqMap.put(value, 1);
      }
    }

    return Collections.max(freqMap.values());
  }

  private int getGameRatio(GameType type) {
    switch (type) {
      case TWO_ALIKE:
        return 2;
      case THREE_ALIKE:
        return 3;
      case FOUR_ALIKE:
        return 4;
      default:
        throw new IllegalStateException("Invalid Game Type :- " + type);
    }
  }
  
}
