package com.yusong.community.utils.tree;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yusong.community.R;

import java.util.List;

/**
 * @author Mr_Peng
 * created at 2017/6/1 9:50.
 */
public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T> {


    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
    }


    @Override
    public View getConvertView(Node node, final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.parent_group_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) convertView
                    .findViewById(R.id.icon_address);
            viewHolder.label = (TextView) convertView
                    .findViewById(R.id.group_name);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (node.getIcon() == -1)
        {
         //   viewHolder.icon.setVisibility(View.INVISIBLE);
            viewHolder.icon.setImageResource(R.mipmap.default_user_icon);
        } else
        {
         //   viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }
        if(node.getMobile()!=null&&node.getMobile().length()>0){
            viewHolder.label.setText(node.getName()+" <"+node.getMobile()+">");
        }else{
            viewHolder.label.setText(node.getName());
        }

//        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandOrCollapse(position);
//            }
//        });

        return convertView;
    }

    private final class ViewHolder
    {
        ImageView icon;
        TextView label;
    }
}
