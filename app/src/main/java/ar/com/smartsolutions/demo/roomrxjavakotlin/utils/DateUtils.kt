package ar.com.smartsolutions.demo.roomrxjavakotlin.utils

import java.util.Date

fun Long?.toDate(): Date? {
    return this?.let {
        Date(it)
    }
}

fun Date?.toLong(): Long? {
    return this?.time
}