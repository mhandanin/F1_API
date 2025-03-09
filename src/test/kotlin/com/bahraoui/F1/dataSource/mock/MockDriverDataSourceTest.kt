package com.bahraoui.F1.dataSource.mock

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*


private fun Int.isNotBlank(): Boolean=this>0

internal class MockDriverDataSourceTest{
   private val mochDataSource=MockDriverDataSource()

    @Test
    fun `should provide a colliction of drivers`(){
        //given

        //when
        val drivers=mochDataSource.retrieveDrivers()

        //then
       assertThat(drivers).isNotEmpty
    }


    @Test
    fun `should provide some mock data`(){

        //when
        val drivers=mochDataSource.retrieveDrivers()

        //then
        assertThat(drivers).allMatch(){ it.id.isNotBlank()}
    }
}