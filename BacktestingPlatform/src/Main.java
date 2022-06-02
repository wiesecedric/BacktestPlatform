import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException { //args: Line Input, Tick Input, Config file, LineCloseFile

        //read the config file



        BufferedReader br = new BufferedReader(new FileReader(args[2]));
        float minStop = Float.parseFloat(br.readLine().split(":")[1]);
        float maxStop = Float.parseFloat(br.readLine().split(":")[1]);
        float minR = Float.parseFloat(br.readLine().split(":")[1]);
        float maxR = Float.parseFloat(br.readLine().split(":")[1]);
        float stepR = Float.parseFloat(br.readLine().split(":")[1]);
        float entryAtLine = Float.parseFloat(br.readLine().split(":")[1]);
        String line = br.readLine();
        String[] dateString = line.split(":")[1].split("\\.");
        Date lineDate = new Date(Integer.parseInt(dateString[0]),Integer.parseInt(dateString[1]),Integer.parseInt(dateString[2]),Integer.parseInt(dateString[3]),Integer.parseInt(dateString[4]),Integer.parseInt(dateString[5]),Integer.parseInt(dateString[6]));

        float currentStop = 0;

        ArrayList<Trade> currentTrades = new ArrayList<>();

        //Create Stack of Lines:
        ArrayList<IndicatorLine> totalLines = new ArrayList<>();

        //Go through line file and fill stack accordingly

        br = new BufferedReader(new FileReader(args[0]));
        while ((line = br.readLine()) != null){
            IndicatorLine tmp = new IndicatorLine(line);
            tmp.getTimestamp().addDate(lineDate);  //shift line date to the right to get active timestamp
            totalLines.add(tmp);
        }

        ArrayList<Bars> allBars = new ArrayList<>();

        br = new BufferedReader(new FileReader(args[3]));
        while ((line = br.readLine()) != null){
            Bars tmpBar = new Bars(line);
            allBars.add(tmpBar);
        }

        for (IndicatorLine il:totalLines) {
            il.getTimestamp().subtractDate(lineDate);
            il.getTimestamp().subtractDate(lineDate);
            for (Bars b : allBars) {
                if(il.getTimestamp().compareDate(b.getOpenTime()) == 0){
                    il.setLineLevel(b.getClose());
                    il.getTimestamp().addDate(lineDate);
                    il.getTimestamp().addDate(lineDate);
                }
            }
        } /////here finished with creating indicator lines and adding close prices of proper bars to it :: not yet tested

        //create Stacks for active Lines

        Stack<IndicatorLine> activeLongLines = new Stack<>();
        Stack<IndicatorLine> activeShortLines = new Stack<>();

        // go through tickFile

        Trade currentTrade = null;

        br = new BufferedReader(new FileReader(args[1]));
        while ((line = br.readLine()) != null){

            TickData currentTick = new TickData(line);

            //check if new line needs to be active

            if (currentTick.getTimestamp().compareDate(totalLines.peek().getTimestamp()) != -1){
                if (totalLines.peek().isLong() == true){
                    activeLongLines.push(totalLines.pop());
                }else{
                    activeShortLines.push(totalLines.pop());
                }
            }

            //check if a current Trade needs to be closed

            if (currentTrade != null){

                //check whether the Trade is long
                if (currentTrade.isLong()){

                    //check whether the bidPrice is below stop loss

                    if (currentTick.getBidPrice() < currentTrade.getStopLevel()){

                        currentTrade.setClosePrice(currentTick.getBidPrice());
                        currentTrade.setRiskReward((currentTrade.getOpenPrice() - currentTrade.getClosePrice()) / currentStop);
                        currentTrades.add(currentTrade);
                        currentTrade = null;
                    }

                    //check whether the askPrice is above take profit

                    if (currentTick.getBidPrice() > currentTrade.getTakeProfitLevel()){

                        currentTrade.setClosePrice(currentTick.getBidPrice());
                        currentTrade.setRiskReward((currentTrade.getOpenPrice() - currentTrade.getClosePrice()) / currentStop );
                        currentTrades.add(currentTrade);
                        currentTrade = null;
                    }

                }else{ //happens when Trade is short

                    //check whether the askPrice is above stop Loss

                    if (currentTick.getAskPrice() > currentTrade.getStopLevel()){

                        currentTrade.closeTrade(currentTick,currentStop);
                        currentTrades.add(currentTrade);
                        currentTrade = null;

                    }
                    //check whether the askPrice is below takeProfit
                    if (currentTick.getAskPrice() < currentTrade.getTakeProfitLevel()){

                        currentTrade.closeTrade(currentTick,currentStop);
                        currentTrades.add(currentTrade);
                        currentTrade = null;

                    }

                }


            }

            // check if there are active Lines

            if (activeLongLines.peek() != null ){

                //check if Trade needs to be opened due to Long Line



            }



        }




    }

}
