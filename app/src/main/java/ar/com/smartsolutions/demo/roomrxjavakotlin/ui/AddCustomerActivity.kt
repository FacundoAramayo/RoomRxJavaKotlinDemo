package ar.com.smartsolutions.demo.roomrxjavakotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ar.com.smartsolutions.demo.roomrxjavakotlin.R
import ar.com.smartsolutions.demo.roomrxjavakotlin.database.ApplicationDatabase
import ar.com.smartsolutions.demo.roomrxjavakotlin.databinding.ActivityAddCustomerBinding
import ar.com.smartsolutions.demo.roomrxjavakotlin.models.Customer
import ar.com.smartsolutions.demo.roomrxjavakotlin.viewmodels.AddCustomerViewModel

class AddCustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCustomerBinding

    private var viewModel: AddCustomerViewModel? = null

    lateinit var lastName: String
    lateinit var firstName: String
    lateinit var address: String
    lateinit var email: String
    lateinit var phone: String
    lateinit var comment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(AddCustomerViewModel::class.java)
        val databaseInstance = ApplicationDatabase.getDatabaseInstance(this)
        viewModel?.setInstanceOfDatabase(databaseInstance)

        setupView()
        observeViewModel()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.add_customer)
        binding.run {
            btnSave.setOnClickListener {
                saveCustomer()
            }
        }
    }

    private fun observeViewModel() {
        viewModel?.customerSaved?.observe(this, Observer {
            if (it) {
                closeActivity()
            } else {
                Toast.makeText(this, "Hubo un error, intente nuevamente", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun saveCustomer() {
        if (validateData()) {
            val customer = Customer(
                lastName = lastName,
                name = firstName,
                address = address,
                email = email,
                phone = phone,
                comment = comment
            )
            viewModel?.saveCustomer(customer)
        } else {
            Toast.makeText(this, "Complete correctamente los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateData(): Boolean {
        binding.run {
            lastName = txtLastName.text.trim().toString()
            firstName = txtFirstName.text.trim().toString()
            address = txtAddress.text.trim().toString()
            email = txtEmail.text.trim().toString()
            phone = txtPhone.text.trim().toString()
            comment = txtComment.text.trim().toString()
            return !(lastName.isEmpty()
                    || firstName.isEmpty()
                    || address.isEmpty()
                    || email.isEmpty()
                    || phone.isEmpty()
                    )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun closeActivity() {
        Toast.makeText(this, "$lastName $firstName guardado correctamente", Toast.LENGTH_LONG).show()
        finish()
    }
}
