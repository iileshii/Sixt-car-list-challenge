package mobi.jedi.example.sixtcarlist.presentation.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import mobi.jedi.example.sixtcarlist.R

class CarBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        private const val TAG = "CarBottomSheetFragment"
        private const val KEY_CAR_ID = "carId"

        fun show(fragmentManager: FragmentManager, carId: String) {
            val fragment = fragmentManager.findFragmentByTag(TAG)

            if (fragment?.arguments?.getString(KEY_CAR_ID) == carId) {
                //leave same fragment
            } else if (fragment?.arguments?.getString(KEY_CAR_ID) != carId) {
                (fragment as? DialogFragment)?.dismissAllowingStateLoss()

                CarBottomSheetFragment()
                    .apply { arguments = Bundle(1).apply { putString(KEY_CAR_ID, carId) } }
                    .show(fragmentManager, TAG)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_car_info, container, false)
    }
}