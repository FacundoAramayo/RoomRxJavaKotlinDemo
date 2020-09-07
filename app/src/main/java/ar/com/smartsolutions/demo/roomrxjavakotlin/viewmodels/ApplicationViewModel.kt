package ar.com.smartsolutions.demo.roomrxjavakotlin.viewmodels

import androidx.lifecycle.ViewModel
import ar.com.smartsolutions.demo.roomrxjavakotlin.database.ApplicationDatabase
import io.reactivex.disposables.CompositeDisposable

open class ApplicationViewModel: ViewModel() {

    val compositeDisposable = CompositeDisposable()

    var databaseInstance: ApplicationDatabase? = null

    fun setInstanceOfDatabase(databaseInstance: ApplicationDatabase) {
        this.databaseInstance = databaseInstance
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}