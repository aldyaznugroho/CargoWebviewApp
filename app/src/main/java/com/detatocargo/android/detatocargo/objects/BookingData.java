package com.detatocargo.android.detatocargo.objects;

/**
 * Created by Aldyaz on 6/2/2016.
 */
public class BookingData {

    public int idBooking;
    public String dateTimeInputted, nameBooking, emailBooking, phoneBooking, addressBooking, awbBooking, commodityBooking, destinationBooking, flightBooking, pcsBooking, weightBooking, dimensionBooking, shipperBooking, consigneeBooking;

    public BookingData() {
    }

    public BookingData(
            int idBooking,
            String dateTimeInputted,
            String nameBooking,
            String emailBooking,
            String phoneBooking,
            String addressBooking,
            String awbBooking,
            String commodityBooking,
            String destinationBooking,
            String flightBooking,
            String pcsBooking,
            String weightBooking,
            String dimensionBooking,
            String shipperBooking,
            String consigneeBooking
    ) {

        this.idBooking = idBooking;
        this.dateTimeInputted = dateTimeInputted;
        this.nameBooking = nameBooking;
        this.emailBooking = emailBooking;
        this.phoneBooking = phoneBooking;
        this.addressBooking = addressBooking;
        this.awbBooking = awbBooking;
        this.commodityBooking = commodityBooking;
        this.destinationBooking = destinationBooking;
        this.flightBooking = flightBooking;
        this.pcsBooking = pcsBooking;
        this.weightBooking = weightBooking;
        this.dimensionBooking = dimensionBooking;
        this.shipperBooking = shipperBooking;
        this.consigneeBooking = consigneeBooking;

    }

    public BookingData(
            String nameBooking,
            String emailBooking,
            String phoneBooking,
            String addressBooking,
            String commodityBooking,
            String destinationBooking,
            String flightBooking,
            String pcsBooking,
            String weightBooking,
            String dimensionBooking,
            String shipperBooking,
            String consigneeBooking
    ) {
        this.nameBooking = nameBooking;
        this.emailBooking = emailBooking;
        this.phoneBooking = phoneBooking;
        this.addressBooking = addressBooking;
        this.commodityBooking = commodityBooking;
        this.destinationBooking = destinationBooking;
        this.flightBooking = flightBooking;
        this.pcsBooking = pcsBooking;
        this.weightBooking = weightBooking;
        this.dimensionBooking = dimensionBooking;
        this.shipperBooking = shipperBooking;
        this.consigneeBooking = consigneeBooking;
    }

    public BookingData(
            String nameBooking,
            String emailBooking,
            String phoneBooking,
            String addressBooking,
            String awbBooking,
            String commodityBooking,
            String destinationBooking,
            String flightBooking,
            String pcsBooking,
            String weightBooking,
            String dimensionBooking,
            String shipperBooking,
            String consigneeBooking
    ) {

        this.nameBooking = nameBooking;
        this.emailBooking = emailBooking;
        this.phoneBooking = phoneBooking;
        this.addressBooking = addressBooking;
        this.awbBooking = awbBooking;
        this.commodityBooking = commodityBooking;
        this.destinationBooking = destinationBooking;
        this.flightBooking = flightBooking;
        this.pcsBooking = pcsBooking;
        this.weightBooking = weightBooking;
        this.dimensionBooking = dimensionBooking;
        this.shipperBooking = shipperBooking;
        this.consigneeBooking = consigneeBooking;

    }

    public String getDateTimeInputted() {
        return dateTimeInputted;
    }

    public void setDateTimeInputted(String dateTimeInputted) {
        this.dateTimeInputted = dateTimeInputted;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public String getNameBooking() {
        return nameBooking;
    }

    public void setNameBooking(String nameBooking) {
        this.nameBooking = nameBooking;
    }

    public String getEmailBooking() {
        return emailBooking;
    }

    public void setEmailBooking(String emailBooking) {
        this.emailBooking = emailBooking;
    }

    public String getPhoneBooking() {
        return phoneBooking;
    }

    public void setPhoneBooking(String phoneBooking) {
        this.phoneBooking = phoneBooking;
    }

    public String getAddressBooking() {
        return addressBooking;
    }

    public void setAddressBooking(String addressBooking) {
        this.addressBooking = addressBooking;
    }

    public String getAwbBooking() {
        return awbBooking;
    }

    public void setAwbBooking(String awbBooking) {
        this.awbBooking = awbBooking;
    }

    public String getCommodityBooking() {
        return commodityBooking;
    }

    public void setCommodityBooking(String commodityBooking) {
        this.commodityBooking = commodityBooking;
    }

    public String getDestinationBooking() {
        return destinationBooking;
    }

    public void setDestinationBooking(String destinationBooking) {
        this.destinationBooking = destinationBooking;
    }

    public String getFlightBooking() {
        return flightBooking;
    }

    public void setFlightBooking(String flightBooking) {
        this.flightBooking = flightBooking;
    }

    public String getPcsBooking() {
        return pcsBooking;
    }

    public void setPcsBooking(String pcsBooking) {
        this.pcsBooking = pcsBooking;
    }

    public String getWeightBooking() {
        return weightBooking;
    }

    public void setWeightBooking(String weightBooking) {
        this.weightBooking = weightBooking;
    }

    public String getDimensionBooking() {
        return dimensionBooking;
    }

    public void setDimensionBooking(String dimensionBooking) {
        this.dimensionBooking = dimensionBooking;
    }

    public String getShipperBooking() {
        return shipperBooking;
    }

    public void setShipperBooking(String shipperBooking) {
        this.shipperBooking = shipperBooking;
    }

    public String getConsigneeBooking() {
        return consigneeBooking;
    }

    public void setConsigneeBooking(String consigneeBooking) {
        this.consigneeBooking = consigneeBooking;
    }
}
