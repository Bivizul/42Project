package aaa.bivizul.a42project.presentation.plaavislist

import aaa.bivizul.a42project.domain.model.Plaavis
import aaa.bivizul.a42project.domain.repository.PlaavisRepository
import androidx.lifecycle.*
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaavisListViewModel @Inject constructor(
    private val plaavisRepository: PlaavisRepository,
) : ViewModel() {

    private val _plaavis = MutableLiveData<List<Plaavis>>()
    val plaavis: LiveData<List<Plaavis>> = _plaavis

    init {
        getPlaavis()
    }

    fun getPlaavis() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = plaavisRepository.getPlaavis()
            if (response.isSuccessful) {
                response.body()?.let {
                    _plaavis.postValue(it)
                }
            } else {
                _plaavis.postValue(emptyList())
            }
        }
    }

}

class PlaavisViewModelFactory @AssistedInject constructor(
    private val plaavisRepository: PlaavisRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaavisListViewModel(plaavisRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(): PlaavisViewModelFactory
    }

}