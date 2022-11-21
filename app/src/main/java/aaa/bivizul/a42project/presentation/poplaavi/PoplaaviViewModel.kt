package aaa.bivizul.a42project.presentation.poplaavi

import aaa.bivizul.a42project.domain.model.Plaavi
import aaa.bivizul.a42project.domain.model.Plaavig
import aaa.bivizul.a42project.domain.repository.PlaaviRepository
import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PoplaaviViewModel @Inject constructor(
    private val plaavi: Plaavi,
    private val plaaviRepository: PlaaviRepository,
) : ViewModel() {

    private val _plaavig = MutableLiveData<Plaavig>()
    val plaavig: LiveData<Plaavig> = _plaavig

    init {
        getPlaavig()
    }

    fun getPlaavig() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = plaaviRepository.getPlaavig(plaavi)
            if (response.isSuccessful) {
                response.body()?.let {
                    _plaavig.postValue(it)
                }
            } else {
                _plaavig.postValue(Plaavig(ERROR_MESSAGE))
            }
        }
    }

    companion object {
        const val ERROR_MESSAGE = "errorMessage"
    }
}

class PoplaaviViewModelFactory @AssistedInject constructor(
    @Assisted("plaavi") private val plaavi: Plaavi,
    private val plaaviRepository: PlaaviRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PoplaaviViewModel(plaavi, plaaviRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("plaavi") plaavi: Plaavi): PoplaaviViewModelFactory
    }

}