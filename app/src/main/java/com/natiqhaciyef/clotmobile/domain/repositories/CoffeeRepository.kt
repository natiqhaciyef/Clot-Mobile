package com.natiqhaciyef.clotmobile.domain.repositories

import com.natiqhaciyef.clotmobile.data.models.CoffeeModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CoffeeResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CoffeeRepository {

    suspend fun getAllCoffee(): CoffeeResult

    suspend fun getCoffeeByTitle(title: String): CoffeeModel?

    suspend fun insertCoffee(coffee: CoffeeModel): CRUDResponse

    suspend fun deleteCoffee(id: Int): CRUDResponse

    suspend fun updateCoffee(coffee: CoffeeModel): CRUDResponse

}