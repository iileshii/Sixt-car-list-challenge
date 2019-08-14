package mobi.jedi.example.sixtcarlist.repository

import androidx.lifecycle.LiveData
import mobi.jedi.example.sixtcarlist.domain.Car

interface ICarRepository {

    fun loadCar(id: String): LiveData<Car>
}
