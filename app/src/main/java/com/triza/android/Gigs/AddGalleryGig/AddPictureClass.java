package com.triza.android.Gigs.AddGalleryGig;

import android.content.Intent;
import android.net.Uri;

public class AddPictureClass {
    private Uri uri;

    AddPictureClass(Uri uri){
        this.uri = uri;

    }

    public Uri getUri() {
        return uri;
    }
}
