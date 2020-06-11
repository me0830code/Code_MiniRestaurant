package com.example.minirestaurant.Model;

// CommentInfo Model
public class CommentInfo {

    private int cID ;
    private int uID ;
    private String commentDate ;
    private String commentContent ;
    private int commentRating ;

    // Setting cID & uID & commentDate & commentContent & commentRating
    public void init(int cID, int uID, String date, String content, int rating) {
        this.cID = cID ;
        this.uID = uID ;
        this.commentDate = date ;
        this.commentContent = content ;
        this.commentRating = rating ;
    }

    // Read commentID
    public int GetCommentID() {
        return this.cID ;
    }

    // Read userID
    public int GetUserID() {
        return this.uID ;
    }

    // Read commentDate
    public String GetCommentDate() {
        return this.commentDate ;
    }

    // Read commentContent
    public String GetCommentContent() {
        return this.commentContent ;
    }

    // Read commentRating
    public int GetCommentRating() {
        return this.commentRating ;
    }
}
