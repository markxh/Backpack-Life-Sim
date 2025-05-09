
import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import za.co.markxh.backpacklifesim.domain.model.Decision
import za.co.markxh.backpacklifesim.state.BackpackState
import za.co.markxh.backpacklifesim.ui.BackpackViewModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BackpackViewModelTest {

    private lateinit var viewModel: BackpackViewModel
    val repository = FakeBackpackRepository()
    private lateinit var fakeLoadBackpack: FakeLoadBackpackUseCase
    private lateinit var fakeSubmitChoices: FakeSubmitChoicesUseCase
    private lateinit var fakeLoadLifePath: FakeLoadLifePathUseCase
    private lateinit var testClock: TestClock

    @BeforeTest
    fun setup() {
        fakeLoadBackpack = FakeLoadBackpackUseCase(repository)
        fakeSubmitChoices = FakeSubmitChoicesUseCase(repository)
        fakeLoadLifePath = FakeLoadLifePathUseCase(repository)
        testClock = TestClock()
        viewModel = BackpackViewModel(
            loadBackpackUseCase = fakeLoadBackpack,
            submitChoicesUseCase = fakeSubmitChoices,
            loadLifePathUseCase = fakeLoadLifePath,
            clock = testClock
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `loadBackpack updates state to Loaded`() = runTest {
        viewModel.backpackState.test {
            val state = awaitItem()
            assertTrue(state is BackpackState.Loading)

            viewModel.loadBackpack()
            advanceUntilIdle()

            val updatedState = awaitItem()
            assertTrue(updatedState is BackpackState.Loaded)
        }
    }

    @Test
    fun `updateChoice adds and replaces choice`() {
        viewModel.updateChoice("id1", "Compass", Decision.KEEP)
        assertEquals(1, viewModel.selectedChoices.size)
        assertEquals(Decision.KEEP, viewModel.selectedChoices[0].decision)

        viewModel.updateChoice("id1", "Compass", Decision.USE)
        assertEquals(1, viewModel.selectedChoices.size)
        assertEquals(Decision.USE, viewModel.selectedChoices[0].decision)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `finalizeSubmission sets state to Submitted`() = runTest {
        viewModel.backpackState.test {
            val state = awaitItem()
            assertTrue(state is BackpackState.Loading)

            viewModel.updateChoice("id1", "Compass", Decision.KEEP)
            viewModel.finalizeSubmission()

            advanceUntilIdle()

            val updatedState = awaitItem()
            assertTrue(updatedState is BackpackState.Submitted)
        }
    }

    @Test
    fun `timeSinceSubmission returns correct time`() {
        testClock.currentTime = 1_000L
        viewModel.finalizeSubmission()

        testClock.currentTime = 2_000L
        assertEquals(1000L, viewModel.timeSinceSubmission())
    }
}