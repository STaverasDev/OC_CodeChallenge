package nyc.c4q.okcupidchallenge.ui

class KUserDataForView() {

    companion object {
        val AGE_LOCATION_DIVIDER: String = " \u00b7 "
        val MATCH_PERCENTAGE: String = "% Match"
        val CITY_STATE_DIVIDER: String = ", "

        fun buildAgeLocationStringForView(age: Int, locationAsCityState: String): String {
            val ageLocation = StringBuilder()
            ageLocation.append(age)
            ageLocation.append(AGE_LOCATION_DIVIDER)
            ageLocation.append(locationAsCityState)
            return ageLocation.toString()
        }

        fun buildMatchStringForView(matchPercentage: Int): String {
            val matchAsString = StringBuilder()
            matchAsString.append(matchPercentage)
            matchAsString.append(MATCH_PERCENTAGE)
            return matchAsString.toString()
        }

        fun buildCityStateStringForView(cityCode: String, stateCode: String): String {
            val locationAsCityState = StringBuilder()
            locationAsCityState.append(cityCode)
            locationAsCityState.append(CITY_STATE_DIVIDER)
            locationAsCityState.append(stateCode)
            return locationAsCityState.toString()
        }

        fun convertMatchToPercentage(responseMatchValue: Int): Int {
            val responseDouble = responseMatchValue.toDouble()
            val percentageValue = Math.round((responseDouble / 100.0).toDouble())
            val finalValue = percentageValue.toInt()
            return finalValue

        }
    }

}