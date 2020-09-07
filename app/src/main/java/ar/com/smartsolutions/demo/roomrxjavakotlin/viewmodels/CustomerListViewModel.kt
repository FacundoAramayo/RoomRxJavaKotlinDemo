package ar.com.smartsolutions.demo.roomrxjavakotlin.viewmodels

import androidx.lifecycle.MutableLiveData
import ar.com.smartsolutions.demo.roomrxjavakotlin.models.Customer
import ar.com.smartsolutions.demo.roomrxjavakotlin.utils.applySchedulers

class CustomerListViewModel: ApplicationViewModel() {

    var customerList = MutableLiveData<List<Customer>>()

    fun getAllCustomers() {
        databaseInstance?.customerDao()?.getAllCustomers()
            ?.applySchedulers()
            ?.subscribe ({
                customerList.postValue(it)
            },{
                it.printStackTrace()
            })?.let {
                compositeDisposable.add(it)
            }
    }

    fun deleteCustomer(customer: Customer) {
        databaseInstance?.customerDao()?.deleteCustomer(customer)
            ?.applySchedulers()
            ?.subscribe({
                getAllCustomers()
            }, {
                it.printStackTrace()
            })?.let {
                compositeDisposable.add(it)
            }
    }

}