package mobi.jedi.example.sixtcarlist.repository

import mobi.jedi.example.sixtcarlist.repository.network.ApiProvider
import mobi.jedi.example.sixtcarlist.repository.network.IApi
import mobi.jedi.example.sixtcarlist.repository.network.response.mapper.CarMapper
import mobi.jedi.example.sixtcarlist.repository.network.response.mapper.ICarMapper

object RepositoryFabric {

    private val carListRepository by lazy { CarListRepository(getApi(), getCarMapper()) }

    fun getCarListRepository(): ICarListRepository {
        return carListRepository
    }

    private fun getApi(): IApi {
        return ApiProvider.api
    }

    private fun getCarMapper(): ICarMapper {
        return CarMapper()
    }
}