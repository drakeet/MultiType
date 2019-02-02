package me.drakeet.multitype.sample

import android.annotation.SuppressLint
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import me.drakeet.multitype.sample.bilibili.BilibiliActivity
import me.drakeet.multitype.sample.communication.CommunicateWithBinderActivity
import me.drakeet.multitype.sample.more.MoreApisPlayground
import me.drakeet.multitype.sample.normal.NormalActivity
import me.drakeet.multitype.sample.one2many.OneDataToManyActivity
import me.drakeet.multitype.sample.payload.TestPayloadActivity
import me.drakeet.multitype.sample.selectable.MultiSelectableActivity
import me.drakeet.multitype.sample.weibo.WeiboActivity

/**
 * @author drakeet
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
      else -> return false
    }
    startActivity(intent)
    this.finish()
    return true
  }
}
