package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserShould {
    private static final User STRANGE_USER = new User();

    @Test
    public void inform_if_users_are_not_friends() {
        User user = new User();
        User anotherUser = new User();

        user.addFriend(STRANGE_USER);

        assertThat(user.isFriendOf(anotherUser), is(false));
    }

    @Test
    public void inform_if_users_are_friends() {
        User user = new User();
        User anotherUser = new User();

        user.addFriend(STRANGE_USER);
        user.addFriend(anotherUser);

        assertThat(user.isFriendOf(anotherUser), is(true));
    }
}
