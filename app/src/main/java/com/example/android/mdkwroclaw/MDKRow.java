package com.example.android.mdkwroclaw;

public class MDKRow {
    private String mdk_full_name;
    private String mdk_street;
    private String mdk_city;
    private String mdk_phone_number;
    private String mdk_website;
    private String mdk_boss;
    private String mdk_transport_bus;
    private String mdk_transport_tram;

    MDKRow(String mdk_full_name, String mdk_street, String mdk_city, String mdk_phone_number, String mdk_website, String mdk_boss, String mdk_transport_bus, String mdk_transport_tram) {
        this.mdk_full_name = mdk_full_name;
        this.mdk_street = mdk_street;
        this.mdk_city = mdk_city;
        this.mdk_phone_number = mdk_phone_number;
        this.mdk_website = mdk_website;
        this.mdk_boss = mdk_boss;
        this.mdk_transport_bus = mdk_transport_bus;
        this.mdk_transport_tram = mdk_transport_tram;
    }

    public String getMDKFullName() {
        return mdk_full_name;
    }

    public String getMDKStreet() {
        return mdk_street;
    }

    public String getMDKCity() {
        return mdk_city;
    }

    public String getMDKPhoneNumber() {
        return mdk_phone_number;
    }

    public String getMDKWebsite() {
        return mdk_website;
    }

    public String getMDKBoss() {
        return mdk_boss;
    }

    public String getMDKTransportBus() {
        return mdk_transport_bus;
    }

    public String getMDKTransportTram() {
        return mdk_transport_tram;
    }
}
