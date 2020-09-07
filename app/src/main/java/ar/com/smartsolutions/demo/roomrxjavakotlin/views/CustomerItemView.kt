package ar.com.smartsolutions.demo.roomrxjavakotlin.views

import android.view.View
import ar.com.smartsolutions.demo.roomrxjavakotlin.R
import ar.com.smartsolutions.demo.roomrxjavakotlin.databinding.ItemCustomerBinding
import ar.com.smartsolutions.demo.roomrxjavakotlin.models.Customer
import com.xwray.groupie.viewbinding.BindableItem

class CustomerItemView(
    private val customer: Customer,
    private val editListener: EditClickListener,
    private val deleteListener: DeleteClickListener
): BindableItem<ItemCustomerBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_customer
    }

    override fun bind(viewBinding: ItemCustomerBinding, position: Int) {
        viewBinding.apply {
            tvInitials.text = "${customer.lastName?.subSequence(0, 1)}${customer.name?.subSequence(0, 1)}"
            tvContactName.text = "${customer.lastName}, ${customer.name}"
            tvAddress.text = customer.address
            tvEmail.text = customer.email
            tvPhone.text = customer.phone
            if (!customer.comment.isNullOrBlank()) {
                tvComments.text = "Comentarios: ${customer.comment}"
            } else {
                tvComments.visibility = View.GONE
            }

            btnDelete.setOnClickListener {
                deleteListener.onDeleteClick(customer)
            }

            btnEdit.setOnClickListener {
                //TODO
            }
        }
    }

    override fun initializeViewBinding(view: View): ItemCustomerBinding {
        return ItemCustomerBinding.bind(view)
    }

    interface EditClickListener {
        fun onEditClick(customer: Customer)
    }

    interface DeleteClickListener {
        fun onDeleteClick(customer: Customer)
    }
}