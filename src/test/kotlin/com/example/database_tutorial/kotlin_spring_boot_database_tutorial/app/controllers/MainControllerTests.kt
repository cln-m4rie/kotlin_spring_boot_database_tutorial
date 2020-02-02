package com.example.database_tutorial.kotlin_spring_boot_database_tutorial.app.controllers

import com.example.database_tutorial.kotlin_spring_boot_database_tutorial.domain.entity.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(SpringExtension::class)
@SpringBootTest
class MainControllerTests {
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var target: MainController

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build()
    }

    @Test
    fun getAllUsersTest() {
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk)
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON)
                )
    }

    @Test
    fun addNewUserTest() {
        mockMvc.perform(
                post("/add")
                        .param("name", "unit_test"))
                .andExpect(status().isOk)
                .andExpect(
                        content().string("Saved")
                )
    }

    @Test
    @Sql(statements = ["insert into user(name) values ('update_data');"])
    fun updateUserTest() {
        val lastUser: User = target.userRepository.findAll().last()
        mockMvc.perform(
                post("/update")
                        .param("id", lastUser.id.toString())
                        .param("name", "updated!!!"))
                .andExpect(status().isOk)
                .andExpect(
                        content().string("Updated")
                )
    }
}
