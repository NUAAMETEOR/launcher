package cn.edu.nuaa.launcher;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Window;

public class LauncherActivity extends BaseFragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return AppFragment.newInstance(1);
    }


}
