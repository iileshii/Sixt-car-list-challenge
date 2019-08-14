package mobi.jedi.example.sixtcarlist.presentation.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mobi.jedi.example.sixtcarlist.repository.RepositoryFabric

class CarViewModelFactory(private val carId: String) : ViewModelProvider.Factory {

    private val repository = RepositoryFabric.provideCarRepository()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarViewModel::class.java)) {
            return CarViewModel(carId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}