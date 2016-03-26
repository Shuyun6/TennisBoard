package Data;

/**
 * Created by Shuyun on 3/13/2016/013.
 */
public class ScoreCalculator {

    private int score[]=new int[2];
    private String scoreShow[]=new String[2];
    private int set[]={0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // set1, set2, set3, set4, set5)*2, setState
    public int setState=1;  //1, 2, 3, 4, 5
    public int setData, gameData, adData;
    public int isServe=0;
    public boolean isTiebreak;
    public Match match;
    private int whoSeverAd=-1, whoBreakAd=-1; // 0 is player1 gets an Ad is serve game(or gets an Ad in break game

    public ScoreCalculator(){
        this.score[0]=0;
        this.score[1]=0;
    }

    public void finishMatch(){
        /** Cause the class will be created as a static object, after finishing a match we need to
         * turn all the data to be ZERO to make sure the next time when the class be called the data
         * is not be used
         * */
        score[0]=0;
        score[1]=0;
        scoreShow[0]="";
        scoreShow[1]="";
        setState=1;
        for(int i=0; i<11; i++){
            set[i]=0;
        }

    }

    public void setMatchData(String setStr, String gameStr, String adStr){

        if(setStr.equals("1")){
            setData=1;
        }else if(setStr.equals("3")){
            setData=3;
        }else if(setStr.equals("5")){
            setData=5;
        }

        if(gameStr.equals("4")){
            gameData=4;
        }else if(gameStr.equals("6")){
            gameData=6;
        }

        if(adStr.equals("YES")){
            adData=1;
        }else if(adStr.equals("NO")){
            adData=0;
        }
    }

    public void setDataSaved(int s){
        switch (s){
            case 1:
                if(isTiebreak){
                    match.set1[1]=score[0];
                    match.set1[3]=score[1];
                }
                match.set1[0]=set[2 * (setState - 1)];
                match.set1[2]=set[2 * (setState - 1)+1];
                break;
            case 2:
                if(isTiebreak){
                    match.set2[1]=score[0];
                    match.set2[3]=score[1];
                }
                match.set2[0]=set[2 * (setState - 1)];
                match.set2[2]=set[2 * (setState - 1)+1];
                break;
            case 3:
                if(isTiebreak){
                    match.set3[1]=score[0];
                    match.set3[3]=score[1];
                }
                match.set3[0]=set[2 * (setState - 1)];
                match.set3[2]=set[2 * (setState - 1)+1];
                break;
            case 4:
                if(isTiebreak){
                    match.set4[1]=score[0];
                    match.set4[3]=score[1];
                }
                match.set4[0]=set[2 * (setState - 1)];
                match.set4[2]=set[2 * (setState - 1)+1];
                break;
            case 5:
                if(isTiebreak){
                    match.set5[1]=score[0];
                    match.set5[3]=score[1];
                }
                match.set5[0]=set[2 * (setState - 1)];
                match.set5[2]=set[2 * (setState - 1)+1];
                break;
        }
    }

    public void calculate(){

        /** when set state > setState (like 5) , lead to the match is over
         * */

        /** tiebreak time
         * */
        if(set[2 * (setState - 1) + 1]==6&&set[2 * (setState - 1)]==6){
            isTiebreak=true;
            scoreShow[0]= String.valueOf(score[0]);
            scoreShow[1]= String.valueOf(score[1]);
            if(score[0]>=7&&(score[0]-score[1]>=2)){
                set[2 * (setState - 1)] += 1;
            }else if(score[1]>=7&&(score[1]-score[0]>=2)){
                set[2 * (setState - 1)+1] += 1;
            }

        }else {
            /** Game over conditions
             * */
            if (score[0] >= 4 && score[1] < 3) {
                set[2 * (setState - 1)] += 1;
                gameOver(0);
                changeServe();
            }

            if (score[1] >= 4 && score[0] < 3) {
                set[2 * (setState - 1) + 1] += 1;
                gameOver(1);
                changeServe();
            }

            /** Normal score number to Tennis score number
             * */
            switch (score[0]) {
                case 0:
                    scoreShow[0] = "0";
                    break;
                case 1:
                    scoreShow[0] = "15";
                    break;
                case 2:
                    scoreShow[0] = "30";
                    break;
                case 3:
                    scoreShow[0] = "40";
                    break;
            }
            switch (score[1]) {
                case 0:
                    scoreShow[1] = "0";
                    break;
                case 1:
                    scoreShow[1] = "15";
                    break;
                case 2:
                    scoreShow[1] = "30";
                    break;
                case 3:
                    scoreShow[1] = "40";
                    break;
            }

            if (adData == 1) {
                /** If using advance format
                 * */
                if (score[0] >= 3 && score[1] >= 3) {
                    if (score[0] == score[1]) {
                        scoreShow[0] = "40";
                        scoreShow[1] = "40";
                    } else if (score[0] - score[1] == 1) {
                        scoreShow[0] = "Ad";
                        scoreShow[1] = "";
                        if(isServe==0) {
                            match.addServePoints(0, setState);
                            whoSeverAd=0;
                        }else {
                            match.addBreakPoints(0, setState);
                            whoBreakAd=0;
                        }
                    } else if (score[1] - score[0] == 1) {
                        scoreShow[0] = "";
                        scoreShow[1] = "Ad";
                        if(isServe==1) {
                            match.addServePoints(1, setState);
                            whoSeverAd=1;
                        }else {
                            match.addBreakPoints(1, setState);
                            whoBreakAd=1;
                        }
                    } else if (score[0] - score[1] == 2) {
                        set[2 * (setState - 1)] += 1;
                        gameOver(0);
                        changeServe();
                    } else if (score[1] - score[0] == 2) {
                        set[2 * (setState - 1) + 1] += 1;
                        gameOver(1);
                        changeServe();
                    }
                }
                /** end */
            }
        }
        /** end */

        set[10]=setState;

        /** when a set is over then turn into next set
         * */
        if((set[2*(setState-1)+1]==gameData&&set[2*(setState-1)]<=(gameData-2))||(set[2*(setState-1)]==gameData&&set[2*(setState-1)+1]<=(gameData-2))
                ||(set[2*(setState-1)])==gameData+1||(set[2*(setState-1)+1])==gameData+1){
            /** Such as: set[1]==6 && set[0]<=4, || set[0]==6 && set[1]<=4, || set[0]==7, || set[1]==7
             * */
            setDataSaved(setState);
            score[0] = 0;
            score[1] = 0;
            set[10]=setState++; // adding setState after output the set data
        }



    }

    public void putData(int whoWin){
        detectServeOrBreakPoints(whoWin);
        if(whoWin==0){
            score[0]+=1;
        }else {
            score[1]+=1;
        }
    }

    private void detectServeOrBreakPoints(int whoWin){
        if(whoSeverAd==0&&whoWin==0){
            match.addServePointWin(0, setState);
            whoSeverAd=-1;
        }
        if(whoSeverAd==1&&whoWin==1){
            match.addServePointWin(1, setState);
            whoSeverAd=-1;
        }
        if(whoBreakAd==0&&whoWin==0){
            match.addBreakPointWin(0, setState);
            whoBreakAd=-1;
        }
        if(whoBreakAd==1&&whoWin==1){
            match.addBreakPointWin(1, setState);
            whoBreakAd=-1;
        }
    }

    public String[] getData(){
        return scoreShow;
    }

    public int[] getSetData(){
        return set;
    }

    public int isServe(){
        return isServe;
    }

    /** change serve state's value in this class */
    public void changeServe(){
        if(isServe==0){
            isServe=1;
        }else{
            isServe=0;
        }
    }

    private void gameOver(int whoWinLastPoint){
        score[0] = 0;
        score[1] = 0;
        scoreShow[0] = "0";
        scoreShow[1] = "0";
        if(whoWinLastPoint==0){
            match.addServeCount(0, setState);
        }else {
            match.addServeCount(1, setState);
        }
    }

    public boolean isSetOver(){

        return false;
    }

}
