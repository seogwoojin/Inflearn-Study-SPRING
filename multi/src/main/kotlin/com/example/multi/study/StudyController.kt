package com.example.multi.study

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudyController (
    private val studyService: StudyService
){
    @GetMapping("/refund")
    fun refund(): String{
        println("refund Start")
        for(i in 1..50){
            studyService.callExternalApi()
        }
        return "Refund Completed"
    }

    @GetMapping("/refund/fast")
    fun fastRefund() : String {
        println("refund Start")

        synchronized(thiw)
        for(i in 1..50){
            studyService.callExternalApi()
        }
        return "Refund Completed"
    }
}