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
import com.codepine.api.testrail.internal.CsvToListDeserializer
import com.codepine.api.testrail.internal.ListToCsvSerializer
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.core.JsonGenerationException
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.StdKeySerializer
import com.google.common.base.MoreObjects
import com.google.common.base.Preconditions

import java.io.IOException
import java.util.ArrayList
import java.util.Date
import java.util.HashMap

import com.codepine.api.testrail.model.Field.Type

/**
 * TestRail result.
 */
class Result {

    var id: Int = 0

    var testId: Int = 0

    @JsonView(TestRail.Results.AddListForCases::class)
    var caseId: Int? = null

    @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
    var statusId: Int? = null

    var createdOn: Date? = null

    var createdBy: Int = 0

    @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
    var assignedtoId: Int? = null

    @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
    var comment: String? = null

    @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
    var version: String? = null

    @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
    var elapsed: String? = null

    @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
    @JsonSerialize(using = ListToCsvSerializer::class)
    @JsonDeserialize(using = CsvToListDeserializer::class)
    var defects: ArrayList<String>? = null

    @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
    @JsonSerialize(keyUsing = CustomFieldSerializer::class)
    var customFields: MutableMap<String, Any>? = null

    /**
     * Add a defect.
     *
     * @param defect defect to be added
     * @return this instance for chaining
     */
    fun addDefect(defect: String): Result {
        Preconditions.checkArgument(!defect.isEmpty(), "defect cannot be empty")
        if (defects == null) {
            defects = ArrayList<String>()
        }
        defects!!.add(defect)
        return this
    }

    /**
     * Add a custom field.
     *
     * @param key   the name of the custom field with or without "custom_" prefix
     * @param value the value of the custom field
     * @return result instance for chaining
     */
    fun addCustomField(key: String, value: Any): Result {
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

    /**
     * Get custom field.
     *
     * Use Java Type Inference, to get the value with correct type. Refer to [Type] for a map of TestRail field types to Java types.
     *
     * @param key the system name of custom field
     * @param <T> the type of returned value
     * @return the value of the custom field
    </T> */
    fun <T> getCustomField(key: String): T {
        return customFields!![key] as T
    }

    /**
     * Serializer for custom fields.
     */
    private class CustomFieldSerializer : StdKeySerializer() {

        @Throws(IOException::class, JsonGenerationException::class)
        override fun serialize(o: Any, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
            super.serialize(CUSTOM_FIELD_KEY_PREFIX + o, jsonGenerator, serializerProvider)
        }
    }

    /**
     * Wrapper for list of `Result`s for internal use.
     */
    class List(results: MutableList<Result>) {

        @JsonView(TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
        var results: ArrayList<Result>? = null

    }

    companion object {

        var CUSTOM_FIELD_KEY_PREFIX = "custom_"
    }
}
