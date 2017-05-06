package com.example.hamza.karton.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hamza.karton.R;

public class ActivityShareFragment extends Fragment {

    Button share;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.activity_share_fragment,container,false);
       share= (Button) v.findViewById(R.id.shareButton);
        /*Facebook - "com.facebook.katana"
        Twitter - "com.twitter.android"
        Instagram - "com.instagram.android"
        Pinterest - "com.pinterest"*/

share.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = getContext().getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
        if (intent != null) {
            // The application exists
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setPackage("com.facebook.katana");

            shareIntent.putExtra(android.content.Intent.EXTRA_TITLE, "");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "this is a v good application");
            // Start the specific social application
            getContext().startActivity(shareIntent);
        } else {
            // The application does not exist
            // Open GooglePlay or use the default system picker
        }
    }
});


        return v;
    }
}
