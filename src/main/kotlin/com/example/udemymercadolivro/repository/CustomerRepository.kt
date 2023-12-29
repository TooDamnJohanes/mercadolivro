package com.example.udemymercadolivro.repository

import com.example.udemymercadolivro.model.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<Customer, Int> {
}