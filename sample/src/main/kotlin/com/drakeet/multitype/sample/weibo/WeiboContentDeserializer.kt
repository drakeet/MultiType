/*
 * Copyright (c) 2016-present. Drakeet Xu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.drakeet.multitype.sample.weibo

import com.google.gson.*
import com.drakeet.multitype.sample.weibo.content.SimpleImage
import com.drakeet.multitype.sample.weibo.content.SimpleText
import java.lang.reflect.Type

/**
 * @author Drakeet Xu
 */
class WeiboContentDeserializer : JsonDeserializer<WeiboContent> {

  @Throws(JsonParseException::class)
  override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): WeiboContent? {
    val gson = WeiboJsonParser.GSON
    val jsonObject = json as JsonObject
    val contentType = stringOrEmpty(jsonObject.get("content_type"))
    var content: WeiboContent? = null

    if (SimpleText.TYPE == contentType) {
      content = gson.fromJson(json, SimpleText::class.java)
    } else if (SimpleImage.TYPE == contentType) {
      content = gson.fromJson(json, SimpleImage::class.java)
    }
    return content
  }

  private fun stringOrEmpty(jsonElement: JsonElement): String {
    return if (jsonElement.isJsonNull) "" else jsonElement.asString
  }
}
