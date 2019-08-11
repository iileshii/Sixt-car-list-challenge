package mobi.jedi.example.sixtcarlist.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mobi.jedi.example.sixtcarlist.R

class CarListFragment : Fragment() {

    companion object {

        fun newInstance(): CarListFragment {
            return CarListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}