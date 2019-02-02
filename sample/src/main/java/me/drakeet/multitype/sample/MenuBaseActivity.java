package me.drakeet.multitype.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import me.drakeet.multitype.sample.bilibili.BilibiliActivity;
import me.drakeet.multitype.sample.communication.CommunicateWithBinderActivity;
import me.drakeet.multitype.sample.more.MoreApisPlayground;
import me.drakeet.multitype.sample.selectable.MultiSelectableActivity;
import me.drakeet.multitype.sample.normal.NormalActivity;
import me.drakeet.multitype.sample.one2many.OneDataToManyActivity;
import me.drakeet.multitype.sample.payload.TestPayloadActivity;
import me.drakeet.multitype.sample.weibo.WeiboActivity;

/**
 * @author drakeet
 */
@SuppressLint("Registered")
public class MenuBaseActivity extends AppCompatActivity {

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Intent intent = new Intent();
    switch (item.getItemId()) {
      case R.id.NormalActivity:
        intent.setClass(this, NormalActivity.class);
        break;
      case R.id.MultiSelectableActivity:
        intent.setClass(this, MultiSelectableActivity.class);
        break;
      case R.id.CommunicateWithBinderActivity:
        intent.setClass(this, CommunicateWithBinderActivity.class);
        break;
      case R.id.BilibiliActivity:
        intent.setClass(this, BilibiliActivity.class);
        break;
      case R.id.WeiboActivity:
        intent.setClass(this, WeiboActivity.class);
        break;
      case R.id.OneDataToManyActivity:
        intent.setClass(this, OneDataToManyActivity.class);
        break;
      case R.id.TestPayloadActivity:
        intent.setClass(this, TestPayloadActivity.class);
        break;
      case R.id.MoreApisPlayground:
        intent.setClass(this, MoreApisPlayground.class);
        break;
      default:
        return false;
    }
    startActivity(intent);
    this.finish();
    return true;
  }
}
