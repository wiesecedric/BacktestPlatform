import java.util.Arrays;

/**
 * This class is meant to process a line of tickdata downloaded from the Meta-Trader-5
 * Each data-string is treated as an object
 * Format: date TAB time TAB bid TAB ask TAB last TAB volume TAB flags newline
 */
public class TickData {

    private Date timestamp;
    private float bidPrice;
    private float askPrice;

    /**
     * Constructor for a TickDataObject
     * @param tickLine
     */
    TickData(String tickLine){

        String[] splittedTickline = tickLine.split("\t");
        String[] splittedDate = splittedTickline[0].split("\\.");
        String[] splittedTime = splittedTickline[1].split(":");
        this.timestamp = new Date(Integer.parseInt(splittedDate[0]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedTime[0]), Integer.parseInt(splittedTime[1]), Integer.parseInt(splittedTime[2].split("\\.")[0]), Integer.parseInt(splittedTime[2].split("\\.")[1]));
        if (splittedTickline[2].isEmpty()){
            this.bidPrice = 0;
        }else {
            this.bidPrice = Float.parseFloat(splittedTickline[2]);                  //if either a bid or ask price is not documented, it will be set to 0, in order to check later on
        }
        if (splittedTickline[3].isEmpty()){
            this.askPrice = 0;
        }else{
            this.askPrice = Float.parseFloat(splittedTickline[3]);
        }

    }




    @Override
    public String toString(){
        return this.timestamp.toString() + "\t" + String.valueOf(this.bidPrice) + "\t" + String.valueOf(this.askPrice);
    }

    //Getters & Setters>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(float bidPrice) {
        this.bidPrice = bidPrice;
    }

    public float getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(float askPrice) {
        this.askPrice = askPrice;
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}
