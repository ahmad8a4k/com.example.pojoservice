package com.example.domain.usecases.util


/*
make page size in range from 10 to 20
 */
fun Int.makePageSizeInRange(): Int {
    return this.coerceIn(0..100)
}

fun Int.makePageNumberDefaultIfItZero(): Int {
    return if (this == 0) 1 else this
}

fun Int.pageNumberToCheckIfPageExist(totalPages: Int): Boolean {
    return if (this > totalPages) false
    else totalPages >= this
}

fun Int.pageNumberToMakeItInRange(totalPages: Int): Int {
    return if (this > totalPages) totalPages else this
}