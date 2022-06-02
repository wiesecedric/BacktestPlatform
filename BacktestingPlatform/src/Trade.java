/**
 * A class creating Trade-Objects
 */
public class Trade {

    private Date opened;
    private Date closed;
    private boolean isLong;
    private float openPrice;
    private float closePrice;
    private float riskReward;
    private float stopLevel;
    private float takeProfitLevel;


    Trade(Date opened, boolean isLong, float openPrice, float stop, float tp){
        this.opened = opened;
        this.isLong = isLong;
        this.openPrice = openPrice;
        this.stopLevel = stop;
        this.takeProfitLevel = tp;
    }

    /**
     * Function to close a Trade, giving it closeTime, RiskReward and closePrice
     * @param tick
     * @param currentStop
     */
    public void closeTrade(TickData tick, float currentStop){

        if (this.isLong){

            this.closePrice = tick.getBidPrice();
            this.riskReward = (this.closePrice - this.openPrice) / currentStop;


        }else{

            this.closePrice = tick.getAskPrice();
            this.riskReward = (this.openPrice - this.closePrice) / currentStop;


        }
        this.closed = tick.getTimestamp();

    }














    @Override
    public String toString(){
        String isLong;
        if (this.isLong){
            isLong = "Long";
        }else{
            isLong = "Short";
        }
        return "Date: " + this.opened.toString() + "\t" + "Openprice: " + this.openPrice + "\t" + "Closeprice: " + this.closePrice + "isLong: " + isLong + "\t" + "R: " + this.riskReward + "\t" + "TPlvl: " + this.takeProfitLevel + "\t" + "StOPlvl: " + this.stopLevel;
    }

    //Getters & Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public boolean isLong() {
        return isLong;
    }

    public void setLong(boolean aLong) {
        isLong = aLong;
    }

    public float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(float openPrice) {
        this.openPrice = openPrice;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(float closePrice) {
        this.closePrice = closePrice;
    }

    public float getRiskReward() {
        return riskReward;
    }

    public void setRiskReward(float riskReward) {
        this.riskReward = riskReward;
    }

    public float getStopLevel() {
        return stopLevel;
    }

    public void setStopLevel(float stopLevel) {
        this.stopLevel = stopLevel;
    }

    public float getTakeProfitLevel() {
        return takeProfitLevel;
    }

    public void setTakeProfitLevel(float takeProfitLevel) {
        this.takeProfitLevel = takeProfitLevel;
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}
