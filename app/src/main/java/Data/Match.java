package Data;

/**
 * Created by Shuyun on 3/13/2016/013.
 */
public class Match {
    /** Player1's match data
     * */
    int firstServeWin1=0, secondServeWin1=0, ace1=0;
    int leftNetWin1=0, hightNetWin1=0, rightNetWin1=0;
    int leftBaseWin1=0, highBaseWin1=0, rightBaseWin1=0;

    int firstServeFault1=0, doubleFault1=0;
    int leftNetFault1=0, hightNetFault1=0, rightNetFault1=0;
    int leftBaseFault1=0, highBaseFault1=0, rightBaseFault1=0;
    
    int serveBreakPoint1, returnBreakPoint1;

    /** Player2's match data
     * */
    int firstServeWin2=0, secondServeWin2=0, ace2=0;
    int leftNetWin2=0, hightNetWin2=0, rightNetWin2=0;
    int leftBaseWin2=0, highBaseWin2=0, rightBaseWin2=0;

    int firstServeFault2=0, doubleFault2=0;
    int leftNetFault2=0, hightNetFault2=0, rightNetFault2=0;
    int leftBaseFault2=0, highBaseFault2=0, rightBaseFault2=0;

    int serveBreakPoint2=0, returnBreakPoint2=0;

    /** Match state
     * */
    int serveState=0;

    
}
