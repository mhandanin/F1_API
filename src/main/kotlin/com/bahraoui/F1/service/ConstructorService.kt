package com.bahraoui.F1.service

import com.bahraoui.F1.dataSource.ConstructorDataSource
import com.bahraoui.F1.model.Constructor
import org.springframework.stereotype.Service


@Service
class ConstructorService(private val dataSource : ConstructorDataSource) {


    fun getConstructors(): Collection<Constructor> = dataSource.retrieveConstructors()

    fun getConstructor(id:Int): Constructor = dataSource.retrieveConstructor(id)

    fun addConstructor(constructor: Constructor): Constructor = dataSource.createConstructor(constructor)

    fun updateConstructor(constructor: Constructor): Constructor =  dataSource.updateConstructor(constructor)

    fun deleteConstructor(id: Int):Unit = dataSource.deleteConstructor(id)
}