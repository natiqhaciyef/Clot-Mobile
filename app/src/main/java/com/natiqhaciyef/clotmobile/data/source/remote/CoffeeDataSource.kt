package com.natiqhaciyef.clotmobile.data.source.remote

import com.natiqhaciyef.clotmobile.data.models.CoffeeModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CoffeeResult
import com.natiqhaciyef.clotmobile.data.network.services.CoffeeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoffeeDataSource(
    private val service: CoffeeService
) {
    suspend fun getAllCoffee(): CoffeeResult = withContext(Dispatchers.IO) {
        service.getAllCoffee()
    }

    suspend fun getCoffeeById(id: Int): CoffeeModel? = withContext(Dispatchers.IO) {
        service.getCoffeeById(id)
    }

    suspend fun insertCoffee(
        coffee: CoffeeModel
    ): CRUDResponse = withContext(Dispatchers.IO) {
        service.insertCoffee(
            title = coffee.title,
            details = coffee.details,
            price = coffee.price,
            priceCurrency = coffee.priceCurrency,
            size = coffee.size,
            image = coffee.image,
            rating = coffee.rating,
            country = coffee.category
        )
    }

    suspend fun deleteCoffee(
        id: Int,
    ): CRUDResponse = withContext(Dispatchers.IO) {
        service.deleteCoffee(id = id)
    }

    suspend fun updateCoffee(
        coffee: CoffeeModel
    ): CRUDResponse = withContext(Dispatchers.IO) {
        service.updateCoffee(
            id = coffee.id,
            title = coffee.title,
            details = coffee.details,
            price = coffee.price,
            priceCurrency = coffee.priceCurrency,
            size = coffee.size,
            image = coffee.image,
            rating = coffee.rating,
            country = coffee.category
        )
    }


}