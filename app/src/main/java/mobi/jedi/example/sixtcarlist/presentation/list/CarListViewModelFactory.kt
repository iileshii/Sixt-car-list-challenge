package mobi.jedi.example.sixtcarlist.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mobi.jedi.example.sixtcarlist.repository.RepositoryFabric

class CarListViewModelFactory : ViewModelProvider.Factory {

    private val listRepository = RepositoryFabric.provideCarListRepository()
    private val carRepository = RepositoryFabric.provideCarRepository()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CarListViewModel::class.java) -> CarListViewModel(listRepository) as T
            modelClass.isAssignableFrom(SelectionViewModel::class.java) -> SelectionViewModel(carRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}