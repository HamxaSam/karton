package com.example.hamza.karton.nevigationDrawerItemClickClasses;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hamza.karton.R;

public class ReportBugFragment extends Fragment {
    Button button_reportBug;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_report_bug_fragment, container, false);
        button_reportBug = (Button) v.findViewById(R.id.button_reportBug);
        button_reportBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "hamza.ali6307@gmail.com.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        return v;
    }
}

