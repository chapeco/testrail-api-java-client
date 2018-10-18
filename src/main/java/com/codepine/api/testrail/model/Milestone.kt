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
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import java.util.Date

/**
 * TestRail milestone.
 */
class Milestone {

    var id: Int = 0

    @JsonView(TestRail.Milestones.Add::class, TestRail.Milestones.Update::class)
    var name: String? = null

    @JsonView(TestRail.Milestones.Add::class, TestRail.Milestones.Update::class)
    var description: String? = null

    @JsonProperty(value = "project_id")
    var projectId: Int = 0

    @JsonView(TestRail.Milestones.Add::class, TestRail.Milestones.Update::class)
    @JsonProperty(value = "due_on")
    var dueOn: Date? = null

    @JsonView(TestRail.Milestones.Update::class)
    @JsonProperty(value = "is_completed")
    var isCompleted: Boolean? = null

    @JsonProperty(value = "completed_on")
    var completedOn: Date? = null

    var url: String? = null

    @JsonView(TestRail.Milestones.Update::class)
    @JsonProperty(value = "is_started")
    var isStarted: Boolean = false

    var milestones: Array<Milestone>? = null

    @JsonView(TestRail.Milestones.Add::class, TestRail.Milestones.Update::class)
    @JsonProperty(value = "parent_id")
    var parentId: Int = 0

    @JsonView(TestRail.Milestones.Add::class, TestRail.Milestones.Update::class)
    @JsonProperty(value = "start_on")
    var startOn: Date? = null

    @JsonProperty(value = "started_on")
    var startedOn: Date? = null
}
