package mobi.jedi.example.sixtcarlist.presentation.car

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.repository.ICarRepository

class CarViewModel(private val carId: String, private val repository: ICarRepository) : ViewModel() {

    private var liveData: LiveData<Car>? = null

    fun getCar(): LiveData<Car> {
        return liveData ?: repository.loadCar(carId).also { liveData = it }
    }
}