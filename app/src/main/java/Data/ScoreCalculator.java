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
    public boolean isServe;
    public Match match;

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

    public void calculate(){

        /** when set state > setState (like 5) , lead to the match is over
         * */

        /** tiebreak time
         * */
        if(set[2 * (setState - 1) + 1]==6&&set[2 * (setState - 1)]==6){
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
                score[0] = 0;
                score[1] = 0;
                scoreShow[0] = "0";
                scoreShow[1] = "0";
            }

            if (score[1] >= 4 && score[0] < 3) {
                set[2 * (setState - 1) + 1] += 1;
                score[0] = 0;
                score[1] = 0;
                scoreShow[0] = "0";
                scoreShow[1] = "0";
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
                    } else if (score[1] - score[0] == 1) {
                        scoreShow[0] = "";
                        scoreShow[1] = "Ad";
                    } else if (score[0] - score[1] == 2) {
                        set[2 * (setState - 1)] += 1;
                        score[0] = 0;
                        score[1] = 0;
                        scoreShow[0] = "0";
                        scoreShow[1] = "0";
                    } else if (score[1] - score[0] == 2) {
                        set[2 * (setState - 1) + 1] += 1;
                        score[0] = 0;
                        score[1] = 0;
                        scoreShow[0] = "0";
                        scoreShow[1] = "0";
                    }
                }
                /** end */
            }
        }

        set[10]=setState;

        /** when a set is over then turn into next set
         * */
        if((set[2*(setState-1)+1]==gameData&&set[2*(setState-1)]<=(gameData-2))||(set[2*(setState-1)]==gameData&&set[2*(setState-1)+1]<=(gameData-2))
                ||(set[2*(setState-1)])==gameData+1||(set[2*(setState-1)+1])==gameData+1){
            /** Such as: set[1]==6 && set[0]<=4, || set[0]==6 && set[1]<=4, || set[0]==7, || set[1]==7
             * */
            score[0] = 0;
            score[1] = 0;
            set[10]=setState++; // adding setState after output the set data
        }



    }

    public void putData(int whoWin){
        if(whoWin==0){
            score[0]+=1;
        }else {
            score[1]+=1;
        }
    }

    public String[] getData(){
        return scoreShow;
    }

    public int[] getSetData(){
        return set;
    }

    public boolean isServe(){
        return isServe;
    }

    public boolean isSetOver(){

        return false;
    }

}
