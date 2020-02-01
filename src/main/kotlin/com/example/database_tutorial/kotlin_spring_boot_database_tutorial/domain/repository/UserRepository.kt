package com.example.database_tutorial.kotlin_spring_boot_database_tutorial.domain.repository

import com.example.database_tutorial.kotlin_spring_boot_database_tutorial.domain.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int>