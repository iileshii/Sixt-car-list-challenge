package mobi.jedi.example.sixtcarlist.presentation

import androidx.lifecycle.ViewModelProvider
import mobi.jedi.example.sixtcarlist.presentation.car.CarViewModelFactory
import mobi.jedi.example.sixtcarlist.presentation.list.ListViewModelFactory
import mobi.jedi.example.sixtcarlist.repository.RepositoryFactory

object Injector {

    private val listViewModelFactory by lazy {
        ListViewModelFactory(
            RepositoryFactory.provideCarListRepository(),
            RepositoryFactory.provideCarRepository()
        )
    }

    fun provideListViewModelFactory(): ViewModelProvider.Factory {
        return listViewModelFactory
    }

    fun provideCarViewModelFactory(carId: String): ViewModelProvider.Factory {
        return CarViewModelFactory(carId)
    }
}