package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceShould {
    private static final User GUEST = null;
    private static final User ANY_USER = new User();
    private User loggedInUser;

    @Test(expected = UserNotLoggedInException.class)
    public void validate_logged_in_user() {
        TripService tripService = new TestableTripService();
        loggedInUser = GUEST;
        tripService.getTripsByUser(ANY_USER);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }
    }
}
