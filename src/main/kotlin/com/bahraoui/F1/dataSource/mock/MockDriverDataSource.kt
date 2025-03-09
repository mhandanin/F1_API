package com.bahraoui.F1.dataSource.mock

import com.bahraoui.F1.dataSource.DriverDataSource
import com.bahraoui.F1.model.Driver
import org.springframework.stereotype.Repository

@Repository
class MockDriverDataSource : DriverDataSource {
    val drivers = mutableListOf(
        Driver(
            1,
            "Alexander",
            "Albon",
            8,
            "Thailand",
            24,
            0,
            0,
            0,
            240,
            "London, England",
            "23-03-1996",
            0,
            0,
            23, 0,
            false,
            12
        ),
        Driver(
            2,
            "Carlos",
            "Sainz",
            2,
            "Spain",
            818,
            16,
            0,
            0,
            170,
            "Madrid, Spain",
            "01-09-1994",
            0,
            0,
            55, 0, false, 290
        ),
        Driver(
            3,
            "Charles",
            "Leclerc",
            2,
            "Monaco",
            1040,
            25,
            18,
            0,
            110,
            "Monte Carlo, Monaco",
            "16-10-1997",
            0, 0, 16, 0, false, 356
        ),

        Driver(
            4,
            "Esteban",
            "Ocon",
            4,
            "France",
            372,
            2,
            0,
            0,
            134,
            "Évreux, France",
            "17-09-1996", 0,
            0,
            0, 31, false, 23
        )
    )

    override fun retrieveDrivers(): Collection<Driver> = drivers

    override fun retrieveDriver(id: Int): Driver = drivers.first { it.id == id }

    override fun createDriver(driver: Driver): Driver {
        if (drivers.any { it.id == driver.id }) {
            throw IllegalArgumentException("Driver with id ${driver.id} already exists!")
        }
        drivers.add(driver)
        return driver

    }

    override fun updateDriver(driver: Driver): Driver {
        val currentDriver = drivers.firstOrNull { it.id == driver.id }
            ?: throw NoSuchElementException("there's no driver with id:  ${driver.id} ")

        drivers.remove(currentDriver)
        drivers.add(driver)

        return driver
    }

    override fun deleteDriver(id: Int) {
        val currentDriver = drivers.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("there's no driver with id:  ${id} ")

        drivers.remove(currentDriver)

    }


}