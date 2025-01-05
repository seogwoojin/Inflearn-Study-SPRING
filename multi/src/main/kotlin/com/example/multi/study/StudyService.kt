package com.example.multi.study

import org.springframework.stereotype.Service


@Service
class StudyService {
    fun callExternalApi(){
        Thread.sleep(500)
    }
}