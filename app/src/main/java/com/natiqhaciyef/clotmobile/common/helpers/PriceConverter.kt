package com.natiqhaciyef.clotmobile.common.helpers

fun priceConverter(currency: String): String {
    return when (currency) {
        "USD" -> {
            "$"
        }

        "AZN" -> {
            "Azn"
        }

        "EURO" -> {
            "€"
        }

        "Pound" -> {
            "£"
        }

        "CAD" -> {
            "$ CAD"
        }

        "TL" -> {
            "TL"
        }

        else -> {
            "Currency not selected"
        }
    }
}


fun priceValueConverter(price: Double): String {
    return if (price.toInt().toDouble() == price) {
        "${price.toInt()}"
    } else {
        "$price"
    }
}


fun totalPriceValueConverter(price: Double, fromCurrency: String, toCurrency: String): Double {
    return when (toCurrency) {
        "USD" -> {
            when (fromCurrency) {
                "AZN" -> { 0.59 * price }
                "EURO" -> { 1.08 * price }
                "Pound" -> { 1.26 * price }
                "CAD" -> { 0.74 * price }
                "TL" -> { 0.04 * price }
                else -> { price }
            }
        }

        "AZN" -> {
            when (fromCurrency) {
                "USD" -> { 1.7 * price }
                "EURO" -> { 1.84 * price }
                "Pound" -> { 2.15 * price }
                "CAD" -> { 1.26 * price }
                "TL" -> { 0.06 * price }
                else -> { price }
            }
        }

        "EURO" -> {
            when (fromCurrency) {
                "AZN" -> { 0.54 * price }
                "USD" -> { 0.92 * price }
                "Pound" -> { 1.17 * price }
                "CAD" -> { 0.68 * price }
                "TL" -> { 0.03 * price }
                else -> { price }
            }
        }

        "Pound" -> {
            when (fromCurrency) {
                "AZN" -> { 0.47 * price }
                "USD" -> { 0.79 * price }
                "EURO" -> { 0.86 * price }
                "CAD" -> { 0.58 * price }
                "TL" -> { 0.027 * price }
                else -> { price }
            }
        }

        "CAD" -> {
            when (fromCurrency) {
                "AZN" -> { 0.8 * price }
                "USD" -> { 1.4 * price }
                "EURO" -> { 1.5 * price }
                "Pound" -> { 1.75 * price }
                "TL" -> { 0.005 * price }
                else -> { price }
            }
        }

        "TL" -> {
            when (fromCurrency) {
                "AZN" -> { 16.05 * price }
                "USD" -> { 27.25 * price }
                "EURO" -> { 29.45 * price }
                "Pound" -> { 34.4 * price }
                "CAD" -> { 20.1 * price }
                else -> { price }
            }
        }

        else -> {
            price
        }
    }

}
