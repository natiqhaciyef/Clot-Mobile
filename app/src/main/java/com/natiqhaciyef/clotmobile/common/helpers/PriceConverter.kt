package com.natiqhaciyef.clotmobile.common.helpers

fun priceConverter(currency: String): String {
    return when (currency) {
        "USD" -> { "$" }
        "AZN" -> { "Azn" }
        "EURO" -> { "€" }
        "Pound" -> { "£" }
        "CAD" -> { "$ CAD" }
        "TL" -> { "TL" }
        else -> { "Currency not selected" }
    }
}