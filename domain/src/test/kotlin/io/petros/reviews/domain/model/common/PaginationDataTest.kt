package io.petros.reviews.domain.model.common

import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.NEXT_PAGE
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReview
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class PaginationDataTest {

    private val firstPageItems = listOf(provideReview(id = 1), provideReview(id = 2), provideReview(id = 3))
    private val secondPageItems = listOf(provideReview(id = 4), provideReview(id = 5), provideReview(id = 6))

    private lateinit var testedClass: PaginationData<Review>

    @Before
    fun setUp() {
        testedClass = PaginationData()
    }

    @Test
    fun `Given no pages, when checking if it is empty, then the return value is true`() {
        assertThat(testedClass.isEmpty()).isTrue()
    }

    @Test
    fun `Given some pages, when checking if it is empty, then the return value is false`() {
        testedClass.addPage(ReviewsResultPage(NEXT_PAGE, firstPageItems))

        assertThat(testedClass.isEmpty()).isFalse()
    }

    @Test
    fun `Given single page, when checking if it is the first page, then the return value is true`() {
        testedClass.addPage(ReviewsResultPage(NEXT_PAGE, firstPageItems))

        assertThat(testedClass.isFirstPage()).isTrue()
    }

    @Test
    fun `Given multiple pages, when checking it is the first page, then the return value is false`() {
        testedClass.addPage(ReviewsResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(ReviewsResultPage(NEXT_PAGE, secondPageItems))

        assertThat(testedClass.isFirstPage()).isFalse()
    }

    @Test
    fun `When adding a first page, then the pagination state is the expected one`() {
        assertThat(testedClass.allPageItems).isEmpty()
        assertThat(testedClass.latestPage).isNull()
        assertThat(testedClass.nextPage).isNull()
        val firstPage = ReviewsResultPage(NEXT_PAGE, firstPageItems)

        testedClass.addPage(firstPage)

        assertThat(testedClass.allPageItems).isEqualTo(firstPageItems)
        assertThat(testedClass.latestPage).isEqualTo(firstPage)
        assertThat(testedClass.nextPage).isEqualTo(NEXT_PAGE)
    }

    @Test
    fun `When adding a second page, then the pagination state is the expected one`() {
        assertThat(testedClass.allPageItems).isEmpty()
        assertThat(testedClass.latestPage).isNull()
        assertThat(testedClass.nextPage).isNull()
        val firstPage = ReviewsResultPage(NEXT_PAGE, firstPageItems)
        testedClass.addPage(firstPage)
        val secondPage = ReviewsResultPage(NEXT_PAGE + 1, secondPageItems)

        testedClass.addPage(secondPage)

        assertThat(testedClass.allPageItems).isEqualTo(firstPageItems + secondPageItems)
        assertThat(testedClass.latestPage).isEqualTo(secondPage)
        assertThat(testedClass.nextPage).isEqualTo(NEXT_PAGE + 1)
    }

    @Test
    fun `When pagination data is cleared, then all fields are reset`() {
        testedClass.addPage(ReviewsResultPage(NEXT_PAGE, firstPageItems))
        assertThat(testedClass.isEmpty()).isFalse()
        assertThat(testedClass.latestPage).isNotNull
        assertThat(testedClass.nextPage).isNotNull()

        testedClass.clear()

        assertThat(testedClass.isEmpty()).isTrue()
        assertThat(testedClass.latestPage).isNull()
        assertThat(testedClass.nextPage).isNull()
    }

}
