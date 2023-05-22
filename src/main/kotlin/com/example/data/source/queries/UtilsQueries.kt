package com.example.data.source.queries

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.Entity
import org.ktorm.schema.Column
import org.ktorm.schema.Table
import kotlin.math.ceil

fun <T : Entity<T>> Database.getCountOfTableItemsQuery(table: Table<T>): Int {
    return this.from(table).select(count())
        .map { it[count().aliased("items_numbers")] ?: 0 }.first()
}

fun Database.checkIfExistByName(columnName: Column<String>, name: String): Boolean {
    return this.from(columnName.table).select().where { columnName eq name }.map {}.isNotEmpty()
}

fun <T : Entity<T>> Database.getTotalPagesTableQuery(table: Table<T>, pageSize: Int): Int {
    val itemCount = this.from(table).select(count())
        .map { it[count().aliased("items_numbers")] ?: 0 }.first()
    return ceil(itemCount.toDouble() / pageSize.toDouble()).toInt()
}