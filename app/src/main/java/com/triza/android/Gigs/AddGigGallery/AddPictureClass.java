package com.triza.android.Gigs.AddGigGallery;

import android.graphics.Bitmap;

public class AddPictureClass {
    private Bitmap bitmap;

    AddPictureClass(Bitmap bitmap){
        this.bitmap = bitmap;

    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
