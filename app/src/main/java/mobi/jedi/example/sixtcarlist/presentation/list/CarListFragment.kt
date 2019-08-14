package mobi.jedi.example.sixtcarlist.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import mobi.jedi.example.sixtcarlist.R
import mobi.jedi.example.sixtcarlist.domain.Car

class CarListFragment : Fragment() {

    companion object {

        fun newInstance(): CarListFragment {
            return CarListFragment()
        }
    }

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), CarListViewModelFactory()).get(CarListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCars().observe(viewLifecycleOwner, Observer(::updateCarList))
    }

    private fun updateCarList(cars: List<Car>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}