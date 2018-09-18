package io.petros.reviews.domain.model.review

import io.petros.reviews.domain.emptyString
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideLanguage
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LanguageTest {

    @Test
    fun `Given a non-foreign language, when language is formatted, then it is the expected one`() {
        val testedClass = provideLanguage()

        assertThat(testedClass.formatted()).isEqualTo(emptyString())
    }

    @Test
    fun `Given a foreign language, when language is formatted, then it is the expected one`() {
        val testedClass = provideLanguage(isForeign = true)

        assertThat(testedClass.formatted()).isEqualTo("(LANGUAGE_CODE)")
    }

}
