package io.petros.reviews.presentation.feature.reviews.navigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.reviews.presentation.PreconfiguredRobolectricTestRunner
import io.petros.reviews.presentation.feature.reviews.ReviewsActivity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor

@RunWith(PreconfiguredRobolectricTestRunner::class)
class ReviewsActivityLauncherRobolectricTest {

    private val intentCaptor = ArgumentCaptor.forClass(Intent::class.java)

    private lateinit var testedClass: ReviewsActivityLauncher
    private var appCompatActivityMock = mock<AppCompatActivity>()

    @Before
    fun setUp() {
        testedClass = ReviewsActivityLauncher(appCompatActivityMock)
    }

    @Test
    fun `When launch is called, then current activity starts target reviews activity`() {
        testedClass.launch()

        verify(appCompatActivityMock).startActivity(intentCaptor.capture())
        assertThat(intentCaptor.value?.component?.className).isEqualTo(ReviewsActivity::class.java.name)
    }

}
