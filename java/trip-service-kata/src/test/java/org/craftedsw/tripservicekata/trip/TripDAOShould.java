package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TripDAOShould {
    private static final Trip LONDON = new Trip();
    private static final Trip BARCELONA = new Trip();

    @Test
    public void return_trips_by_user() {
        User user = UserBuilder.aUser()
                        .addTrips(LONDON, BARCELONA)
                        .build();
        List<Trip> tripList = user.trips();
        assertThat(tripList.size(), is(2));
    }
}
