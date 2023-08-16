package com.natiqhaciyef.clotmobile.common.helpers

import com.natiqhaciyef.clotmobile.data.models.enums.Colors

fun colorConvertHexCode(color : String): String{
    return when(color){
        Colors.White.name -> Colors.White.code
        Colors.DarkBlue.name -> Colors.DarkBlue.code
        Colors.Blue.name -> Colors.Blue.code
        Colors.LightBlue.name -> Colors.LightBlue.code
        Colors.ExtraLightBlue.name -> Colors.ExtraLightBlue.code

        Colors.DarkRed.name -> Colors.DarkRed.code
        Colors.Red.name -> Colors.Red.code
        Colors.LightRed.name -> Colors.LightRed.code

        Colors.DarkGreen.name -> Colors.DarkGreen.code
        Colors.Green.name -> Colors.Green.code
        Colors.LightGreen.name -> Colors.LightGreen.code

        Colors.DarkYellow.name -> Colors.DarkYellow.code
        Colors.Yellow.name -> Colors.Yellow.code
        Colors.LightYellow.name -> Colors.LightYellow.code

        Colors.DarkPurple.name -> Colors.DarkPurple.code
        Colors.Purple.name -> Colors.Purple.code
        Colors.LightPurple.name -> Colors.LightPurple.code

        Colors.DarkBrown.name -> Colors.DarkBrown.code
        Colors.Brown.name -> Colors.Brown.code
        Colors.LightBrown.name -> Colors.LightBrown.code

        Colors.DarkAquatic.name -> Colors.DarkAquatic.code
        Colors.Aquatic.name -> Colors.Aquatic.code
        Colors.LightAquatic.name -> Colors.LightAquatic.code
        else -> "#000000"
    }
}