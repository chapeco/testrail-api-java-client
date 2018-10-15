/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Kunal Shah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.codepine.api.testrail.model

import com.codepine.api.testrail.TestRail
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import java.util.Date

/**
 * TestRail run.
 */
open class Run {

    private val id: Int = 0

    @JsonView(TestRail.Runs.Add::class, TestRail.Runs.Update::class)
    private val name: String? = null

    @JsonView(TestRail.Runs.Add::class, TestRail.Runs.Update::class)
    private val description: String? = null

    private val url: String? = null

    private val projectId: Int = 0

    private val planId: Int? = null

    @JsonView(TestRail.Runs.Add::class)
    private val suiteId: Int? = null

    @JsonView(TestRail.Runs.Add::class, TestRail.Runs.Update::class)
    private val milestoneId: Int? = null

    @JsonView(TestRail.Runs.Add::class, TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class)
    private val assignedtoId: Int? = null

    @JsonView(TestRail.Runs.Add::class, TestRail.Runs.Update::class, TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class)
    private val includeAll: Boolean? = null

    @JsonView(TestRail.Runs.Add::class, TestRail.Runs.Update::class, TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class)
    private val caseIds: List<Int>? = null

    private val createdOn: Date? = null

    private val createdBy: Int = 0

    @JsonProperty
    private val isCompleted: Boolean = false

    private val completedOn: Date? = null

    private val config: List<String>? = null

    @JsonView(TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class)
    private val configIds: List<Int>? = null

    private val passedCount: Int = 0

    private val blockedCount: Int = 0

    private val untestedCount: Int = 0

    private val retestCount: Int = 0

    private val failedCount: Int = 0

    private val customStatus1Count: Int = 0

    private val customStatus2Count: Int = 0

    private val customStatus3Count: Int = 0

    private val customStatus4Count: Int = 0

    private val customStatus5Count: Int = 0

    private val customStatus6Count: Int = 0

    private val customStatus7Count: Int = 0

}
