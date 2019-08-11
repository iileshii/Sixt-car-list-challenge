package mobi.jedi.example.sixtcarlist.repository

import androidx.lifecycle.LiveData
import mobi.jedi.example.sixtcarlist.domain.Car

interface ICarListRepository {

    fun loadList(): LiveData<List<Car>>
}
