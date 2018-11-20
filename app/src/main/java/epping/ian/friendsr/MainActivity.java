package epping.ian.friendsr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // create layout for main screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Friend> friends = new ArrayList<>();

        // add friends to array
        Friend Arya = new Friend("Arya","Valar Morghulis", getResources().getIdentifier
                ("arya", "drawable", MainActivity.this.getPackageName()));
        Friend Cersei = new Friend("Cersei","Hear me Roar", getResources().getIdentifier
                ("cersei", "drawable", MainActivity.this.getPackageName()));
        Friend Daenerys = new Friend("Daenerys","Dracarys", getResources().getIdentifier
                ("daenerys", "drawable", MainActivity.this.getPackageName()));
        Friend Jaime = new Friend("Jaime","Oathbreaker", getResources().getIdentifier
                ("jaime", "drawable", MainActivity.this.getPackageName()));
        Friend Jon = new Friend("Jon","Winter is coming", getResources().getIdentifier
                ("jon", "drawable", MainActivity.this.getPackageName()));
        Friend Melisandre = new Friend("Melisandre","Lord of Light", getResources().getIdentifier
                ("melisandre", "drawable", MainActivity.this.getPackageName()));
        Friend Sansa = new Friend("Sansa","I Play a Game", getResources().getIdentifier
                ("sansa", "drawable", MainActivity.this.getPackageName()));
        Friend Tyrion= new Friend("Tyrion","You are no Son of Mine", getResources().getIdentifier
                ("tyrion", "drawable", MainActivity.this.getPackageName()));

        // add all friends to list
        friends.addAll(Arrays.asList(Arya, Cersei, Daenerys,Jaime,Jon,Melisandre,Sansa,Tyrion));

        // match list with layout
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);

        // create gridview and connect it with listener
        GridView grid = findViewById(R.id.GridView);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new GridItemClickListener());
    }

    // switch to friend's profile window
    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // recognizes selected friend
            Friend clickedFriend = (Friend) adapterView.getItemAtPosition(i);

            // passes information from main to profile.
            Intent intent =  new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }
}
