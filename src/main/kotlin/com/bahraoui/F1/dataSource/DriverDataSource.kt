package com.bahraoui.F1.dataSource

import com.bahraoui.F1.model.Driver

interface DriverDataSource {

    fun retrieveDrivers(): Collection<Driver>

    fun retrieveDriver(id: Int): Driver

    fun createDriver(driver: Driver): Driver

    fun updateDriver(driver: Driver): Driver

    fun deleteDriver(id: Int):Unit
}