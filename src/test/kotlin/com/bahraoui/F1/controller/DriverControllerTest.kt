package com.bahraoui.F1.controller

import com.bahraoui.F1.model.Driver
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
internal class DriverControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {


    val baseUrl = "/api/drivers"


    @Nested
    @DisplayName("GET /api/drivers")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetDrivers {

        @Test
        fun `should return all drivers`() {
            //when/then
            mockMvc.get(baseUrl)
                .andDo { println() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].id") {
                        value(1)
                    }
                }
        }
    }



    @Nested
    @DisplayName("GET /api/drivers/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetDriver {

        @Test
        fun `should return the driver with the given id`() {
            //given
            val id = 1

            //when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.equipe") { value(8) }
                }
        }


        @Test
        fun `should return not found if the id doesn't exist`() {
            //given
            val id = 150

            //when
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }

    }


    @Nested
    @DisplayName("POST /api/drivers")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewDriver {

        @Test
        fun `should add new driver`() {
            //given
            val newDriver = Driver(
                5,
                "Esteban",
                "Ocon",
                4,
                "France",
                372,
                2,
                0,
                0,
                134,
                "Évreux, France",
                "17-09-1996", 0,
                0,
                0, 31, false, 23
            )

            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newDriver)
            }

            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") { value(5) }
                }
        }

        @Test
        fun `should return bad request if driver id already exists`() {
            //given
            val invalidDriver = Driver(
                2,
                "Charles",
                "Leclerc",
                2,
                "Monaco",
                1040,
                25,
                18,
                0,
                110,
                "Monte Carlo, Monaco",
                "16-10-1997",
                0, 0, 16, 0, false, 356
            )

            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidDriver)
            }

            //then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
        }
    }

    @Nested
    @DisplayName("PATCH /api/drivers")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingDriver {

        @Test
        fun `should update driver already existe`() {
            //given
            val updateDriver = Driver(
                4,
                "Esteban",
                "Ocon",
                4,
                "France",
                372,
                2,
                0,
                0,
                134,
                "Évreux, France",
                "17-09-1996", 0,
                0,
                0, 31, false, 23
            )

            //when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateDriver)
            }

            //then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updateDriver))
//                        jsonPath("$.id") { value(4) }
                    }
                }


            mockMvc.get("$baseUrl/${updateDriver.id}")
                .andExpect {
                    content { json(objectMapper.writeValueAsString(updateDriver)) }
                }

        }


        @Test
        fun `should return BAD Request if no driver with the given id exists`() {
            // given
            val invalidDriver = Driver(
                5,
                "Fernando",
                "Alonso",
                6,
                "Spain",
                2100,
                105,
                22,
                2,
                370,
                "Oviedo, Spain",
                "29-07-1981",
                0,
                0, 14, 0, false, 70
            )

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidDriver)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }

    }


    @Nested
    @DisplayName("DELETE /api/drivers/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingDriver {

        @Test
        fun `should delete driver with id`() {
            //given
            val id = 3

            //when//then
            mockMvc.delete("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$id")
                .andExpect { status { isNotFound() } }
            
        }
        
        @Test
        fun `should return not Found if no driver found with id`(){
            //given
            val invalidId=1000


            // when//then
            mockMvc.delete("$baseUrl/$invalidId")
                .andDo { print() }
                .andExpect { status { isNotFound() } }

            
        }
    }


}