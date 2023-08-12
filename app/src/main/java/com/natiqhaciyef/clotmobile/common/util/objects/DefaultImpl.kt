package com.natiqhaciyef.clotmobile.common.util.objects

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.models.enums.ClothesCategory
import com.natiqhaciyef.clotmobile.data.models.enums.ClothesSizes
import com.natiqhaciyef.clotmobile.data.models.enums.PriceCurrencies
import com.natiqhaciyef.clotmobile.data.models.enums.Seasons

object DefaultImpl {
    val clothesModel = ClothesModel(
        id = 0,
        title = "Nike Pro 1",
        details = "Women's mid-rise graphic short",
        brand = "Nike",
        price = 120.0,
        priceCurrency = PriceCurrencies.USD.name,
        size = ClothesSizes.M.name,
        color = "Black",
        image = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/6915c42d-d050-4767-bc0d-1a803fa99c56/pro-mid-rise-8cm-graphic-shorts-NmGqL2.png",
        type = "Short",
        category = ClothesCategory.Shorts.name,
        cargoPrice = 20.0,
        season = Seasons.Summer.name,
        country = "USA",
        isActive = 1
    )

}