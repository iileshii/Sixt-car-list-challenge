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
import com.google.android.gms.maps.model.MarkerOptions
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.domain.Coordinate


class MapFragment : SupportMapFragment() {

    companion object {

        fun newInstance(): MapFragment {
            return MapFragment()
        }

    }

    private lateinit var map: GoogleMap
    private val markers = mutableListOf<MarkerOptions>()

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), CarListViewModelFactory()).get(CarListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(::handleMap)
    }

    private fun handleMap(googleMap: GoogleMap?) {
        googleMap ?: return

        map = googleMap

        viewModel.getCars().observe(viewLifecycleOwner, Observer(::updateCarList))
    }

    private fun updateCarList(cars: List<Car>) {
        map.clear()
        markers.clear()
        val boundsBuilder = LatLngBounds.Builder()
        cars.forEach {
            val latLng = it.coordinate.toLatLng()

            val marker =
                MarkerOptions()
                    .position(latLng)
                    .title(it.name)

            map.addMarker(marker)
            markers.add(marker)
            boundsBuilder.include(latLng)
        }
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 0)
        map.moveCamera(cameraUpdate)
    }
}

private fun Coordinate.toLatLng() = LatLng(lat, lon)
