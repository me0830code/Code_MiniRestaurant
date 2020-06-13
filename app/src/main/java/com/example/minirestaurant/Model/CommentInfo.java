package com.example.minirestaurant.Model;

// CommentInfo Model
public class CommentInfo {

    private String cID ;
    private String uID ;
    private String commentDate ;
    private String commentContent ;
    private String commentRating ;

    // Setting cID & uID & commentDate & commentContent & commentRating
    public void init(String cID, String uID, String date, String content, String rating) {
        this.cID = cID ;
        this.uID = uID ;
        this.commentDate = date ;
        this.commentContent = content ;
        this.commentRating = rating ;
    }

    // Read commentID
    public String GetCommentID() {
        return this.cID ;
    }

    // Read userID
    public String GetUserID() {
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
    public String GetCommentRating() {
        return this.commentRating ;
    }
}
