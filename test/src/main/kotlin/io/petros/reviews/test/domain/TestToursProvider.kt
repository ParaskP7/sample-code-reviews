package io.petros.reviews.test.domain

import io.petros.reviews.domain.model.place.Tour

class TestToursProvider {

    companion object {

        private const val CITY = "berlin-l17"
        private const val PLACE = "tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776"

        fun provideTour(
            city: String = CITY,
            place: String = PLACE
        ): Tour {
            return Tour(
                city,
                place
            )
        }

    }

}
