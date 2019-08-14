package mobi.jedi.example.sixtcarlist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.repository.cache.ICache
import mobi.jedi.example.sixtcarlist.repository.network.IApi
import mobi.jedi.example.sixtcarlist.repository.network.response.mapper.ICarMapper

internal class CarListRepository(
    private val api: IApi,
    private val carMapper: ICarMapper,
    private var carCache: ICache<Car>
) : ICarListRepository {

    override fun loadList(): LiveData<List<Car>> {
        return liveData {
            val source =
                api.getCars()
                    .map(carMapper::mapCar)
                    .apply { forEach { car -> carCache.put(car.id, car) } }

            emit(source)
        }
    }
}