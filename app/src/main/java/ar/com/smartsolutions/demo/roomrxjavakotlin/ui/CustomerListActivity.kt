package ar.com.smartsolutions.demo.roomrxjavakotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.smartsolutions.demo.roomrxjavakotlin.R
import ar.com.smartsolutions.demo.roomrxjavakotlin.database.ApplicationDatabase
import ar.com.smartsolutions.demo.roomrxjavakotlin.databinding.ActivityCustomerListBinding
import ar.com.smartsolutions.demo.roomrxjavakotlin.models.Customer
import ar.com.smartsolutions.demo.roomrxjavakotlin.viewmodels.CustomerListViewModel
import ar.com.smartsolutions.demo.roomrxjavakotlin.views.CustomerItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class CustomerListActivity : AppCompatActivity(),
CustomerItemView.DeleteClickListener,
CustomerItemView.EditClickListener{

    private lateinit var binding: ActivityCustomerListBinding

    private var viewModel: CustomerListViewModel? = null

    private val customerListAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(CustomerListViewModel::class.java)
        val databaseInstance = ApplicationDatabase.getDatabaseInstance(this)
        viewModel?.setInstanceOfDatabase(databaseInstance)

        setupView()
        observeViewModel()

        viewModel?.getAllCustomers()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.see_customer_list)
        binding.recyclerCustomerList.apply {
            layoutManager = LinearLayoutManager(this@CustomerListActivity, RecyclerView.VERTICAL, false)
            adapter = customerListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel?.customerList?.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                handleCustomersData(it)
            } else {
                showEmptyList()
            }
        })
    }

    private fun handleCustomersData(customerList: List<Customer>) {
        binding.apply {
            cardContainer.visibility = View.VISIBLE
            tvNoCustomers.visibility = View.GONE
        }
        customerListAdapter.update(
            customerList.map { CustomerItemView(it, this, this) }
        )
    }

    private fun showEmptyList() {
        binding.apply {
            cardContainer.visibility = View.GONE
            tvNoCustomers.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDeleteClick(customer: Customer) {
        viewModel?.deleteCustomer(customer)
    }

    override fun onEditClick(customer: Customer) {
        //TODO("not implemented")
    }
}
