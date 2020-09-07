package ar.com.smartsolutions.demo.roomrxjavakotlin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.smartsolutions.demo.roomrxjavakotlin.database.ApplicationDatabase
import ar.com.smartsolutions.demo.roomrxjavakotlin.models.Customer
import ar.com.smartsolutions.demo.roomrxjavakotlin.utils.applySchedulers
import io.reactivex.disposables.CompositeDisposable

class AddCustomerViewModel: ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    private var databaseInstance: ApplicationDatabase? = null

    var customerSaved: MutableLiveData<Boolean> = MutableLiveData()

    fun setInstanceOfDatabase(databaseInstance: ApplicationDatabase) {
        this.databaseInstance = databaseInstance
    }

    fun saveCustomer(customer: Customer) {
        databaseInstance?.customerDao()?.insertCustomer(customer)
            ?.applySchedulers()
            ?.doOnError {
                it.printStackTrace()
                customerSaved.postValue(false)
            }
            ?.subscribe({
                customerSaved.postValue(true)
            },{
                it.printStackTrace()
            })?.let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}