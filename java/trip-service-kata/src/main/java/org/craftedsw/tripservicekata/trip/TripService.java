package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	private static final List<Trip> NO_TRIPS = new ArrayList<Trip>();

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedInUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		if (user.isFriendOf(loggedUser)) {
            return tripsBy(user);
        }
		return NO_TRIPS;
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
