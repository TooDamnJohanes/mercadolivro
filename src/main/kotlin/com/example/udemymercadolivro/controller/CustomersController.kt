package com.example.udemymercadolivro.controller

import com.example.udemymercadolivro.controller.CustomerPaths.CUSTOMERS
import com.example.udemymercadolivro.controller.CustomerPaths.CUSTOMER_ID
import com.example.udemymercadolivro.controller.request.PostCustomerRequest
import com.example.udemymercadolivro.controller.request.PutCustomersRequest
import com.example.udemymercadolivro.model.Customer
import com.example.udemymercadolivro.model.toDomain
import com.example.udemymercadolivro.service.CustomersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(CUSTOMERS)
class CustomerController(
    private val customersService: CustomersService
) {

    @GetMapping
    fun getCustomers(
        @RequestParam filterName: String?
    ): List<Customer> {
        return customersService.getCustomers(filterName)
    }

    @GetMapping(CUSTOMER_ID)
    fun getCustomerById(
        @PathVariable customerId: Int
    ): String {
        val customerToDisplay = customersService.getCustomerById(customerId = customerId)
        return "Customer: $customerToDisplay"
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(
        @RequestBody newCustomerDto: PostCustomerRequest
    ): String {
        return if (customersService.createNewCustomer(newCustomerDto.toDomain())) {
            println("New customer: $newCustomerDto")
            "User created successfully"
        } else {
            println("Fail to save new customer")
            "Fail to save new customer"
        }
    }

    @PutMapping(CUSTOMER_ID)
    fun updateCustomer(
        @PathVariable customerId: Int,
        @RequestBody customerUpdated: PutCustomersRequest
    ): String {
        return customersService.updateCustomer(customerId, customerUpdated.toDomain(customerId))
    }

    @DeleteMapping(CUSTOMER_ID)
    fun deleteCustomerById(
        @PathVariable customerId: Int
    ): String {
        return if (customersService.deleteCustomerById(customerId)) {
            "User deleted!"
        } else {
            "Error while delete it"
        }
    }
}

object CustomerPaths {
    const val CUSTOMERS = "customers"
    const val CUSTOMER_ID = "/{customerId}"
}
