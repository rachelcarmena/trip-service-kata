package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

class UserBuilder {
    private User[] friends = new User[0];
    private Trip[] trips = new Trip[0];

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder addFriends(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder addTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        for(User friend: friends)
            user.addFriend(friend);
        for(Trip trip: trips)
            user.addTrip(trip);
        return user;
    }
}
