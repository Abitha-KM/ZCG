package com.zcg.model

enum class SectionType(val type: String) {
    HORIZONTAL_FREE_SCROLL("horizontalFreeScroll"),
    SPLIT_BANNER("splitBanner"),
    BANNER("banner");

    companion object {
        fun getValue(value: String) = entries.first { it.type == value }
    }
}
