package ar.com.smartsolutions.demo.roomrxjavakotlin.database

import androidx.room.OnConflictStrategy

/*TABLES*/
const val TABLE_NAME_CUSTOMER = "customer"

/*DATABASE CONFIG*/
const val DATABASE_NAME = "CustomerLogs"
const val DATABASE_VERSION = 1
const val ON_CONFLICT_STRATEGY = OnConflictStrategy.REPLACE