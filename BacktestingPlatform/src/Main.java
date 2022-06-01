import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException { //args: Line Input, Tick Input,

        //Create Stack of Lines:
        Stack<IndicatorLine> totalLines = new Stack<>();

        //Go through line file and fill stack accordingly

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = br.readLine()) != null){
            IndicatorLine tmp = new IndicatorLine(line);
            totalLines.push(tmp);
        }

        //create Stacks for active Lines

        Stack<IndicatorLine> activeLongLines = new Stack<>();
        Stack<IndicatorLine> activeShortLines = new Stack<>();

        // go through tickFile

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

            //continue here with backtest Strategy

        }


    }

}
