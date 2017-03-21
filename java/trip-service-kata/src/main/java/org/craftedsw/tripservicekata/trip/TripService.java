package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private static final List<Trip> NO_TRIPS = new ArrayList<Trip>();
    private final User loggedInUser;

    public TripService() {
        this(UserSession.getInstance().getLoggedUser());
    }

    public TripService(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (isNotValidUser()) {
            throw new UserNotLoggedInException();
        }
        if (user.isFriendOf(loggedInUser)) {
            return tripsBy(user);
        }
        return NO_TRIPS;
    }

    private boolean isNotValidUser() {
        return loggedInUser == null;
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
