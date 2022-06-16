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

package com.drakeet.multitype.sample

import android.annotation.SuppressLint
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.drakeet.multitype.sample.bilibili.BilibiliActivity
import com.drakeet.multitype.sample.communication.CommunicateWithBinderActivity
import com.drakeet.multitype.sample.concat.ConcatActivity
import com.drakeet.multitype.sample.more.MoreApisPlayground
import com.drakeet.multitype.sample.normal.NormalActivity
import com.drakeet.multitype.sample.one2many.OneDataToManyActivity
import com.drakeet.multitype.sample.payload.TestPayloadActivity
import com.drakeet.multitype.sample.selectable.MultiSelectableActivity
import com.drakeet.multitype.sample.weibo.WeiboActivity

/**
 * @author Drakeet Xu
 */
@SuppressLint("Registered")
open class MenuBaseActivity : AppCompatActivity() {

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    super.onCreateOptionsMenu(menu)
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val intent = Intent()
    when (item.itemId) {
      R.id.NormalActivity -> intent.setClass(this, NormalActivity::class.java)
      R.id.MultiSelectableActivity -> intent.setClass(this, MultiSelectableActivity::class.java)
      R.id.CommunicateWithBinderActivity -> intent.setClass(this, CommunicateWithBinderActivity::class.java)
      R.id.BilibiliActivity -> intent.setClass(this, BilibiliActivity::class.java)
      R.id.WeiboActivity -> intent.setClass(this, WeiboActivity::class.java)
      R.id.OneDataToManyActivity -> intent.setClass(this, OneDataToManyActivity::class.java)
      R.id.TestPayloadActivity -> intent.setClass(this, TestPayloadActivity::class.java)
      R.id.MoreApisPlayground -> intent.setClass(this, MoreApisPlayground::class.java)
      R.id.ConcatActivity -> intent.setClass(this, ConcatActivity::class.java)
      else -> return false
    }
    startActivity(intent)
    this.finish()
    return true
  }
}
