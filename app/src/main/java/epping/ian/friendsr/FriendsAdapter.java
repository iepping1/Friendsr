package epping.ian.friendsr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// match gridview with friend elements
public class FriendsAdapter extends ArrayAdapter<Friend> {

    // initialize friend elements
    private ArrayList friends;
    public ImageView photo;
    public TextView name;
    public Friend friend;


    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> objects) {
        super(context, resource, objects);
        friends = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // if nothing is viewed
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        // create and initialize friend, photo and name
        friend = (Friend) friends.get(position);
        photo = convertView.findViewById(R.id.imageView);
        name = convertView.findViewById(R.id.textView);

        int id = friend.getDrawableId();

        photo.setImageDrawable(getContext().getResources().getDrawable(id, null));
        name.setText(friend.getName());

        return convertView;
    }
}
