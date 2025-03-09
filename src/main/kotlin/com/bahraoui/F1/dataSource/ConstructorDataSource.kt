package com.bahraoui.F1.dataSource

import com.bahraoui.F1.model.Constructor


interface ConstructorDataSource {

    fun retrieveConstructors(): Collection<Constructor>

    fun retrieveConstructor(id: Int): Constructor

    fun createConstructor(constructor: Constructor): Constructor

    fun updateConstructor(constructor: Constructor): Constructor

    fun deleteConstructor(id: Int):Unit
}