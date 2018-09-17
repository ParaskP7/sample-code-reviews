package io.petros.reviews.presentation.feature.splash.navigator

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.reviews.presentation.feature.reviews.navigator.ReviewsLauncher
import io.petros.reviews.presentation.navi.manual.Launcher
import org.junit.Before
import org.junit.Test

class SplashActivityNavigatorTest {

    private lateinit var testedClass: SplashActivityNavigator
    private val reviewsLauncherMock = mock<ReviewsLauncher>()
    private val launcherMock = mock<Launcher>()

    @Before
    fun setUp() {
        testedClass = SplashActivityNavigator(reviewsLauncherMock)
        testedClass.launcher = launcherMock
    }

    @Test
    fun `When navigating from splash activity, then reviews activity launches`() {
        testedClass.navigate()

        verify(reviewsLauncherMock).launch()
    }

    @Test
    fun `When navigating from splash activity, then splash activity finishes`() {
        testedClass.navigate()

        verify(launcherMock).finish()
    }

}
