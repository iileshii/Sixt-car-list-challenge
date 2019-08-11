package mobi.jedi.example.sixtcarlist.repository.network.response

import kotlinx.serialization.Serializable

@Serializable
internal data class CarResponse(
    val id: String,
    val modelIdentifier: String,
    val modelName: String,
    val name: String,
    val make: String,
    val group: String,
    val color: String,
    val fuelType: String,
    val fuelLevel: Float,
    val transmission: String,
    val licensePlate: String,
    val latitude: Double,
    val longitude: Double,
    val innerCleanliness: String,
    val carImageUrl: String
)

internal const val CLEANLINESS_REGULAR = "REGULAR"
internal const val CLEANLINESS_CLEAN = "CLEAN"
internal const val CLEANLINESS_VERY_CLEAN = "VERY_CLEAN"

internal const val FUEL_PETROLEUM = "P"
internal const val FUEL_DIESEL = "D"

internal const val TRANSMISSION_MANUAL = "M"
internal const val TRANSMISSION_AUTOMATIC = "A"


