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

    public  Match(){
        super();
    }

    public void setPlayers(Player player1, Player player2){
        this.player1=player1;
        this.player2=player2;
    }

    /** Match sets
     * */
    public int[] set1=new int[4], set2=new int[4],set3=new int[4],set4=new int[4],set5=new int[4];

    /** Player1's match data
     * */
    public int[] firstServeWin1=new int[5], secondServeWin1=new int[5], ace1=new int[5];
    public int[] leftNetWin1=new int[5], highNetWin1=new int[5], rightNetWin1=new int[5];
    public int[] leftBaseWin1=new int[5], highBaseWin1=new int[5], rightBaseWin1=new int[5];

    public int[] firstServeFault1=new int[5], doubleFault1=new int[5];
    public int[] leftNetFault1=new int[5], highNetFault1=new int[5], rightNetFault1=new int[5];
    public int[] leftBaseFault1=new int[5], highBaseFault1=new int[5], rightBaseFault1=new int[5];

    public int[] servePointWin1=new int[5], servePointFault1=new int[5],
            breakPointWin1=new int[5], breakPointFault1=new int[5],
            servePoints1=new int[5], breakPoints1=new int[5], serveCount1=new int[5];

    /** Player2's match data
     * */
    public int[] firstServeWin2=new int[5], secondServeWin2=new int[5], ace2=new int[5];
    public int[] leftNetWin2=new int[5], highNetWin2=new int[5], rightNetWin2=new int[5];
    public int[] leftBaseWin2=new int[5], highBaseWin2=new int[5], rightBaseWin2=new int[5];

    public int[] firstServeFault2=new int[5], doubleFault2=new int[5];
    public int[] leftNetFault2=new int[5], highNetFault2=new int[5], rightNetFault2=new int[5];
    public int[] leftBaseFault2=new int[5], highBaseFault2=new int[5], rightBaseFault2=new int[5];

    public int[] servePointWin2=new int[5], servePointFault2=new int[5],
            breakPointWin2=new int[5], breakPointFault2=new int[5],
            servePoints2=new int[5], breakPoints2=new int[5], serveCount2=new int[5];


    /** Match state
     * */
    int serveState=1;   //detect who serve in this game

    public void cleanData(){
        for(int i=0; i<5; i++){
            firstServeWin1[i]=0;secondServeWin1[i]=0;ace1[i]=0;
            leftNetWin1[i]=0;  highNetWin1[i]=0; rightNetWin1[i]=0;
            leftBaseWin1[i]=0; highBaseWin1[i]=0; rightBaseWin1[i]=0;
            firstServeFault1[i]=0; doubleFault1[i]=0;
            leftNetFault1[i]=0; highNetFault1[i]=0; rightNetFault1[i]=0;
            leftBaseFault1[i]=0; highBaseFault1[i]=0; rightBaseFault1[i]=0;
            servePointWin1[i]=0; servePointFault1[i]=0;
            breakPointWin1[i]=0; breakPointFault1[i]=0;
            servePoints1[i]=0; breakPoints1[i]=0;

            firstServeWin2[i]=0; secondServeWin2[i]=0; ace2[i]=0;
            leftNetWin2[i]=0; highNetWin2[i]=0; rightNetWin2[i]=0;
            leftBaseWin2[i]=0; highBaseWin2[i]=0; rightBaseWin2[i]=0;
            firstServeFault2[i]=0; doubleFault2[i]=0;
            leftNetFault2[i]=0; highNetFault2[i]=0; rightNetFault2[i]=0;
            leftBaseFault2[i]=0; highBaseFault2[i]=0; rightBaseFault2[i]=0;
            servePointWin2[i]=0; servePointFault2[i]=0;
            breakPointWin2[i]=0; breakPointFault2[i]=0;
            servePoints2[i]=0; breakPoints2[i]=0;

        }
        
    }

    public void addFirstServeWin(int playerId, int setState){
        if(playerId==0) {
            firstServeWin1[setState-1] += 1;
        }else if(playerId==1){
            firstServeWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addSecondServeWin(int playerId, int setState){
        if(playerId==0) {
            secondServeWin1 [setState-1]+= 1;
        }else if(playerId==1){
            secondServeWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addAce(int playerId, int setState){
        if(playerId==0) {
            ace1 [setState-1]+= 1;
        }else if(playerId==1){
            ace2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addFirstServeFault(int playerId, int setState){
        if(playerId==0) {
            firstServeFault1 [setState-1]+= 1;
        }else if(playerId==1){
            firstServeFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addDoubleFault(int playerId, int setState){
        if(playerId==0) {
            doubleFault1 [setState-1]+= 1;
        }else if(playerId==1){
            doubleFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addLeftNetWin(int playerId, int setState){
        if(playerId==0){
            leftNetWin1[setState-1]+=1;
        }else if(playerId==1){
            leftNetWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addRightNetWin(int playerId, int setState){
        if(playerId==0){
            rightNetWin1[setState-1]+=1;
        }else if(playerId==1){
            rightNetWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addHighNetWin(int playerId, int setState){
        if(playerId==0){
            highNetWin1[setState-1]+=1;
        }else if(playerId==1){
            highNetWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addLeftBaseWin(int playerId, int setState){
        if(playerId==0){
            leftBaseWin1[setState-1]+=1;
        }else if(playerId==1){
            leftBaseWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addRightBaseWin(int playerId, int setState){
        if(playerId==0){
            rightBaseWin1[setState-1]+=1;
        }else if(playerId==1){
            rightBaseWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addHighBaseWin(int playerId, int setState){
        if(playerId==0){
            highBaseWin1[setState-1]+=1;
        }else if(playerId==1){
            highBaseWin2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addLeftNetFault(int playerId, int setState){
        if(playerId==0){
            leftNetFault1[setState-1]+=1;
        }else if(playerId==1){
            leftNetFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addRightNetFault(int playerId, int setState){
        if(playerId==0){
            rightNetFault1[setState-1]+=1;
        }else if(playerId==1){
            rightNetFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addHighNetFault(int playerId, int setState){
        if(playerId==0){
            highNetFault1[setState-1]+=1;
        }else if(playerId==1){
            highNetFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addLeftBaseFault(int playerId, int setState){
        if(playerId==0){
            leftBaseFault1[setState-1]+=1;
        }else if(playerId==1){
            leftBaseFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addRightBaseFault(int playerId, int setState){
        if(playerId==0){
            rightBaseFault1[setState-1]+=1;
        }else if(playerId==1){
            rightBaseFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addHighBaseFault(int playerId, int setState){
        if(playerId==0){
            highBaseFault1[setState-1]+=1;
        }else if(playerId==1){
            highBaseFault2[setState-1]+=1;
        }else {
            return;
        }
    }

    public void addServePointWin(int playerId, int setState){
        if(playerId==0){
            servePointWin1[setState-1]+=1;
        }else if(playerId==1){
            servePointWin2[setState-1]+=1;
        }
    }

//    public void addServePointFault(int playerId, int setState){
//        if(playerId==0){
//            servePointFault1[setState-1]+=1;
//        }else if(playerId==1){
//            servePointFault2[setState-1]+=1;
//        }
//    }

    public void addBreakPointWin(int playerId, int setState){
        if(playerId==0){
            breakPointWin1[setState-1]+=1;
        }else if(playerId==1){
            breakPointWin2[setState-1]+=1;
        }
    }

//    public void addBreakPointFault(int playerId, int setState){
//        if(playerId==0){
//            breakPointFault1[setState-1]+=1;
//        }else if(playerId==1){
//            breakPointFault2[setState-1]+=1;
//        }
//    }

    public void addServePoints(int playerId, int setState){
        if(playerId==0){
            servePoints1[setState-1]+=1;
        }else if(playerId==1){
            servePoints2[setState-1]+=1;
        }
    }

    public void addBreakPoints(int playerId, int setState){
        if(playerId==0){
            breakPoints1[setState-1]+=1;
        }else if(playerId==1){
            breakPoints2[setState-1]+=1;
        }
    }

    public void addServeCount(int playerId, int serveState){
        if(playerId==0){
            serveCount1[serveState-1]+=1;
        }else if(playerId==1){
            serveCount2[serveState-1]+=1;
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
