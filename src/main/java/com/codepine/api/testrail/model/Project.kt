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

/**
 *
 */
package com.codepine.api.testrail.model

import com.codepine.api.testrail.TestRail
import com.fasterxml.jackson.annotation.JsonView

import java.util.Date

/**
 * TestRail project.
 */
class Project {

    var id: Int = 0

    @JsonView(TestRail.Projects.Add::class, TestRail.Projects.Update::class)
    var name: String? = null

    @JsonView(TestRail.Projects.Add::class, TestRail.Projects.Update::class)
    var announcement: String? = null

    @JsonView(TestRail.Projects.Add::class, TestRail.Projects.Update::class)
    var showAnnouncement: Boolean? = null

    @JsonView(TestRail.Projects.Update::class)
    var isCompleted: Boolean? = null

    var completedOn: Date? = null

    var url: String? = null

    @JsonView(TestRail.Projects.Add::class, TestRail.Projects.Update::class)
    var suiteMode: Int? = null
}