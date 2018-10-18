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

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.google.common.base.MoreObjects

import java.util.Collections
import java.util.HashMap

/**
 * TestRail test.
 */
class Test {

    var id: Int = 0

    var caseId: Int = 0

    var assignedtoId: Int? = null

    var title: String? = null

    var statusId: Int = 0

    var typeId: Int = 0

    var priorityId: Int = 0

    var milestoneId: Int? = null

    var runId: Int? = null

    var refs: String? = null

    var estimate: String? = null

    var estimateForecast: String? = null

    private var customFields: MutableMap<String, Any>? = null

    fun getCustomFields(): Map<String, Any> {
        return MoreObjects.firstNonNull(customFields, emptyMap())
    }

    /**
     * Add a custom field.
     *
     * @param key   the name of the custom field with or without "custom_" prefix
     * @param value the value of the custom field
     * @return test instance for chaining
     */
    fun addCustomField(key: String, value: Any): Test {
        if (customFields == null) {
            customFields = HashMap()
        }
        customFields!![key.replaceFirst(CUSTOM_FIELD_KEY_PREFIX.toRegex(), "")] = value
        return this
    }

    /**
     * Support for forward compatibility and extracting custom fields.
     *
     * @param key the name of the unknown field
     * @param value the value of the unkown field
     */
    @JsonAnySetter
    private fun addUnknownField(key: String, value: Any) {
        if (key.startsWith(CUSTOM_FIELD_KEY_PREFIX)) {
            addCustomField(key, value)
        }
    }

    companion object {

        var CUSTOM_FIELD_KEY_PREFIX = "custom_"
    }

}
