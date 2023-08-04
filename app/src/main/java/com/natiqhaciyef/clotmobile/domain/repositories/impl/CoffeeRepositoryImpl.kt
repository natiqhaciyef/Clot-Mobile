package com.natiqhaciyef.clotmobile.domain.repositories.impl

import com.natiqhaciyef.clotmobile.data.models.CoffeeModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CoffeeResult
import com.natiqhaciyef.clotmobile.data.source.remote.CoffeeDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.CoffeeRepository

class CoffeeRepositoryImpl(private val ds: CoffeeDataSource) : CoffeeRepository  {
    override suspend fun getAllCoffee(): CoffeeResult = ds.getAllCoffee()

    override suspend fun getCoffeeById(id: Int): CoffeeModel? = ds.getCoffeeById(id)

    override suspend fun insertCoffee(coffee: CoffeeModel): CRUDResponse = ds.insertCoffee(coffee)

    override suspend fun deleteCoffee(id: Int): CRUDResponse = ds.deleteCoffee(id)

    override suspend fun updateCoffee(coffee: CoffeeModel): CRUDResponse = ds.updateCoffee(coffee)
}