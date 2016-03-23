package Data;

/**
 * Created by Shuyun on 3/13/2016/013.
 */
public class Match {

    public Player player1;
    public Player player2;

    public Match(Player player1, Player player2){
        this.player1=player1;
        this.player2=player2;
    }
    /** Player1's match data
     * */
    int[] firstServeWin1, secondServeWin1, ace1;
    
    int[] leftNetWin1, highNetWin1, rightNetWin1;
    int[] leftBaseWin1, highBaseWin1, rightBaseWin1;

    int[] firstServeFault1, doubleFault1;
    int[] leftNetFault1, highNetFault1, rightNetFault1;
    int[] leftBaseFault1, highBaseFault1, rightBaseFault1;

    int[] serveBreakPoint1, returnBreakPoint1;

    /** Player2's match data
     * */
    int[] firstServeWin2, secondServeWin2, ace2;
    int[] leftNetWin2, highNetWin2, rightNetWin2;
    int[] leftBaseWin2, highBaseWin2, rightBaseWin2;

    int[] firstServeFault2, doubleFault2;
    int[] leftNetFault2, highNetFault2, rightNetFault2;
    int[] leftBaseFault2, highBaseFault2, rightBaseFault2;

    int[] serveBreakPoint2, returnBreakPoint2;

    /** Match state
     * */
    int serveState=1;   //detect who serve in this game

    public void addFirstServeWin(int playerId, int setState){
        if(playerId==1) {
            firstServeWin1[setState-1] += 1;
        }else if(playerId==2){
            firstServeWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addSecondServeWin(int playerId, int setState){
        if(playerId==1) {
            secondServeWin1 [setState-1]+= 1;
        }else if(playerId==2){
            secondServeWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addAce(int playerId, int setState){
        if(playerId==1) {
            ace1 [setState-1]+= 1;
        }else if(playerId==2){
            ace2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addFirstServeFault(int playerId, int setState){
        if(playerId==1) {
            firstServeFault1 [setState-1]+= 1;
        }else if(playerId==2){
            firstServeFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addDoubleFault(int playerId, int setState){
        if(playerId==1) {
            doubleFault1 [setState-1]+= 1;
        }else if(playerId==2){
            doubleFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addLeftNetWin(int playerId, int setState){
        if(playerId==1){
            leftNetWin1[setState-1]+=1;
        }else if(playerId==2){
            leftNetWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addRightNetWin(int playerId, int setState){
        if(playerId==1){
            rightNetWin1[setState-1]+=1;
        }else if(playerId==2){
            rightNetWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addHighNetWin(int playerId, int setState){
        if(playerId==1){
            highNetWin1[setState-1]+=1;
        }else if(playerId==2){
            highNetWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addLeftBaseWin(int playerId, int setState){
        if(playerId==1){
            leftBaseWin1[setState-1]+=1;
        }else if(playerId==2){
            leftBaseWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addRightBaseWin(int playerId, int setState){
        if(playerId==1){
            rightBaseWin1[setState-1]+=1;
        }else if(playerId==2){
            rightBaseWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addHighBaseWin(int playerId, int setState){
        if(playerId==1){
            highBaseWin1[setState-1]+=1;
        }else if(playerId==2){
            highBaseWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addLeftNetFault(int playerId, int setState){
        if(playerId==1){
            leftNetFault1[setState-1]+=1;
        }else if(playerId==2){
            leftNetFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addRightNetFault(int playerId, int setState){
        if(playerId==1){
            rightNetFault1[setState-1]+=1;
        }else if(playerId==2){
            rightNetFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addHighNetFault(int playerId, int setState){
        if(playerId==1){
            highNetFault1[setState-1]+=1;
        }else if(playerId==2){
            highNetFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addLeftBaseFault(int playerId, int setState){
        if(playerId==1){
            leftBaseFault1[setState-1]+=1;
        }else if(playerId==2){
            leftBaseFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addRightBaseFault(int playerId, int setState){
        if(playerId==1){
            rightBaseFault1[setState-1]+=1;
        }else if(playerId==2){
            rightBaseFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addHighBaseFault(int playerId, int setState){
        if(playerId==1){
            highBaseFault1[setState-1]+=1;
        }else if(playerId==2){
            highBaseFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addServeBreakPoint(int playerId, int setState){
        if(playerId==1){
            serveBreakPoint1[setState-1]+=1;
        }else if(playerId==2){
            serveBreakPoint2[setState-1]+=1;
        }
    }

    public void addReturnBreakPoint(int playerId, int setState){
        if(playerId==1){
            returnBreakPoint1[setState-1]+=1;
        }else if(playerId==2){
            returnBreakPoint2[setState-1]+=1;
        }
    }

    public void changeServeState(){
//        if(this.serveState==1){
//            serveState=2;
//        }else {
//            serveState=1;
//        }
        serveState=(serveState>1)?1:2;
    }

    public int getServeState(){
        return serveState;
    }
    
}
