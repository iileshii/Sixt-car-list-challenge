package mobi.jedi.example.sixtcarlist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.repository.cache.ICache
import mobi.jedi.example.sixtcarlist.repository.network.IApi
import mobi.jedi.example.sixtcarlist.repository.network.response.mapper.ICarMapper

internal class CarRepository(
    private val api: IApi,
    private val carMapper: ICarMapper,
    private var carCache: ICache<Car>
) : ICarRepository {

    override fun loadCar(id: String): LiveData<Car> {
        return liveData {
            val source =
                if (carCache.has(id)) {
                    carCache.get(id)
                } else {
                    api.getCars()
                        .map(carMapper::mapCar)
                        .apply { forEach { car -> carCache.put(car.id, car) } }
                        .find { it.id == id }
                }

            source?.let { emit(it) }
        }
    }


}