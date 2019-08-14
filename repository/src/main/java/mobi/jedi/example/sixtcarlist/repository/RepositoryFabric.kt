package mobi.jedi.example.sixtcarlist.repository

import mobi.jedi.example.sixtcarlist.repository.network.ApiProvider
import mobi.jedi.example.sixtcarlist.repository.network.IApi
import mobi.jedi.example.sixtcarlist.repository.network.response.mapper.CarMapper
import mobi.jedi.example.sixtcarlist.repository.network.response.mapper.ICarMapper

object RepositoryFabric {

    private val carListRepository by lazy { CarListRepository(provideApi(), provideCarMapper()) }

    private val carRepository by lazy { CarRepository(provideApi(), provideCarMapper()) }

    fun provideCarListRepository(): ICarListRepository {
        return carListRepository
    }

    fun provideCarRepository(): ICarRepository {
        return carRepository
    }

    private fun provideApi(): IApi {
        return ApiProvider.api
    }

    private fun provideCarMapper(): ICarMapper {
        return CarMapper()
    }
}