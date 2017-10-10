package com.yusong.club.ui.community.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.community.activity.HaveCommunityCityActivity;
import com.yusong.club.ui.community.activity.NearbyCommuityActivity;
import com.yusong.club.ui.community.mvp.entity.HaveCommunityCity;

import java.util.List;

/**
 * Created by kun on 2016/10/26.
 */
public class CitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<HaveCommunityCity> cities;

    private final int HEAD = 0;
    private final int WORD = 1;
    private final int CITY = 2;

    public CitiesAdapter(Context context, List<HaveCommunityCity> cities){
        this.mContext = context;
        this.cities = cities;
    }

    public List<HaveCommunityCity> getData() {
        return cities;
    }

    @Override
    public int getItemCount() {
        int count = 1;
        if(cities==null || cities.size()==0) return count;
        count +=cities.size();
        for(HaveCommunityCity datasBean:cities){
            count += datasBean.getList().size();
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int count = 0;
        if(position==count) return HEAD;//下标为0的固定显示头部布局。

        for(int i = 0; i < cities.size(); i++){
            count++;
            if(position==count){
                return WORD;
            }
            List<HaveCommunityCity.ListBean> addressList = cities.get(i).getList();
            for(int j =0;j<addressList.size();j++){
                count++;
                if(position==count){
                    return CITY;
                }
            }
        }
        return super.getItemViewType(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case HEAD:
                View head = LayoutInflater.from(mContext).inflate(R.layout.layout_head_havecommunitycity, parent, false);
                return new HeadViewHolder(head);
            case WORD:
                View word = LayoutInflater.from(mContext).inflate(R.layout.item_havecommunitycity_word, parent, false);
                return new WordViewHolder(word);
            case CITY:
                View city = LayoutInflater.from(mContext).inflate(R.layout.item_havecommunitycity_city, parent, false);
                return new CityViewHolder(city);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HaveCommunityCityActivity activity = (HaveCommunityCityActivity) mContext;
        int count = 0;
        if(position==count){
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            headViewHolder.btnLocationCity.setText(activity.cityname);
            headViewHolder.btnLocationCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(mContext, NearbyCommuityActivity.class);
                    data.putExtra("cityName","");
                    data.putExtra("cityCode",0);
                    activity.setResult(Activity.RESULT_OK,data);
                    activity.finish();
                }
            });
        }
        for(int i = 0; i < cities.size(); i++){
            count++;
            if(position==count){
                WordViewHolder wordViewHolder = (WordViewHolder) holder;
                HaveCommunityCity datasBean = cities.get(i);
                wordViewHolder.textWord.setText(datasBean.getLetter());
            }
            List<HaveCommunityCity.ListBean> addressList = cities.get(i).getList();
            for(int j =0;j<addressList.size();j++){
                count++;
                if(position==count){
                    CityViewHolder cityViewHolder = (CityViewHolder) holder;
                    final HaveCommunityCity.ListBean addressListBean = addressList.get(j);
                    cityViewHolder.textCity.setText(addressListBean.getAreaName());
                    cityViewHolder.textCity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent data = new Intent(mContext, NearbyCommuityActivity.class);
                            data.putExtra("cityName",addressListBean.getAreaName());
                            data.putExtra("areaId",addressListBean.getId());
                            activity.setResult(Activity.RESULT_OK,data);
                            activity.finish();
                        }
                    });
                }
            }
        }

    }


    public static class HeadViewHolder extends RecyclerView.ViewHolder {

        Button btnLocationCity;

        public HeadViewHolder(View view) {
            super(view);
            btnLocationCity = (Button) view.findViewById(R.id.btn_location_city);
        }
    }
    public static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView textWord;
        public WordViewHolder(View view) {
            super(view);
            textWord = (TextView) view.findViewById(R.id.textWord);
        }
    }
    public static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView textCity;
        public CityViewHolder(View view) {
            super(view);
            textCity = (TextView) view.findViewById(R.id.textCity);

        }
    }
}
