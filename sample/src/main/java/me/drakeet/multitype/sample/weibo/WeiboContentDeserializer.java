/*
 * Copyright 2016 drakeet. https://github.com/drakeet
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

package me.drakeet.multitype.sample.weibo;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import me.drakeet.multitype.sample.weibo.content.SimpleImage;
import me.drakeet.multitype.sample.weibo.content.SimpleText;

/**
 * @author drakeet
 */
public class WeiboContentDeserializer implements JsonDeserializer<WeiboContent> {

    @Override
    public WeiboContent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        Gson gson = GsonProvider.gson;
        JsonObject jsonObject = (JsonObject) json;
        final String contentType = stringOrEmpty(jsonObject.get("content_type"));
        WeiboContent content = null;

        if (contentType.equals(SimpleText.TYPE)) {
            content = gson.fromJson(json, SimpleText.class);
        } else if (contentType.equals(SimpleImage.TYPE)) {
            content = gson.fromJson(json, SimpleImage.class);
        }
        return content;
    }


    private String stringOrEmpty(JsonElement jsonElement) {
        return jsonElement.isJsonNull() ? "" : jsonElement.getAsString();
    }
}
