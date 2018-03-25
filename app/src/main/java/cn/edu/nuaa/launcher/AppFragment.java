package cn.edu.nuaa.launcher;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AppFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AppFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AppFragment newInstance(int columnCount) {
        AppFragment fragment = new AppFragment();
        Bundle      args     = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    public static List<ResolveInfo> getAppInformation(Context context){
        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final PackageManager pm          = context.getPackageManager();
        PackageInfo          packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(context.getPackageName().toString(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String               appName     = context.getText(packageInfo.applicationInfo.labelRes).toString();
        List<ResolveInfo>    list        = pm.queryIntentActivities(startupIntent, 0);
        Collections.sort(list, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo o1, ResolveInfo o2) {
                String label1 = o1.loadLabel(pm).toString();
                String label2 = o2.loadLabel(pm).toString();
                return String.CASE_INSENSITIVE_ORDER.compare(label1, label2);
            }
        });
        Iterator<ResolveInfo> i = list.iterator();
        while (i.hasNext()) {
            ResolveInfo info = i.next();
            Log.d("app info", info.activityInfo.processName + " can startup");
            if (info.activityInfo.applicationInfo.packageName.contains(context.getPackageName()) && (info.activityInfo.name.contains(appName))) {
                i.remove();
            }
        }
        return list;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*View v = new AppRecyclerView(getActivity());
        v.setBackground(getActivity().getResources().getDrawable(R.drawable.laucher_bg));*/

        //用下面这种方法生成的布局更加好看点，上面方法布局可能略微不好看。可能跟container有关。实际调用构造函数是AppRecyclerView(Context context, @Nullable AttributeSet attrs)
        View v = inflater.inflate(R.layout.recyler, container, false);
        v.setBackground(getActivity().getResources().getDrawable(R.drawable.laucher_bg));
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
