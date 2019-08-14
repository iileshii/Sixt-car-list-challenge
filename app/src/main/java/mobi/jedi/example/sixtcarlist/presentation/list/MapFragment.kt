package mobi.jedi.example.sixtcarlist.presentation.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.domain.Coordinate
import mobi.jedi.example.sixtcarlist.presentation.Injector

class MapFragment : SupportMapFragment() {

    companion object {

        fun newInstance(): MapFragment {
            return MapFragment()
        }

    }

    private lateinit var map: GoogleMap
    private val markers = mutableMapOf<String, Marker>()

    private val viewModelFactory by lazy { Injector.provideListViewModelFactory() }

    private val listViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(CarListViewModel::class.java)
    }
    private val selectionViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(SelectionViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(::handleMap)
    }

    private fun handleMap(googleMap: GoogleMap?) {
        googleMap ?: return

        map = googleMap

        listViewModel.getCars().observe(viewLifecycleOwner, Observer(::updateCarList))
        selectionViewModel.getSelectedCar().observe(viewLifecycleOwner, Observer(::setCarSelected))

        map.setOnMarkerClickListener { marker -> onMarkerSelect(marker) }
    }

    private fun setCarSelected(car: Car) {
        centerCamera(car.coordinate)
        showInfo(car.id)
    }

    private fun centerCamera(coordinate: Coordinate) {
        val cameraUpdate = CameraUpdateFactory.newLatLng(coordinate.toLatLng())
        map.moveCamera(cameraUpdate)
    }

    private fun showInfo(carId: String) {
        markers[carId]?.showInfoWindow()
    }

    private fun updateCarList(cars: List<Car>) {
        map.clear()
        markers.clear()
        val boundsBuilder = LatLngBounds.Builder()
        cars.forEach {
            val latLng = it.coordinate.toLatLng()

            val markerOptions =
                MarkerOptions()
                    .position(latLng)
                    .title(it.name)

            val marker = map.addMarker(markerOptions).apply { tag = it.id }
            markers[it.id] = marker

            boundsBuilder.include(latLng)
        }
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 0)
        map.moveCamera(cameraUpdate)
    }

    private fun onMarkerSelect(marker: Marker): Boolean {
        selectionViewModel.selectCar(marker.tag as String)
        return true
    }
}

private fun Coordinate.toLatLng() = LatLng(lat, lon)
