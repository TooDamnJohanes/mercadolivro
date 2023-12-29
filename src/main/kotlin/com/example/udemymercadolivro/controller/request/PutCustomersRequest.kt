package com.example.udemymercadolivro.controller.request

data class PutCustomersRequest(
    val newCustomerFirstName: String,
    val newCustomerLastName: String,
    val newCustomerEmail: String
)
