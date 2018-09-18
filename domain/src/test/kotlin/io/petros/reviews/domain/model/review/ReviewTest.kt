package io.petros.reviews.domain.model.review

import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReview
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ReviewTest {

    @Test
    fun `Given a five stars review, when review stars are asked, then the stars format is the expected one`() {
        val testedClass = provideReview()

        assertThat(testedClass.stars()).isEqualTo("★ ★ ★ ★ ★")
    }

    @Test
    fun `Given a four stars review, when review stars are asked, then the stars format is the expected one`() {
        val testedClass = provideReview(rating = 4.0)

        assertThat(testedClass.stars()).isEqualTo("★ ★ ★ ★")
    }

    @Test
    fun `Given a three stars review, when review stars are asked, then the stars format is the expected one`() {
        val testedClass = provideReview(rating = 3.0)

        assertThat(testedClass.stars()).isEqualTo("★ ★ ★")
    }

    @Test
    fun `Given a two stars review, when review stars are asked, then the stars format is the expected one`() {
        val testedClass = provideReview(rating = 2.0)

        assertThat(testedClass.stars()).isEqualTo("★ ★")
    }

    @Test
    fun `Given a one star review, when review stars are asked, then the stars format is the expected one`() {
        val testedClass = provideReview(rating = 1.0)

        assertThat(testedClass.stars()).isEqualTo("★")
    }

    @Test
    fun `Given an invalid review (ten), when review stars are asked, then a question mark is returned`() {
        val testedClass = provideReview(rating = 0.0)

        assertThat(testedClass.stars()).isEqualTo("?")
    }

    @Test
    fun `Given an invalid review (zero), when review stars are asked, then a question mark is returned`() {
        val testedClass = provideReview(rating = 10.0)

        assertThat(testedClass.stars()).isEqualTo("?")
    }

}

