package epping.ian.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.Toast;

// create profile window
public class ProfileActivity extends AppCompatActivity {

    Friend retrievedFriend;
    SharedPreferences prefsM;
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
        TextView name = findViewById(R.id.friendname);
        name.setText(retrievedFriend.getName());

        // retrieving friends bio
        TextView bio = findViewById(R.id.friendbio);
        bio.setText(retrievedFriend.getBio());

        // retrieving and storing personal message when changed
        final EditText personal = findViewById(R.id.personal);

        // stores personal message
        prefsM = getSharedPreferences("friend_message", MODE_PRIVATE);

        // watches changes to message
        personal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence message, int start, int before, int count){
                String storedMessage = prefsM.getString("message" + retrievedFriend.getMessage(), null);
                if (storedMessage != "") {
                    retrievedFriend.setMessage(storedMessage);
                }
                personal.setText(storedMessage);
            }

            public void onTextChanged(CharSequence message, int start, int count, int after){
                String currentMessage = retrievedFriend.getMessage();
                editor = prefs.edit();
                editor.putString("message", currentMessage);
                editor.apply();
            }
            public void afterTextChanged(Editable message){
                String storedMessage = prefsM.getString("message" + retrievedFriend.getMessage(), null);
                if (storedMessage != "") {
                    retrievedFriend.setMessage(storedMessage);
                }
                personal.setText(storedMessage);
            }
        });

        // retrieving friends rating
        RatingBar ratingBar = findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener());

        // stores ratings
        prefs = getSharedPreferences("friend_rating", MODE_PRIVATE);
        float storedRating = prefs.getFloat("rating" + retrievedFriend.getName(), 0);

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
