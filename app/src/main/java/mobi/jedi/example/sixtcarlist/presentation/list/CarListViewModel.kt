package mobi.jedi.example.sixtcarlist.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.repository.ICarListRepository

class CarListViewModel(private val repository: ICarListRepository) : ViewModel() {

    private var liveData: LiveData<List<Car>>? = null

    fun getCars(): LiveData<List<Car>> {
        return liveData ?: repository.loadList().also { liveData = it }
    }
}