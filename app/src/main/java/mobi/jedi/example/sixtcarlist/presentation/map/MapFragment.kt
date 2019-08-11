package mobi.jedi.example.sixtcarlist.presentation.map

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment


class MapFragment : SupportMapFragment() {

    private lateinit var map: GoogleMap

    companion object {

        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(::handleMap)
    }

    private fun handleMap(googleMap: GoogleMap?) {
        googleMap ?: return

        map = googleMap
    }
}