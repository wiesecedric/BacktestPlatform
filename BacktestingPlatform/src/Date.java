/**
 * This Class is used for creating Date-Objects, allowing for comparison, and addition of Date-Objects
 * Format: YEAR:MONTH:DAY:HOUR:MINUTE:SECOND:MILLISECOND
 */
public class Date {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int millisecond;

    /**
     * Creating a Date-Object, parsing all necessary values
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param millisecond
     */
    Date(int year, int month, int day, int hour, int minute, int second, int millisecond){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    /**
     * This function is used to modify a Date by another Date (e.g add 3 hours to Date)
     * @param addedDate
     */
    public void addDate(Date addedDate) {

        int overflow; //add variable for catching overflows (e.g 20 hours + 8 hours has an overflow of 1)

        this.millisecond = (overflow = (this.millisecond + addedDate.millisecond)) % 1000;
        overflow = Math.floorDiv(overflow,1000);

        this.second = (overflow = (this.second + addedDate.second + overflow)) % 60;
        overflow = Math.floorDiv(overflow,60);

        this.minute = (overflow = (this.minute + addedDate.minute + overflow)) % 60;
        overflow = Math.floorDiv(overflow,60);

        this.hour = (overflow = (this.hour + addedDate.hour + overflow)) % 24;
        overflow = Math.floorDiv(overflow,24);


        int daysOfCurrentMonth;
        if (this.month == 2 || this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11){
            if(this.month == 2){
                if(this.year % 4 == 0){
                    daysOfCurrentMonth = 29;                          //find out how many days the current month has (important for overflow)
                }else{
                    daysOfCurrentMonth = 28;
                }
            }else{
                daysOfCurrentMonth = 30;
            }
        }else{
            daysOfCurrentMonth = 31;
        }

        this.day = (overflow = (this.day + addedDate.day + overflow)) % (++daysOfCurrentMonth);
        overflow = Math.floorDiv(overflow,daysOfCurrentMonth);
        if (overflow != 0){
            this.day++;
        }
        this.month = (overflow = (this.month + addedDate.month + overflow)) % 13;
        overflow = Math.floorDiv(overflow,13);
        if (overflow != 0){
            this.month++;
        }

        this.year = this.year + addedDate.year + overflow;

    }

    /**
     * A function used to subtract a date from another
     * @param subtractor
     */
    public void subtractDate(Date subtractor){

        int overflow = 0;

        if (this.millisecond < subtractor.millisecond){
            overflow = 1;
            this.millisecond = 1000 + (this.millisecond - subtractor.millisecond);
        }else{
            this.millisecond = this.millisecond - subtractor.millisecond;
        }

        if ((this.second - overflow) < subtractor.second){
            overflow = 1;
            this.second = 60 + (this.second - subtractor.second);
        }else{
            this.second = this.second - subtractor.second - overflow;
            overflow = 0;
        }

        if ((this.minute - overflow) < subtractor.minute){
            overflow = 1;
            this.minute = 60 + (this.minute - subtractor.minute);
        }else{
            this.minute = this.minute - subtractor.minute - overflow;
            overflow = 0;
        }

        if ((this.hour - overflow) < subtractor.hour){
            overflow = 1;
            this.hour = 24 + (this.hour - subtractor.hour);
        }else{
            this.hour = this.hour - subtractor.hour - overflow;
            overflow = 0;
        }

        if ((this.day - overflow - 1) < subtractor.day){
            int daysOfPreviousMonth;
            if ((this.month - 1) == 1 || (this.month - 1) == 3 || (this.month - 1) == 5 || (this.month - 1) == 7 || (this.month - 1) == 8 || (this.month - 1) == 10 || (this.month - 1) == 12){
                daysOfPreviousMonth = 31;
            }else{
                if((this.month - 1) == 2){
                    if (this.year % 4 == 0){
                        daysOfPreviousMonth = 29;
                    }else{
                        daysOfPreviousMonth = 28;
                    }
                }else{
                    daysOfPreviousMonth = 30;
                }
            }
            overflow = 1;
            this.day = daysOfPreviousMonth + (this.day - subtractor.day);
        }else{
            this.day = this.day - subtractor.day - overflow;
            overflow = 0;
        }

        if ((this.month - overflow - 1) < subtractor.month){
            overflow = 1;
            this.month = 12 + (this.month - subtractor.month - 1);
        }else{
            this.month = this.month - subtractor.month - overflow;
            overflow = 0;
        }

        this.year = this.year - subtractor.year - overflow;

    }

    /**
     * Compares two Dates, returning -1 if THIS date is earlier, 0 if equal and +1 if comparedDate is earlier
     * @param comparedDate
     * @return
     */
    public int compareDate(Date comparedDate){
                                                                    //start with year, comparing step by step (trivial)
        if (this.year != comparedDate.year){
            if (this.year < comparedDate.year){
                return -1;
            }return 1;
        } else if (this.month != comparedDate.month) {
            if (this.month < comparedDate.month){
                return -1;
            }return 1;
        } else if (this.day != comparedDate.day) {
            if (this.day < comparedDate.day){
                return -1;
            }return 1;
        } else if (this.hour != comparedDate.hour) {
            if (this.hour < comparedDate.hour){
                return -1;
            }return 1;
        } else if (this.minute != comparedDate.minute) {
            if (this.minute < comparedDate.minute){
                return -1;
            }return 1;
        } else if (this.second != comparedDate.second) {
           if (this.second < comparedDate.second){
               return -1;
           }return 1;
        } else if (this.millisecond != comparedDate.millisecond) {
            if (this.millisecond < comparedDate.millisecond){
                return -1;
            }return 1;
        }return 0;

    }

    /**
     * To String Method returning YYYY:MM:DD:HH:MM:SS:MSS without leading zeroes
     * @return
     */
    @Override
    public String toString(){
        return Integer.toString(this.year) + ":" + Integer.toString(this.month) + ":" + Integer.toString(this.day) + ":" + Integer.toString(this.hour) + ":" + Integer.toString(this.minute) + ":" + Integer.toString(this.second) + ":" + Integer.toString(this.millisecond);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>> Getter&Setter
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}
