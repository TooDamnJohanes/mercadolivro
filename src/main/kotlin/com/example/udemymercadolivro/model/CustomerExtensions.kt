package com.example.udemymercadolivro.model

import com.example.udemymercadolivro.controller.request.PostCustomerRequest
import com.example.udemymercadolivro.controller.request.PutCustomersRequest

fun PutCustomersRequest.toDomain(customerId: Int): Customer {
    return Customer(
        id = customerId,
        firstName = this.newCustomerFirstName,
        lastName = this.newCustomerLastName,
        email = this.newCustomerEmail
    )
}

fun PostCustomerRequest.toDomain(): Customer {
    return Customer(
        firstName = this.customerFirstName,
        lastName = this.customerLastName,
        email = this.customerEmail
    )
}