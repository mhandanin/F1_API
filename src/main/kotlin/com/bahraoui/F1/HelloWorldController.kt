package com.bahraoui.F1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/wach")
class HelloWorldController {
    @GetMapping
    fun helloWorld():String="salam, this is a Rest endpoint"
}