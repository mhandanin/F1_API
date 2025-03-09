package com.bahraoui.F1.model

import jakarta.persistence.*


@Entity
data class Driver(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    var firstName: String = "",
    var lastName: String = "",
    var equipe: Int = 0,
    var nationalite: String = "",
    var points: Int = 0,
    var podiums: Int = 0,
    var fastestLap: Int = 0,
    var worldChampionships: Long = 0,
    var GPsEntered: Int = 0,
    var birthPlace: String = "",
    var birthDate: String = "",
    var driverImage1: Int = 0,
    var driverImage2: Int = 0,
    var driverNumber: Int = 0,
    var flag: Int = 0,
    var retiredOrNot: Boolean = false,
    var seasonPoint: Int = 0,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructor_id")
    var constructor: Constructor? = null
)


@Entity
data class Constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    var nom: String,
    var logo: Int,
    val firstEntry: Int,
    var chassis: String,
    var powerUnit: String,
    var teamChief: String,
    val nationalite: String,
    var points: Int,
    var construcorColor: Int,
    var carImage: Int,
    var polePositions: Int,
    var constructorChampionships: Int,
    var highestFinish: String,


    //  cascade = [CascadeType.ALL]
    @OneToMany(mappedBy = "constructor", fetch = FetchType.LAZY)
    var pilotes: List<Driver> = mutableListOf()
)