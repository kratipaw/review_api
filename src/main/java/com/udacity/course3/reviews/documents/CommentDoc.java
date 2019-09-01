package com.udacity.course3.reviews.documents;

import com.udacity.course3.reviews.entity.Comment;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CommentDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String commentString;

    public CommentDoc(){

    }

    public CommentDoc(Comment comment){
        this.id = comment.getId();
        this.commentString = comment.getCommentString();
    }

    public int getId() {
        return id;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }
}
