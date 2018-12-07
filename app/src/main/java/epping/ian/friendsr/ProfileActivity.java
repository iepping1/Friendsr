package epping.ian.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


// create profile window
public class ProfileActivity extends AppCompatActivity {

    Friend retrievedFriend;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // extract friend values from when they're called
        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // retrieving friends image
        ImageView photo = findViewById(R.id.friendphoto);
        photo.setImageResource(retrievedFriend.getDrawableId());

        // retrieving friends name
        final String named = retrievedFriend.getName();
        TextView name = findViewById(R.id.friendname);
        name.setText(named);

        // retrieving friends bio
        TextView bio = findViewById(R.id.friendbio);
        bio.setText(retrievedFriend.getBio());

        // retrieving friends rating
        RatingBar ratingBar = findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener());

        // stores ratings
        prefs = getSharedPreferences("friend_rating", MODE_PRIVATE);
        float storedRating = prefs.getFloat("rating" + named, 0);

        //retrieve stored rating
        if (storedRating != 0) {
            ratingBar.setRating(storedRating);
        }
    }

    // handles changes in rating
    private class OnRatingBarChangeListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

            // acquires the name of the rated friend and stores the changed rating
            editor = getSharedPreferences("friend_rating", MODE_PRIVATE).edit();
            editor.putFloat("rating" + retrievedFriend.getName(), rating);
            editor.apply();
        }
    }
}
