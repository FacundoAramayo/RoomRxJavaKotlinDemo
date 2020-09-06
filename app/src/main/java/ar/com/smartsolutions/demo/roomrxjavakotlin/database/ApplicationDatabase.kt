package ar.com.smartsolutions.demo.roomrxjavakotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.com.smartsolutions.demo.roomrxjavakotlin.dao.CustomerDao
import ar.com.smartsolutions.demo.roomrxjavakotlin.models.Customer

@Database(
    entities = [
        Customer::class
    ],
    version = DATABASE_VERSION)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    companion object {

        @Volatile
        private var databaseInstance: ApplicationDatabase? = null

        fun getDatabaseInstance(context: Context): ApplicationDatabase {
            return databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabase(context).also {
                    databaseInstance = it
                }
            }
        }

        private fun buildDatabase(context: Context): ApplicationDatabase {
            return Room.databaseBuilder(context, ApplicationDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
