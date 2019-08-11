package mobi.jedi.example.sixtcarlist.repository.network.response.mapper

import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.repository.network.response.CarResponse

internal interface ICarMapper {
    fun mapCar(carResponse: CarResponse): Car
}