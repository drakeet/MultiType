package me.drakeet.multitype.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.camnter.multitype.databinding.sample.normal.BindingNormalActivity;
import me.drakeet.multitype.sample.bilibili.BilibiliActivity;
import me.drakeet.multitype.sample.communicate_with_binder.SimpleActivity;
import me.drakeet.multitype.sample.multi_select.MultiSelectActivity;
import me.drakeet.multitype.sample.normal.NormalActivity;
import me.drakeet.multitype.sample.one2many.OneDataToManyActivity;
import me.drakeet.multitype.sample.payload.TestPayloadActivity;
import me.drakeet.multitype.sample.weibo.WeiboActivity;

/**
 * @author drakeet
 */
public class MenuBaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.NormalActivity:
                intent.setClass(this, NormalActivity.class);
                break;
            case R.id.MultiSelectActivity:
                intent.setClass(this, MultiSelectActivity.class);
                break;
            case R.id.communicate_with_binder:
                intent.setClass(this, SimpleActivity.class);
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
            case R.id.BindingNormalActivity:
                intent.setClass(this, BindingNormalActivity.class);
                break;
            default:
                return false;
        }
        startActivity(intent);
        this.finish();
        return true;
    }
}
