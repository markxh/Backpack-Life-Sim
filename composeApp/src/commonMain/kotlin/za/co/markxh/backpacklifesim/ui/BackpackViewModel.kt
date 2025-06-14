package za.co.markxh.backpacklifesim.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.Decision
import za.co.markxh.backpacklifesim.state.BackpackState
import za.co.markxh.backpacklifesim.state.LifePathState
import za.co.markxh.backpacklifesim.ui.theme.AppStrings
import za.co.markxh.backpacklifesim.util.Clock

class BackpackViewModel(
    private val loadBackpackUseCase: LoadBackpackUseCase,
    private val submitChoicesUseCase: SubmitChoicesUseCase,
    private val loadLifePathUseCase: LoadLifePathUseCase,
    private val clock: Clock
) : ViewModel() {

    private val _backpackState = MutableStateFlow<BackpackState>(BackpackState.Loading)
    val backpackState: StateFlow<BackpackState> = _backpackState

    private val _lifePathState = MutableStateFlow<LifePathState>(LifePathState.Loading)
    val lifePathState: StateFlow<LifePathState> = _lifePathState

    private val _selectedChoices = mutableStateListOf<Choice>()
    val selectedChoices: List<Choice> get() = _selectedChoices

    private var hasSubmitted = false
    private var submitStartTime: Long? = null

    init {
        loadBackpack()
    }

    fun loadBackpack() {
        viewModelScope.launch {
            _backpackState.value = BackpackState.Loading
            try {
                val backpack = loadBackpackUseCase()
                _selectedChoices.clear()
                _backpackState.value = BackpackState.Loaded(backpack)
            } catch (e: Exception) {
                _backpackState.value = BackpackState.Error(e.message ?: AppStrings.errorOccurred)
            }
        }
    }

    fun updateChoice(itemId: String, name: String, decision: Decision) {
        _selectedChoices.removeAll { it.itemId == itemId }
        _selectedChoices.add(Choice(itemId = itemId, name = name, decision = decision))

        checkAndSubmitChoices()
    }

    private fun checkAndSubmitChoices() {
        val currentState = _backpackState.value
        if (
            currentState is BackpackState.Loaded &&
            !hasSubmitted &&
            _selectedChoices.size == currentState.backpack.items.size
        ) {
            hasSubmitted = true
            submitChoices()
        }
    }

    private fun submitChoices() {
        viewModelScope.launch {
            _backpackState.value = BackpackState.Loading
            val result = submitChoicesUseCase(_selectedChoices)

            if (result.isSuccess) {
                _backpackState.value = BackpackState.Submitted
                loadLifePath()
            } else {
                _backpackState.value = BackpackState.Error(AppStrings.errorOccurred)
            }
        }
    }

    private fun loadLifePath() {
        viewModelScope.launch {
            _lifePathState.value = LifePathState.Loading
            try {
                val lifePath = loadLifePathUseCase()
                _lifePathState.value = LifePathState.Loaded(lifePath)
            } catch (e: Exception) {
                _lifePathState.value = LifePathState.Error(e.message ?: AppStrings.errorOccurred)
            }
        }
    }

    private fun recordSubmissionStartTime() {
        submitStartTime = clock.currentTimeMillis()
    }

    fun timeSinceSubmission(): Long {
        val now = clock.currentTimeMillis()
        return now - (submitStartTime ?: now)
    }

    fun clearLifePathState() {
        _lifePathState.value = LifePathState.Idle
    }
}

