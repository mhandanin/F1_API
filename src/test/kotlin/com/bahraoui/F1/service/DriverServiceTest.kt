package com.bahraoui.F1.service

import com.bahraoui.F1.dataSource.DriverDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DriverServiceTest{
    private val dataSource: DriverDataSource = mockk(relaxed = true)
    private val driverService=DriverService(dataSource)
    @Test
    fun `should call its data source to retrieve drivers`(){

        //when
        driverService.getDrivers()
        
        //then
        verify(exactly = 1) { dataSource.retrieveDrivers() }
    }
    

}