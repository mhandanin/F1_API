package com.bahraoui.F1.repository

import com.bahraoui.F1.model.Constructor
import com.bahraoui.F1.model.Driver
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DriverRepository : JpaRepository<Driver, Int>

@Repository
interface ConstructorRepository : JpaRepository<Constructor, Int>