package com.bahraoui.F1.controller

import com.bahraoui.F1.model.Constructor
import com.bahraoui.F1.service.ConstructorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/constructors")
class ConstructorController(private val service:ConstructorService) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e:NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e:IllegalArgumentException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getConstructors():Collection<Constructor> = service.getConstructors()

    @GetMapping("/{id}")
    fun getConstructor(@PathVariable id:Int) = service.getConstructor(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addConstructor(@RequestBody constructor: Constructor): Constructor = service.addConstructor(constructor)

    @PatchMapping
    fun updateConstructor(@RequestBody constructor: Constructor): Constructor = service.updateConstructor(constructor)


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteConstructor(@PathVariable id:Int):Unit = service.deleteConstructor(id)


}