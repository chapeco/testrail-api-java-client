package com.codepine.api.testrail.model

import org.junit.Test
import kotlin.test.assertEquals

class CaseTest()
{
    @Test
    fun createCaseTest()
    {
        val case = Case()
        assertEquals(0,case.id)
    }
}