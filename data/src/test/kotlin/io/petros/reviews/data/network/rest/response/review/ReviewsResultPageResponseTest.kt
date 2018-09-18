package io.petros.reviews.data.network.rest.response.review

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ReviewsResultPageResponseTest {

    companion object {

        private const val TOTAL_REVIEW_COMMENTS = 33

    }

    private lateinit var testedClass: ReviewsResultPageResponse

    @Before
    fun setUp() {
        testedClass = ReviewsResultPageResponse(true, TOTAL_REVIEW_COMMENTS, null, null)
    }

    @Test
    fun `Given first page, when there are more pages, then the next page is set`() {
        val currentPage = 0

        assertThat(testedClass.nextPage(currentPage)).isEqualTo(1)
    }

    @Test
    fun `Given second page, when there are no more pages, then the next page is not set`() {
        val currentPage = 1

        assertThat(testedClass.nextPage(currentPage)).isEqualTo(null)
    }

}
