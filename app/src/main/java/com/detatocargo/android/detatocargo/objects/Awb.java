package com.detatocargo.android.detatocargo.objects;

/**
 * Created by Aldyaz on 5/28/2016.
 */
public class Awb {

    public int awbId;
    public String awbNumber;
    public String dateTimeInputted;

    public Awb() {
    }

    public Awb(int awbId, String awbNumber, String dateTimeInputted) {
        this.awbId = awbId;
        this.awbNumber = awbNumber;
        this.dateTimeInputted = dateTimeInputted;
    }

    public Awb(int awbId, String awbNumber) {
        this.awbId = awbId;
        this.awbNumber = awbNumber;
    }

    public Awb(String awbNumber, String dateTimeInputted) {
        this.awbNumber = awbNumber;
        this.dateTimeInputted = dateTimeInputted;
    }

    public int getAwbId() {
        return awbId;
    }

    public void setAwbId(int awbId) {
        this.awbId = awbId;
    }

    public String getDateTimeInputted() {
        return dateTimeInputted;
    }

    public void setDateTimeInputted(String dateTimeInputted) {
        this.dateTimeInputted = dateTimeInputted;
    }

    public String getAwbNumber() {
        return awbNumber;
    }

    public void setAwbNumber(String awbNumber) {
        this.awbNumber = awbNumber;
    }
}
