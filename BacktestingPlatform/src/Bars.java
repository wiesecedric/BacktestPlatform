public class Bars {

    private Date openTime;
    private float close;


    Bars(String line){

        String[] lineSplitted = line.split("\t");
        String[] dateSplitted = lineSplitted[0].split("\\.");
        String[] timeSplitted = lineSplitted[1].split(":");
        this.openTime = new Date(Integer.parseInt(dateSplitted[0]),Integer.parseInt(dateSplitted[1]),Integer.parseInt(dateSplitted[2]),Integer.parseInt(timeSplitted[0]),Integer.parseInt(timeSplitted[1]),Integer.parseInt(timeSplitted[2]),0);
        this.close = Float.parseFloat(lineSplitted[5]);

    }




    //Getters & Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }
}
