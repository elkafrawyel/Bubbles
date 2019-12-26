package com.elwaha.bubbles.utilies

import com.beust.klaxon.Klaxon
import com.elwaha.bubbles.data.models.UserModel

class ObjectConverter {

    fun saveUser(userModel: UserModel): String {
        return Klaxon().toJsonString(userModel)
    }

    fun getUser(userString: String): UserModel {
        return Klaxon().parse(userString)!!
    }
}