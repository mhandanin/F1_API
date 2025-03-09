package com.bahraoui.F1.controller

import com.bahraoui.F1.model.Driver
import com.bahraoui.F1.service.DriverService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/drivers")
class DriverController(private val service:DriverService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e:NoSuchElementException):ResponseEntity<String> = ResponseEntity(e.message,HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e:IllegalArgumentException):ResponseEntity<String> = ResponseEntity(e.message,HttpStatus.BAD_REQUEST)



    @GetMapping
    fun getDrivers():Collection<Driver> = service.getDrivers()

    @GetMapping("/{id}")
    fun getDriver(@PathVariable id:Int) = service.getDriver(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addDriver(@RequestBody driver:Driver): Driver = service.addDriver(driver)

    @PatchMapping
    fun updateDriver(@RequestBody driver:Driver):Driver = service.updateDriver(driver)


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDriver(@PathVariable id:Int):Unit = service.deleteDriver(id)
}