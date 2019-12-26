package com.elwaha.bubbles.utilies

object Constants {
    const val BASE_URL = "http://stbraq4it.com/rwaq/api/v1/"
    const val IMAGES_BASE_URL = "http://stbraq4it.com/rwaq/public/uploads/"
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