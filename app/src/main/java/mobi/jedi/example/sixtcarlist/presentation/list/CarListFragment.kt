package mobi.jedi.example.sixtcarlist.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import mobi.jedi.example.sixtcarlist.R
import mobi.jedi.example.sixtcarlist.domain.Car
import mobi.jedi.example.sixtcarlist.presentation.Injector

class CarListFragment : Fragment() {

    companion object {

        fun newInstance(): CarListFragment {
            return CarListFragment()
        }
    }

    private val viewModelFactory by lazy { Injector.provideListViewModelFactory() }

    private val listViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(CarListViewModel::class.java)
    }
    private val selectionViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(SelectionViewModel::class.java)
    }

    private val adapter = CarListAdapter(::onItemClick)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.getCars().observe(viewLifecycleOwner, Observer(::updateCarList))

        initRecycler(view.context)
    }

    private fun updateCarList(cars: List<Car>) {
        adapter.updateData(cars)
    }

    private fun initRecycler(context: Context) {
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
            .apply {
                val drawable = ContextCompat.getDrawable(context, R.drawable.divider)
                drawable?.let { setDrawable(it) }
            }

        recycler.addItemDecoration(divider)

        recycler.adapter = adapter
    }

    private fun onItemClick(carId: String) {
        selectionViewModel.selectCar(carId)
    }
}