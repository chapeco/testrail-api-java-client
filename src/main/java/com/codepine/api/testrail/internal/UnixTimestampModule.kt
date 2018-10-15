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

package com.codepine.api.testrail.internal

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule

import java.io.IOException
import java.util.Date

/**
 * Jackson module to register serializers and deserializers for unix time stamp in seconds.
 *
 *
 * INTERNAL ONLY
 */
class UnixTimestampModule : SimpleModule() {
    init {
        addSerializer(Date::class.java, UnixTimestampSerializer())
        addDeserializer(Date::class.java, UnixTimestampDeserializer())
    }

    /**
     * Serializer to convert `java.util.Date` to unit timestamps in seconds.
     */
    internal class UnixTimestampSerializer : JsonSerializer<Date>() {

        @Throws(IOException::class, JsonProcessingException::class)
        override fun serialize(value: Date, jgen: JsonGenerator, provider: SerializerProvider) {
            jgen.writeNumber(value.time / 1000)
        }
    }

    /**
     * Deserializer to convert unit timestamps in seconds to `java.util.Date`.
     */
    internal class UnixTimestampDeserializer : JsonDeserializer<Date>() {

        @Throws(IOException::class, JsonProcessingException::class)
        override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Date {
            return Date(jp.valueAsInt * 1000L)
        }
    }
}
