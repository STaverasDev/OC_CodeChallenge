package nyc.c4q.okcupidchallenge.model;

/**
 * Manipulates user data from the API response to be suitable for the view
 */

public class UserDataForView {

    private static final String AGE_LOCATION_DIVIDER = " \u00b7 ";
    private static final String MATCH_PERCENTAGE = "% Match";
    private static final String CITY_STATE_DIVIDER = ", ";

    public static String buildAgeLocationStringForView(int age, String locationAsCityState) {
        StringBuilder ageLocation = new StringBuilder();
        ageLocation.append(age);
        ageLocation.append(AGE_LOCATION_DIVIDER);
        ageLocation.append(locationAsCityState);
        return ageLocation.toString();
    }

    public static String buildMatchStringForView(int matchPercentage) {
        StringBuilder matchAsString = new StringBuilder();
        matchAsString.append(matchPercentage);
        matchAsString.append(MATCH_PERCENTAGE);
        return matchAsString.toString();
    }

    public static String buildCityStateStringForView(String cityCode, String stateCode) {
        StringBuilder locationAsCityState = new StringBuilder();
        locationAsCityState.append(cityCode);
        locationAsCityState.append(CITY_STATE_DIVIDER);
        locationAsCityState.append(stateCode);
        return locationAsCityState.toString();
    }


    public static int convertMatchToPercentage(int responseMatchValue) {
        double responseDouble = responseMatchValue;
        Double percentageValue = Double.valueOf(Math.round((responseDouble / 100.0)));
        int finalValue = percentageValue.intValue();
        return finalValue;
    }
}
