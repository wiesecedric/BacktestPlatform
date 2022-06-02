/**
 * A Class for creating an Indicator Line
 */
public class IndicatorLine {

    private Date timestamp;
    private boolean isLong;
    private String originalLine;
    private float lineLevel;

    IndicatorLine(String line){
        if (line.split("y")[1].equals("Green")){
            this.isLong = true;
        }else {
            this.isLong = false;
        }

        this.timestamp = getDateOfFile(line);
        this.originalLine = line;
    }




    /**
     * extract date for each line in file
     * @param line
     * @return
     */
    private Date getDateOfFile(String line){
        String[] splittedLine = line.split(" ");
        String[] splittedDate = splittedLine[2].split("\\.");
        String[] splittedTime = splittedLine[3].split(":");
        //return statement has to be adjusted for the date (e.g if minutes exist)
        return new Date(Integer.parseInt(splittedDate[0]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedTime[0]), Integer.parseInt(splittedTime[1].split("y")[0]),0,0);

    }




    @Override
    public String toString(){
        return this.originalLine;
    }

    //Getters & Setters <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isLong() {
        return isLong;
    }

    public void setLong(boolean aLong) {
        isLong = aLong;
    }

    public String getOriginalLine() {
        return originalLine;
    }

    public void setOriginalLine(String originalLine) {
        this.originalLine = originalLine;
    }

    public float getLineLevel() {
        return lineLevel;
    }

    public void setLineLevel(float lineLevel) {
        this.lineLevel = lineLevel;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><

}
