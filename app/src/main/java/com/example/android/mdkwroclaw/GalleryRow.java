package com.example.android.mdkwroclaw;

/**
 * Created by AD on 2016-04-24.
 */
public class GalleryRow {
    private String gallery_id;
    private String mdk_name;
    private String gallery_name;
    private String date;

    public GalleryRow(String gallery_id, String mdk_name, String gallery_name, String date) {
        this.gallery_id = gallery_id;
        this.mdk_name = mdk_name;
        this.gallery_name = gallery_name;
        this.date = date;
    }

    public String getGalleryId() {
        return gallery_id;
    }

    public String getMDKName() {
        return mdk_name;
    }

    public String getGalleryName() {
        return gallery_name;
    }

    public String getDate() {
        return date;
    }
}
