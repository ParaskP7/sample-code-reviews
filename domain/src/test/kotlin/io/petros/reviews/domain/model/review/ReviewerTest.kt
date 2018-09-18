package io.petros.reviews.domain.model.review

import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReviewer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ReviewerTest {

    @Test
    fun `Given a type, when reviewer is formatted, then it is the expected one`() {
        val testedClass = provideReviewer()

        assertThat(testedClass.formatted()).isEqualTo("REVIEWER_NAME - REVIEWER_COUNTRY (REVIEWER_TYPE)")
    }

    @Test
    fun `Given no type, when reviewer is formatted, then it is the expected one`() {
        val testedClass = provideReviewer(type = null)

        assertThat(testedClass.formatted()).isEqualTo("REVIEWER_NAME - REVIEWER_COUNTRY")
    }

}
