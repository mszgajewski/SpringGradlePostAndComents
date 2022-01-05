package com.example.SpringGradlePostAndComents.model;

public class Comment {
    @Id
    @GeneratedValue(strategy = Generationtype.IDENTITY)
    private long id;
    private long postId;
    private String content;
    private LocalDateTime created;
}