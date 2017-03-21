package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TripServiceShould {
    private static final User GUEST = null;
    private static final User ANY_USER = new User();
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip BADAJOZ = new Trip();
    private static final Trip MURCIA = new Trip();
    private User loggedInUser;
    private TripService tripService;

    @Before
    public void setUp() throws Exception {
        tripService = new TestableTripService(REGISTERED_USER);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void validate_logged_in_user() {
        tripService = new TestableTripService(GUEST);
        loggedInUser = GUEST;
        tripService.getTripsByUser(ANY_USER);
    }

    @Test
    public void return_no_trips_when_users_are_not_friends() {
        User user = UserBuilder.aUser()
                        .addFriends(ANOTHER_USER)
                        .addTrips(BADAJOZ)
                        .build();

        List<Trip> tripList = tripService.getTripsByUser(user);
        assertThat(tripList.size(), is(0));
    }

    @Test
    public void return_trips_when_users_are_friends() {
        User user = UserBuilder.aUser()
                        .addFriends(ANOTHER_USER, REGISTERED_USER)
                        .addTrips(BADAJOZ, MURCIA)
                        .build();

        List<Trip> tripList = tripService.getTripsByUser(user);
        assertThat(tripList.size(), is(2));
    }

    private class TestableTripService extends TripService {

        public TestableTripService(User user) {
            super(user);
        }

        @Override
        protected List<Trip> tripsBy(User user) {
            return user.trips();
        }
    }
}