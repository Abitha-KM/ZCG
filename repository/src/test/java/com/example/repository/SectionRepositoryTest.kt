package com.example.repository

import com.zcg.core.SectionService
import com.zcg.core.response.SectionDTO
import com.zcg.model.Section
import com.zcg.model.SectionType
import com.zcg.repository.Result
import com.zcg.repository.SectionRepositoryImpl
import com.zcg.repository.mapper.toLocal
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SectionRepositoryImplTest {

    // Set up a test dispatcher for coroutines
    private val testDispatcher = StandardTestDispatcher()

    // Mock dependencies
    private val sectionService = mockk<SectionService>()

    // Create an instance of the repository to test
    private lateinit var sectionRepository: SectionRepositoryImpl

    @Before
    fun setUp() {
        // Set the main dispatcher to a TestDispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize the repository with the mocked service and test dispatcher
        sectionRepository = SectionRepositoryImpl(
            sectionService = sectionService,
            defaultDispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher to the original Main dispatcher after each test
        Dispatchers.resetMain()
    }

    @Test
    fun `getSections returns success when service call succeeds`() = runTest {
        // Prepare mock data for SectionDTO
        val sectionDTO = SectionDTO(sectionType = "banner", items = emptyList())
        val section = Section(sectionType = SectionType.BANNER, items = emptyList())

        // Mock the service call to return a list containing the mock SectionDTO
        coEvery { sectionService.getSections() } returns listOf(sectionDTO)

        // Correctly mock the static mapping function from SectionMapperExt.kt
        mockkStatic("com.zcg.repository.mapper.SectionMapperExtKt")
        coEvery { sectionDTO.toLocal() } returns section

        // Call the method under test
        val result = sectionRepository.getSections()

        // Assert that the result is a success and contains the expected data
        assertTrue(result is Result.Success)
        assertEquals(listOf(section), (result as Result.Success).data)
    }

    @Test
    fun `getSections returns error when service call fails`() = runTest {
        // Mock the service call to throw an exception
        val exception = Exception("Network Error")
        coEvery { sectionService.getSections() } throws exception

        // Call the method under test
        val result = sectionRepository.getSections()

        // Assert that the result is an error and contains the expected exception
        assertTrue(result is Result.Error)
        assertEquals(exception, (result as Result.Error).exception)
    }
}
