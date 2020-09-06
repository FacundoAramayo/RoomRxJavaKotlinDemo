package ar.com.smartsolutions.demo.roomrxjavakotlin.dao

import androidx.room.*
import ar.com.smartsolutions.demo.roomrxjavakotlin.database.ON_CONFLICT_STRATEGY
import ar.com.smartsolutions.demo.roomrxjavakotlin.database.TABLE_NAME_CUSTOMER
import ar.com.smartsolutions.demo.roomrxjavakotlin.models.Customer
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CustomerDao {

    @Insert(onConflict = ON_CONFLICT_STRATEGY)
    fun insertCustomer(data: Customer): Completable

    @Query("SELECT * FROM $TABLE_NAME_CUSTOMER")
    fun getAllCustomers(): Single<List<Customer>>

    @Update
    fun updateCustomer(data: Customer)

    @Delete
    fun deleteCustomer(customer: Customer): Completable

}