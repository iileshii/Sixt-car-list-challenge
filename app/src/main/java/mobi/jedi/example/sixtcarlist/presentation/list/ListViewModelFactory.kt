package mobi.jedi.example.sixtcarlist.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mobi.jedi.example.sixtcarlist.repository.ICarListRepository
import mobi.jedi.example.sixtcarlist.repository.ICarRepository

class ListViewModelFactory(
    private val listRepository: ICarListRepository,
    private val carRepository: ICarRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CarListViewModel::class.java) -> CarListViewModel(listRepository) as T
            modelClass.isAssignableFrom(SelectionViewModel::class.java) -> SelectionViewModel(carRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}