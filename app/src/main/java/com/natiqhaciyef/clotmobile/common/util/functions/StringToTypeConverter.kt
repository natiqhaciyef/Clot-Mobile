package com.natiqhaciyef.clotmobile.common.util.functions


fun main() {
    val map = mutableMapOf("start" to "21 June", "end" to "24 June")
    println(map.toSQLiteString())
    println(map.toSQLiteString().toSQLiteMutableMap())
}

fun Map<String, String>.toSQLiteString(): String{
    val keys = this.keys
    var str = ""

    for (key in keys){
        str += key
        str += ":"
        str += this[key]
        str += "#"
    }
    return str
}


fun String.toSQLiteMutableMap(): MutableMap<String, String>{
    val map = mutableMapOf<String, String>()
    val list = mutableListOf<String>()
    var vanishData = ""
    var value = ""
    var key = ""


    for (char in this){
        if (char != '#')
            vanishData += char
        else{
            list.add(vanishData)
            vanishData = ""
        }
    }

    for (vd in list){
        for (char in vd){
            if (char != ':')
                value += char
            else {
                key = value
                value = ""
            }
        }
        map[key] = value
        key = ""
        value = ""
    }
    return map
}



fun MutableList<String>.toSQLiteString(): String {
    var str = ""
    for (element in this) {
        str += element
        str += "#"
    }
    return str
}


fun String.toSQLiteMutableList(): MutableList<String> {
    val list = mutableListOf<String>()
    var word = ""
    for (element in this) {
        if (element != '#')
            word += element
        else{
            list.add(word)
            word = ""
        }
    }

    return list
}