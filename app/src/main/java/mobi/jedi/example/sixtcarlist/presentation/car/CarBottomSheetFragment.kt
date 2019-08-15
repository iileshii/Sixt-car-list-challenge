package mobi.jedi.example.sixtcarlist.presentation.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_car_info.*
import mobi.jedi.example.sixtcarlist.R
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.domain.Cleanliness
import mobi.jedi.example.sixtcarlist.domain.Transmission
import mobi.jedi.example.sixtcarlist.presentation.Injector

class CarBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        private const val TAG = "CarBottomSheetFragment"
        private const val KEY_CAR_ID = "carId"

        fun show(fragmentManager: FragmentManager, carId: String) {
            val fragment = fragmentManager.findFragmentByTag(TAG)

            if (fragment?.arguments?.getString(KEY_CAR_ID) == carId) {
                //leave same fragment
            } else {
                (fragment as? DialogFragment)?.dismissAllowingStateLoss()

                CarBottomSheetFragment()
                    .apply { arguments = Bundle(1).apply { putString(KEY_CAR_ID, carId) } }
                    .show(fragmentManager, TAG)
            }
        }
    }

    private val carId by lazy { arguments?.getString(KEY_CAR_ID) }

    private val viewModelFactory by lazy { carId?.let { Injector.provideCarViewModelFactory(it) } }

    private val carViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(CarViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_car_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel.getCar().observe(viewLifecycleOwner, Observer(::showCarInfo))
    }

    private fun showCarInfo(car: Car) {
        make.text = car.make
        car_name.text = car.name
        cleanliness_indicator.rating = calculateCleanlinessRating(car.innerCleanliness, cleanliness_indicator.numStars)
        fuel_level_indicator.rating = calculateFuelLevelRating(car, fuel_level_indicator.numStars)
        model_name.text = car.modelName
        transmission_indicator.text = getTransmissionStringResId(car.transmission)
        color.text = car.color
        Picasso.get()
            .load(car.carImageUrl)
            .error(R.drawable.ic_directions_car_black_60dp)
            .into(car_image)
    }

    private fun calculateFuelLevelRating(car: Car, stars: Int) = car.fuelLevel * stars

    private fun getTransmissionStringResId(transmission: Transmission): CharSequence {
        when (transmission) {
            Transmission.UNKNOWN -> R.string.transmission_unknown
            Transmission.MANUAL -> R.string.transmission_manual
            Transmission.AUTOMATIC -> R.string.transmission_automatic
        }.also {
            return getString(it)
        }
    }

    private fun calculateCleanlinessRating(cleanliness: Cleanliness, stars: Int): Float {
        val amountOfProperVariants = Cleanliness.values().size - 1 //for unknown place
        return when (cleanliness) {
            Cleanliness.UNKNOWN -> stars
            Cleanliness.REGULAR -> stars / amountOfProperVariants
            Cleanliness.CLEAN -> stars * 2 / amountOfProperVariants
            Cleanliness.VERY_CLEAN -> stars
        }.toFloat()
    }
}