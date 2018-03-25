package cn.edu.nuaa.launcher.dummy;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuaa.launcher.R;
import cn.edu.nuaa.launcher.utils.ContainerFactory;

/**
 * Created by Meteor on 2018/3/17.
 */

public class AppRecyclerAdapter extends RecyclerView.Adapter<AppRecyclerAdapter.AppRecyclerViewHolder> {
    ArrayList<ResolveInfo> data = ContainerFactory.newList();
    Context context;

    public AppRecyclerAdapter(List<ResolveInfo> data, Context context) {
        this.data.addAll(data);
        this.context = context;
    }

    @Override
    public AppRecyclerAdapter.AppRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_item, parent, false);
        return new AppRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AppRecyclerAdapter.AppRecyclerViewHolder holder, final int position) {
        View           view = holder.itemView;
        ResolveInfo    info = data.get(position);
        ImageView      iv   = view.findViewById(R.id.app_icon);
        TextView       tv   = view.findViewById(R.id.app_name);
        PackageManager pm   = context.getPackageManager();
        tv.setText(info.loadLabel(pm));
        iv.setImageDrawable(info.loadIcon(pm));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResolveInfo info = data.get(position);
                if (info != null) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClassName(info.activityInfo.applicationInfo.packageName, info.activityInfo.name);
                    AppRecyclerAdapter.this.context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class AppRecyclerViewHolder extends RecyclerView.ViewHolder {
        public AppRecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }


}
