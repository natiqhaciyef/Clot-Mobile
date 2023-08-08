package com.natiqhaciyef.clotmobile.domain.repositories.remote

import com.natiqhaciyef.clotmobile.data.models.CoffeeModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CoffeeResult

interface CoffeeRemoteRepository {

    suspend fun getAllCoffee(): CoffeeResult

    suspend fun getCoffeeById(id: Int): CoffeeModel?

    suspend fun insertCoffee(coffee: CoffeeModel): CRUDResponse

    suspend fun deleteCoffee(id: Int): CRUDResponse

    suspend fun updateCoffee(coffee: CoffeeModel): CRUDResponse

}