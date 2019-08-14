package mobi.jedi.example.sixtcarlist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.repository.network.IApi
import mobi.jedi.example.sixtcarlist.repository.network.response.mapper.ICarMapper

internal class CarRepository(
    private val api: IApi,
    private val carMapper: ICarMapper
) : ICarRepository {

    override fun loadCar(id: String): LiveData<Car> {
        return liveData {
            val source =
                api.getCars()
                    .map(carMapper::mapCar)
                    .first()

            emit(source)
        }
    }


}