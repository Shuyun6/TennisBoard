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
    int firstServeWin1=0, secondServeWin1=0, ace1=0;
    int leftNetWin1=0, highNetWin1=0, rightNetWin1=0;
    int leftBaseWin1=0, highBaseWin1=0, rightBaseWin1=0;

    int firstServeFault1=0, doubleFault1=0;
    int leftNetFault1=0, highNetFault1=0, rightNetFault1=0;
    int leftBaseFault1=0, highBaseFault1=0, rightBaseFault1=0;

    int serveBreakPoint1=0, returnBreakPoint1=0;

    /** Player2's match data
     * */
    int firstServeWin2=0, secondServeWin2=0, ace2=0;
    int leftNetWin2=0, highNetWin2=0, rightNetWin2=0;
    int leftBaseWin2=0, highBaseWin2=0, rightBaseWin2=0;

    int firstServeFault2=0, doubleFault2=0;
    int leftNetFault2=0, highNetFault2=0, rightNetFault2=0;
    int leftBaseFault2=0, highBaseFault2=0, rightBaseFault2=0;

    int serveBreakPoint2=0, returnBreakPoint2=0;

    /** Match state
     * */
    int serveState=1;   //detect who serve in this game

    public void addFirstServeWin(int playerId){
        if(playerId==1) {
            firstServeWin1 += 1;
        }else if(playerId==2){
            firstServeWin2+=1;
        }else {
            return;
        }
    }

    public void addSecondServeWin(int playerId){
        if(playerId==1) {
            secondServeWin1 += 1;
        }else if(playerId==2){
            secondServeWin2+=1;
        }else {
            return;
        }
    }

    public void addAce(int playerId){
        if(playerId==1) {
            ace1 += 1;
        }else if(playerId==2){
            ace2+=1;
        }else {
            return;
        }
    }

    public void addFirstServeFault(int playerId){
        if(playerId==1) {
            firstServeFault1 += 1;
        }else if(playerId==2){
            firstServeFault2+=1;
        }else {
            return;
        }
    }

    public void addDoubleFault(int playerId){
        if(playerId==1) {
            doubleFault1 += 1;
        }else if(playerId==2){
            doubleFault2+=1;
        }else {
            return;
        }
    }

    public void addLeftNetWin(int playerId){
        if(playerId==1){
            leftNetWin1+=1;
        }else if(playerId==2){
            leftNetWin2+=1;
        }else {
            return;
        }
    }

    public void addRightNetWin(int playerId){
        if(playerId==1){
            rightNetWin1+=1;
        }else if(playerId==2){
            rightNetWin2+=1;
        }else {
            return;
        }
    }

    public void addHighNetWin(int playerId){
        if(playerId==1){
            highNetWin1+=1;
        }else if(playerId==2){
            highNetWin2+=1;
        }else {
            return;
        }
    }

    public void addLeftBaseWin(int playerId){
        if(playerId==1){
            leftBaseWin1+=1;
        }else if(playerId==2){
            leftBaseWin2+=1;
        }else {
            return;
        }
    }

    public void addRightBaseWin(int playerId){
        if(playerId==1){
            rightBaseWin1+=1;
        }else if(playerId==2){
            rightBaseWin2+=1;
        }else {
            return;
        }
    }

    public void addHighBaseWin(int playerId){
        if(playerId==1){
            highBaseWin1+=1;
        }else if(playerId==2){
            highBaseWin2+=1;
        }else {
            return;
        }
    }

    public void addLeftNetFault(int playerId){
        if(playerId==1){
            leftNetFault1+=1;
        }else if(playerId==2){
            leftNetFault2+=1;
        }else {
            return;
        }
    }

    public void addRightNetFault(int playerId){
        if(playerId==1){
            rightNetFault1+=1;
        }else if(playerId==2){
            rightNetFault2+=1;
        }else {
            return;
        }
    }

    public void addHighNetFault(int playerId){
        if(playerId==1){
            highNetFault1+=1;
        }else if(playerId==2){
            highNetFault2+=1;
        }else {
            return;
        }
    }

    public void addLeftBaseFault(int playerId){
        if(playerId==1){
            leftBaseFault1+=1;
        }else if(playerId==2){
            leftBaseFault2+=1;
        }else {
            return;
        }
    }

    public void addRightBaseFault(int playerId){
        if(playerId==1){
            rightBaseFault1+=1;
        }else if(playerId==2){
            rightBaseFault2+=1;
        }else {
            return;
        }
    }

    public void addHighBaseFault(int playerId){
        if(playerId==1){
            highBaseFault1+=1;
        }else if(playerId==2){
            highBaseFault2+=1;
        }else {
            return;
        }
    }

    public void addServeBreakPoint(int playerId){
        if(playerId==1){
            serveBreakPoint1+=1;
        }else if(playerId==2){
            serveBreakPoint2+=1;
        }
    }

    public void addReturnBreakPoint(int playerId){
        if(playerId==1){
            returnBreakPoint1+=1;
        }else if(playerId==2){
            returnBreakPoint2+=1;
        }
    }

    public void changeServeState(){
        if(this.serveState==1){
            serveState=2;
        }else {
            serveState=1;
        }
    }

    public int getServeState(){
        return serveState;
    }
    
}
