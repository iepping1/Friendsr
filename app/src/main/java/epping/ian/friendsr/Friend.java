package epping.ian.friendsr;

import java.io.Serializable;

public class Friend implements Serializable{

    // create variables of all friends
    private String name, bio, message;
    private int drawableId;
    private float rating;

    // create friend Constructor to give variables value
    public Friend(String name, String bio, int drawableId) {
        this.name = name;
        this.bio = bio;
        this.drawableId = drawableId;
    }

    // get values of each friend
    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getMessage() { return message; }

    public int getDrawableId() {
        return drawableId;
    }

    public float getRating() {
        return rating;
    }

    // set personal message and reating of each friend
    public void setMessage(String message) { this.message = message; }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
