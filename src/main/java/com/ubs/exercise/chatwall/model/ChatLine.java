package com.ubs.exercise.chatwall.model;

public class ChatLine implements Comparable<ChatLine> {
    private final User user;
    private final String line;
    private final long timeOfPost;

    public ChatLine(User user, String line, long timeOfPost) {
        this.user = user;
        this.line = line;
        this.timeOfPost = timeOfPost;
    }

    @Override
    public int compareTo(ChatLine counter) {
        return Long.compare(counter.getTimeOfPost(), this.timeOfPost);
    }

    public String getLine() {
        return line;
    }

    public long getTimeOfPost() {
        return timeOfPost;
    }

    public String getUserName() {
        return user.getName();
    }
}
