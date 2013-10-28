package com.ubs.exercise.chatwall.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SimpleUser implements User {

    private final String name;

    private final Set<User> subscriptions = new HashSet<>();

    public SimpleUser(String name) {
        this.name = name;
        //subscriptions.add(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void subscribe(User user) {
        if (!this.equals(user)) {
            subscriptions.add(user);
        }
    }

    @Override
    public Set<User> getSubscriptions() {
        return Collections.unmodifiableSet(subscriptions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleUser)) {
            return false;
        }

        SimpleUser that = (SimpleUser) o;

        if (!name.equals(that.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
