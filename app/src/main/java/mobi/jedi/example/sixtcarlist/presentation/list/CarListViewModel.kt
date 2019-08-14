package mobi.jedi.example.sixtcarlist.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.repository.ICarListRepository

class CarListViewModel(private val repository: ICarListRepository) : ViewModel() {

    fun getCars(): LiveData<List<Car>> {
        return repository.loadList()
    }
}