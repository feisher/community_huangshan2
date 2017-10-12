package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.yusong.community.R;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuyu on 2016/11/11.
 */

public class VideoDetailNewAdapter extends BaseAdapter {

    public final static String TAG = "TT2";

    private List<VideoAlbum.VideoListBean> list = new ArrayList<>();
    private LayoutInflater inflater = null;
    private Context context;

    private ViewGroup rootView;
    private OrientationUtils orientationUtils;

    private boolean isFullVideo;

    private ListVideoUtil listVideoUtil;

    public VideoDetailNewAdapter(Context context, ListVideoUtil listVideoUtil, List<VideoAlbum.VideoListBean> list) {
        super();
        this.context = context;
        this.listVideoUtil = listVideoUtil;
        this.list = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_list_video, null);
            holder.videoContainer = (FrameLayout) convertView.findViewById(R.id.list_item_container);
            holder.playerBtn = (ImageView) convertView.findViewById(R.id.list_item_btn);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.imageView = new ImageView(context);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        VideoAlbum.VideoListBean videoListBean = (VideoAlbum.VideoListBean) getItem(position);
        //增加封面
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.tvName.setText(DateUtil.getHidatenew(videoListBean.getCreateTime()));
        if (videoListBean != null && videoListBean.getImagePath() != null) {
            GlideImgManager.loadImage(context, videoListBean.getImagePath(), holder.imageView);
        } else {
            holder.imageView.setImageResource(R.mipmap.xxx1);
        }
        listVideoUtil.addVideoPlayer(position, holder.imageView, TAG, holder.videoContainer, holder.playerBtn);

        holder.playerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                //listVideoUtil.setLoop(true);
                listVideoUtil.setPlayPositionAndTag(position, TAG);
                final String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
                //listVideoUtil.setCachePath(new File(FileUtils.getPath()));
                listVideoUtil.startPlay(url);
            }
        });
        return convertView;
    }


    class ViewHolder {
        FrameLayout videoContainer;
        ImageView playerBtn;
        ImageView imageView;
        TextView tvName;
    }

    public void setRootView(ViewGroup rootView) {
        this.rootView = rootView;
    }
}
