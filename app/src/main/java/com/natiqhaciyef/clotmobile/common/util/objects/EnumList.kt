package com.natiqhaciyef.clotmobile.common.util.objects

import com.natiqhaciyef.clotmobile.data.models.enums.ClothesCategory
import com.natiqhaciyef.clotmobile.data.models.enums.ClothesSizes
import com.natiqhaciyef.clotmobile.data.models.enums.ClothesTypes
import com.natiqhaciyef.clotmobile.data.models.enums.PriceCurrencies
import com.natiqhaciyef.clotmobile.data.models.enums.Seasons
import com.natiqhaciyef.clotmobile.data.models.enums.UserTypes

object EnumList {

    val clothesCategories = listOf(
        ClothesCategory.Sport.name,
        ClothesCategory.Clothes.name,
        ClothesCategory.UnderClothes.name,
        ClothesCategory.Pants.name,
        ClothesCategory.Shorts.name,
        ClothesCategory.Shoes.name,
        ClothesCategory.Jewelery.name,
    )

    val clothesSizes = listOf(
        ClothesSizes.XXS.name,
        ClothesSizes.XS.name,
        ClothesSizes.S.name,
        ClothesSizes.M.name,
        ClothesSizes.L.name,
        ClothesSizes.XL.name,
        ClothesSizes.XXL.name,
    )

    val clothesTypes = listOf(
        ClothesTypes.Shirts.name,
        ClothesTypes.Outerwear.name,
        ClothesTypes.Coats.name,
        ClothesTypes.Pants.name,
        ClothesTypes.Shoes.name,
        ClothesTypes.Caps.name,
        ClothesTypes.Glasses.name,
        ClothesTypes.Shorts.name,
        ClothesTypes.Accessories.name,
    )

    val priceCurrencies = listOf(
        PriceCurrencies.AZN.name,
        PriceCurrencies.USD.name,
        PriceCurrencies.EURO.name,
        PriceCurrencies.TL.name,
        PriceCurrencies.Pound.name,
        PriceCurrencies.CAD.name,
    )

    val seasons = listOf(
        Seasons.Autumn.name,
        Seasons.Spring.name,
        Seasons.Summer.name,
        Seasons.Winter.name
    )

    val userType = listOf(
        UserTypes.User.name,
        UserTypes.Admin.name,
        UserTypes.Premium.name
    )

}