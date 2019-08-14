package mobi.jedi.example.sixtcarlist.presentation.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import mobi.jedi.example.sixtcarlist.domain.Car


class MapFragment : SupportMapFragment() {

    companion object {

        fun newInstance(): MapFragment {
            return MapFragment()
        }

    }

    private lateinit var map: GoogleMap

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}