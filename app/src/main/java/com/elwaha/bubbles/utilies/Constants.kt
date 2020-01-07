package com.elwaha.bubbles.utilies

object Constants {
    const val BASE_URL = ""
    const val IMAGES_BASE_URL = ""
    const val AUTHORIZATION_START = "Bearer"
    const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

    enum class Language(val value: String) {
        ARABIC("ar"),
        ENGLISH("en")
    }

    enum class UserType(val value: Int) {
        USER(0),
        DRIVER(1)
    }
}