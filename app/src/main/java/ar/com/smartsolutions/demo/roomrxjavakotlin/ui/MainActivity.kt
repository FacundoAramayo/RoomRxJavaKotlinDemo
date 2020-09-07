package ar.com.smartsolutions.demo.roomrxjavakotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ar.com.smartsolutions.demo.roomrxjavakotlin.R
import ar.com.smartsolutions.demo.roomrxjavakotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.setTitle(R.string.customer_app)
        binding.apply {
            btnAddCustomer.setOnClickListener {
                val intent = Intent(this@MainActivity, AddCustomerActivity::class.java)
                startActivity(intent)
            }
            btnOpenCustomerList.setOnClickListener {
                val intent = Intent(this@MainActivity, CustomerListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
