package nyc.c4q.okcupidchallenge.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class UserDataForViewTest {



    @Test
    public void convertMatchToPercentageReturnsCorrectRoundedInt() {
        int actual = UserDataForView.convertMatchToPercentage(8766);
        int expected = 88;
        assertEquals(expected,actual);
    }

    @Test
    public void getLocationAsCityStateReturnsCorrectString() {
        String actual = UserDataForView.buildCityStateStringForView("Manhattan","NY");
        String expected = "Manhattan, NY";
        assertEquals(expected,actual);
    }

    @Test
    public void buildMatchStringForViewReturnsCorrectString() {
        String actual = UserDataForView.buildMatchStringForView(87);
        String expected = "87% Match";
        assertEquals(expected,actual);
    }

    @Test
    public void getAgeLocationForView() {
        String actual = UserDataForView.buildAgeLocationStringForView(37,"Brooklyn, NY");
        String expected = "37 \u00b7 Brooklyn, NY";
        assertEquals(expected,actual);
    }
}