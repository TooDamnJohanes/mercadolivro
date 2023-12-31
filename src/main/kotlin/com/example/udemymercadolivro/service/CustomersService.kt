package com.example.udemymercadolivro.service

import com.example.udemymercadolivro.model.Customer
import com.example.udemymercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomersService(
    val customerRepository: CustomerRepository
) {
    private val customersList = mutableListOf<Customer>()

    fun createNewCustomer(newCustomerDto: Customer): Boolean {
        return try {
            customerRepository.save(createNewCustomerObject(newCustomerDto))
            true
        } catch (t: Throwable) {
            println("Fail to save customer $newCustomerDto")
            false
        }
    }

    fun getCustomers(filterName: String?): List<Customer> {
        filterName?.let { customerName ->
            return filterCustomersListByName(customerName = customerName)
        }
        return customerRepository.findAll().toList()
    }

    fun getCustomerById(customerId: Int): Customer {
        return customerRepository.findById(customerId).orElseThrow()
    }

    fun updateCustomer(customerId: Int, customerUpdated: Customer): String {
        val customerToUpdate = filterCustomerById(customerId = customerId)
        val updatedCustomer = updateCustomerObject(customerToUpdate, customerUpdated)
        customersList.map { customer ->
            if (customer.id == updatedCustomer.id) {
                customer.apply {
                    this.email = updatedCustomer.email
                    this.firstName = updatedCustomer.firstName
                    this.lastName = updatedCustomer.lastName
                }
            }
        }
        println(customersList)
        return "Customer ${updatedCustomer.firstName} updated!"
    }

    fun deleteCustomerById(
        customerId: Int
    ): Boolean {
        return try {
            val userToDelete = filterCustomerById(customerId)
            customersList.remove(userToDelete)
            true
        } catch (t: Throwable) {
            false
        }
    }

    private fun updateCustomerObject(customerToUpdate: Customer, updatedCustomer: Customer): Customer {
        return customerToUpdate.copy(
            email = updatedCustomer.email.ifBlank {
                customerToUpdate.email
            },
            firstName = updatedCustomer.firstName.ifBlank {
                customerToUpdate.firstName
            },
            lastName = updatedCustomer.lastName.ifBlank {
                customerToUpdate.lastName
            }
        )
    }

    private fun createNewCustomerObject(newCustomerDto: Customer): Customer {
        return Customer(
            id = newCustomerDto.id,
            firstName = newCustomerDto.firstName,
            lastName = newCustomerDto.lastName,
            email = newCustomerDto.email
        )
    }

    private fun filterCustomerById(customerId: Int): Customer {
        return customersList.first { filteredCustomer ->
            filteredCustomer.id == customerId
        }
    }

    private fun filterCustomersListByName(customerName: String): List<Customer> {
        return customersList.filter { customer ->
            customer.firstName.contains(customerName, ignoreCase = true)
        }
    }
}
