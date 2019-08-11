package mobi.jedi.example.sixtcarlist.domain

data class Car(
    val id: String,
    val coordinate: Coordinate,
    val modelIdentifier: String,
    val modelName: String,
    val name: String,
    val make: String,
    val group: String,
    val color: String,
    val fuelType: Fuel,
    val fuelLevel: Float,
    val transmission: Transmission,
    val licensePlate: String,
    val innerCleanliness: Cleanliness,
    val carImageUrl: String
)

data class Coordinate(val lat: Double, val lon: Double)

enum class Cleanliness {
    UNKNOWN, REGULAR, CLEAN, VERY_CLEAN
}

enum class Transmission {
    UNKNOWN, MANUAL, AUTOMATIC
}

enum class Fuel {
    UNKNOWN, PETROLEUM, DIESEL
}