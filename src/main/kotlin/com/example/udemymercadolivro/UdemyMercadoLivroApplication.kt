package com.example.udemymercadolivro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = ["com.example.udemymercadolivro.model"])
class UdemyMercadoLivroApplication

fun main(args: Array<String>) {
	runApplication<UdemyMercadoLivroApplication>(*args)
}
