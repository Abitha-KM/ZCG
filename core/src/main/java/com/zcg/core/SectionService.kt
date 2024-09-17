package com.zcg.core

import com.zcg.core.response.SectionDTO
import retrofit2.http.GET
import javax.inject.Inject

class SectionService @Inject constructor(
    private val sectionApi: SectionApi
) {
    suspend fun getSections() : List<SectionDTO> {
        return sectionApi.getSections()
    }
}

interface SectionApi {
    @GET("b/5BEJ")
    suspend fun getSections() : List<SectionDTO>
}
