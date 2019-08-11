package mobi.jedi.example.sixtcarlist.repository.network.response.mapper

import mobi.jedi.example.sixtcarlist.domain.*
import mobi.jedi.example.sixtcarlist.repository.network.response.*

internal class CarMapper : ICarMapper {

    override fun mapCar(carResponse: CarResponse): Car {
        return Car(
            carResponse.id,
            Coordinate(carResponse.latitude, carResponse.longitude),
            carResponse.modelIdentifier,
            carResponse.modelName,
            carResponse.name,
            carResponse.make,
            carResponse.group,
            carResponse.color,
            mapFuelType(carResponse.fuelType),
            carResponse.fuelLevel,
            mapTransmission(carResponse.transmission),
            carResponse.licensePlate,
            mapCleanliness(carResponse.innerCleanliness),
            carResponse.carImageUrl
        )
    }

    private fun mapCleanliness(cleanliness: String): Cleanliness {
        return when (cleanliness) {
            CLEANLINESS_REGULAR -> Cleanliness.REGULAR
            CLEANLINESS_CLEAN -> Cleanliness.CLEAN
            CLEANLINESS_VERY_CLEAN -> Cleanliness.VERY_CLEAN
            else -> Cleanliness.UNKNOWN
        }
    }

    private fun mapTransmission(transmission: String): Transmission {
        return when (transmission) {
            TRANSMISSION_MANUAL -> Transmission.MANUAL
            TRANSMISSION_AUTOMATIC -> Transmission.AUTOMATIC
            else -> Transmission.UNKNOWN
        }
    }

    private fun mapFuelType(fuel: String): Fuel {
        return when (fuel) {
            FUEL_PETROLEUM -> Fuel.PETROLEUM
            FUEL_DIESEL -> Fuel.DIESEL
            else -> Fuel.UNKNOWN
        }
    }

}