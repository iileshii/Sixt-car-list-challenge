package mobi.jedi.example.sixtcarlist.repository.network

import mobi.jedi.example.sixtcarlist.repository.network.response.CarResponse
import retrofit2.http.GET

internal interface IApi {

    @GET("codingtask/cars")
    suspend fun getCars(): List<CarResponse>
}