package CrapsGame;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.games.BasicCraps.CrapsGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class CrapsGameTest {

  private Integer balance;

  @Test
  public void gettingRobbedProbabability(){
    this.balance = 500;
    CasinoAccount x = new CasinoAccount(null, null, this.balance);
    Random rand =  new Random(1);
    Integer expected = 500;





  }






//    public Integer gettingRobbedProbablity() {
//        // For every roll, there is a probability of getting robbed
//        int rob;
//        while(robMechanicOn) {
//            randomDiceRollRobbingProb = diceGenerator.diceRoll();
//            if (randomDiceRollRobbingProb > 5) {
//                rob = this.balance * 50;
//                robMechanicOn = false;
//                return this.balance-rob;
//            }
//        }
//        return null;
//    }


//    public Integer diceRoll(){
//        Integer x = rand.nextInt(6 - 1) + 1 ;
//        Integer y = rand.nextInt(6 - 1) + 1;
//        return x + y;
//    }


//    public void setRandomBets(){
//        this.randomBetsBot1 = rand.nextInt(2500 - 500) +1;
//        this.randomBetsBot2 = rand.nextInt(2500 - 500) +1;
//        this.randomBetsBot3 = rand.nextInt(2500 - 500) +1;
//        this.randomBetsBot4 = rand.nextInt(2500 - 500) +1;
//        this.randomBetsBot5 = rand.nextInt(2500 - 500) +1;
//        this.randomBetsBot6 = rand.nextInt(2500 - 500) +1;
//
//    }

//    public Integer poolBets(Integer bets, Integer playerBet){
//        this.playerBets=playerBet;
//        this.pool7Or11Bets = bets;
//        this.poolAll7Or11Bets = playerBet + bets;
//
//        return  this.poolAll7Or11Bets;
//    }



}
