package com.zcg.repository.mapper

import com.zcg.core.response.ItemDTO
import com.zcg.core.response.SectionDTO
import com.zcg.model.Item
import com.zcg.model.Section
import com.zcg.model.SectionType

fun SectionDTO.toLocal() = Section(
    sectionType = SectionType.getValue(sectionType),
    items = items.map {
        it.toLocal()
    }
)

fun ItemDTO.toLocal() = Item(
    title = title,
    image = image
)
