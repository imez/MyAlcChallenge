package com.example.android.alcchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DeveloperProfileDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_profile_display);


        Dev_Git dev = getIntent().getParcelableExtra("dev");

        TextView userName = (TextView) findViewById(R.id.user_name);
        TextView profileUrl = (TextView)findViewById(R.id.profile_url);

        ImageView devProfileAvatar = (ImageView) findViewById(R.id.avatar);

        userName.setText(dev.getLogin().toString());

        profileUrl.setText(dev.getUrl().toString());

        Picasso.with(this)
                .load(dev.getAvatarUrl())
                .fit()
                .placeholder(R.drawable.profile_avatar_placeholder_large)
                .error(R.drawable.profile_avatar_placeholder_large)
                .into(devProfileAvatar);
        Button share = (Button) findViewById(R.id.share_user);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Share this awesome developer.");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Share this awesome developer."));
            }
        });







    }
}
