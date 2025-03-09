package com.bahraoui.F1.service

import com.bahraoui.F1.dataSource.DriverDataSource
import com.bahraoui.F1.model.Driver
import org.springframework.stereotype.Service

@Service
class DriverService(private val dataSource : DriverDataSource) {

    fun getDrivers(): Collection<Driver> = dataSource.retrieveDrivers()

    fun getDriver(id:Int): Driver = dataSource.retrieveDriver(id)

    fun addDriver(driver: Driver): Driver = dataSource.createDriver(driver)

    fun updateDriver(driver: Driver): Driver =  dataSource.updateDriver(driver)

    fun deleteDriver(id: Int):Unit = dataSource.deleteDriver(id)
}