package mobi.jedi.example.sixtcarlist.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mobi.jedi.example.sixtcarlist.repository.RepositoryFabric

class CarListViewModelFactory : ViewModelProvider.Factory {

    private val repository = RepositoryFabric.provideCarListRepository()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarListViewModel::class.java)) {
            return CarListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}