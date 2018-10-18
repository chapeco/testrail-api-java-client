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
 * TestRail plan.
 */
class Plan {

    var id: Int = 0

    @JsonView(TestRail.Plans.Add::class, TestRail.Plans.Update::class)
    var name: String? = null

    @JsonView(TestRail.Plans.Add::class, TestRail.Plans.Update::class)
    var description: String? = null

    var url: String? = null

    var projectId: Int = 0

    @JsonView(TestRail.Plans.Add::class, TestRail.Plans.Update::class)
    var milestoneId: Int? = null

    var assignedtoId: Int? = null

    var createdOn: Date? = null

    var createdBy: Int = 0

    @JsonProperty
    var isCompleted: Boolean = false

    var completedOn: Date? = null

    var passedCount: Int = 0

    var blockedCount: Int = 0

    var untestedCount: Int = 0

    var retestCount: Int = 0

    var failedCount: Int = 0

    var customStatus1Count: Int = 0

    var customStatus2Count: Int = 0

    var customStatus3Count: Int = 0

    var customStatus4Count: Int = 0

    var customStatus5Count: Int = 0

    var customStatus6Count: Int = 0

    var customStatus7Count: Int = 0

    @JsonView(TestRail.Plans.Add::class)
    var entries: List<Entry>? = null

    class Entry {

        var id: String? = null

        @JsonView(TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class, TestRail.Plans.UpdateEntry::class)
        var name: String? = null

        @JsonView(TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class)
        var suiteId: Int? = null

        @JsonView(TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class, TestRail.Plans.UpdateEntry::class)
        var assignedtoId: Int? = null

        @JsonView(TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class, TestRail.Plans.UpdateEntry::class)
        var includeAll: Boolean? = null

        @JsonView(TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class, TestRail.Plans.UpdateEntry::class)
        var caseIds: List<Int>? = null

        @JsonView(TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class)
        var configIds: List<Int>? = null

        @JsonView(TestRail.Plans.Add::class, TestRail.Plans.AddEntry::class)
        var runs: List<Run>? = null

        class Run : com.codepine.api.testrail.model.Run() {
            var entryId: String? = null
            var entryIndex: Int = 0
        }
    }
}
