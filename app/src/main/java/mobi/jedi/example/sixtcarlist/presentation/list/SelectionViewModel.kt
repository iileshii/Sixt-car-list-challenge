package mobi.jedi.example.sixtcarlist.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.repository.ICarRepository

class SelectionViewModel(private val repository: ICarRepository) : ViewModel() {

    private val selectedCarIdLiveData = MutableLiveData<String>()

    fun selectCar(carId: String) {
        selectedCarIdLiveData.value = carId
    }

    fun getSelectedCar(): LiveData<Car> {
        return Transformations.switchMap(selectedCarIdLiveData, repository::loadCar)
    }
}