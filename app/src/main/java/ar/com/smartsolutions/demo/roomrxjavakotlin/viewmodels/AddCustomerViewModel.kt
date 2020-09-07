package ar.com.smartsolutions.demo.roomrxjavakotlin.viewmodels

import androidx.lifecycle.MutableLiveData
import ar.com.smartsolutions.demo.roomrxjavakotlin.models.Customer
import ar.com.smartsolutions.demo.roomrxjavakotlin.utils.applySchedulers

class AddCustomerViewModel: ApplicationViewModel() {

    var customerSaved: MutableLiveData<Boolean> = MutableLiveData()

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
}