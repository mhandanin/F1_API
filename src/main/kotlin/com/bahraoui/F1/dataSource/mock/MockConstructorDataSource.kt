package com.bahraoui.F1.dataSource.mock

import com.bahraoui.F1.dataSource.ConstructorDataSource
import com.bahraoui.F1.model.Constructor
import org.springframework.stereotype.Repository

@Repository
class MockConstructorDataSource : ConstructorDataSource {
    val constructors = mutableListOf(
        Constructor(
            1,
            "Red Bull Racing",
            0,
            2005,
            "RB19",
            "Honda RBPT",
            "Christian Horner",
            "Austria",
            700,
            0,
            0,
            103,
            6,
            "1 (x122)",
            listOf(MockDriverDataSource().retrieveDriver(1), MockDriverDataSource().retrieveDriver(2))
        ),
        Constructor(
            2,
            "Ferrari",
            0,
            1950,
            "SF-24",
            "Ferrari",
            "Fred Vasseur",
            "Italy", 600,
            0,
            0,
            253,
            16,
            "1 (x249)",
            listOf(MockDriverDataSource().retrieveDriver(1), MockDriverDataSource().retrieveDriver(2))

        ),
        Constructor(
            3,
            "Mercedes",
            0,
            1954,
            "W15",
            "Mercedes",
            "Toto Wolff",
            "Germany", 550,
            0,
            0,
            133,
            8,
            "1 (x120)",
            listOf(MockDriverDataSource().retrieveDriver(1), MockDriverDataSource().retrieveDriver(2))
        )
    )

    override fun retrieveConstructors(): Collection<Constructor> = constructors

    override fun retrieveConstructor(id: Int): Constructor = constructors.first { it.id == id }


    override fun createConstructor(constructor: Constructor): Constructor {
        if (constructors.any { it.id == constructor.id }) {
            throw IllegalArgumentException("Constructor with id ${constructor.id} already exists!")
        }
        constructors.add(constructor)
        return constructor

    }

    override fun updateConstructor(constructor: Constructor): Constructor {
        val currentConstructors = constructors.firstOrNull { it.id == constructor.id }
            ?: throw NoSuchElementException("there's no constructor with id:  ${constructor.id} ")

        constructors.remove(currentConstructors)
        constructors.add(constructor)

        return constructor
    }


    override fun deleteConstructor(id: Int) {
        val currentConstructors = constructors.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("there's no constructors with id:  ${id} ")

        constructors.remove(currentConstructors)

    }


}