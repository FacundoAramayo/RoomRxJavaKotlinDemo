package ar.com.smartsolutions.demo.roomrxjavakotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.com.smartsolutions.demo.roomrxjavakotlin.database.TABLE_NAME_CUSTOMER
import java.util.*

@Entity (tableName = Customer.TABLE_NAME)
class Customer(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var customerId: Int? = null,

    @ColumnInfo(name = LAST_NAME)
    var lastName: String? = null,

    @ColumnInfo(name = NAME)
    var name: String? = null,

    @ColumnInfo(name = BIRTHDAY)
    var birthday: Long? = null,

    @ColumnInfo(name = ADDRESS)
    var address: String? = null,

    @ColumnInfo(name = EMAIL)
    var email: String? = null,

    @ColumnInfo(name = PHONE)
    var phone: String? = null,

    @ColumnInfo(name = COMMENT)
    var comment: String? = null
)  {
    companion object {
        const val TABLE_NAME = TABLE_NAME_CUSTOMER
        const val ID = "id"
        const val LAST_NAME = "last_name"
        const val NAME = "name"
        const val BIRTHDAY = "birthday"
        const val ADDRESS = "address"
        const val EMAIL = "email"
        const val PHONE = "phone"
        const val COMMENT = "comment"
    }
}
