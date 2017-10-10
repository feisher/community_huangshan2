package com.joybar.librarycalendar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.joybar.librarycalendar.R;
import com.joybar.librarycalendar.adapter.CalendarViewPagerAdapter;
import com.joybar.librarycalendar.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by joybar on 4/28/16.
 */
public class CalendarViewPagerFragment extends Fragment {
    private static final String CHOICE_MODE_SINGLE = "choice_mode_single";
    private boolean isChoiceModelSingle;
    private ViewPager viewPager;
    private Button nextButton;
    private Button previousButton;
    private TextView tvDate;
    private OnPageChangeListener onPageChangeListener;
    List<Integer> weekList=new ArrayList<>();
    public CalendarViewPagerFragment() {
    }

    public static CalendarViewPagerFragment newInstance(boolean isChoiceModelSingle) {
        CalendarViewPagerFragment fragment = new CalendarViewPagerFragment();
        Bundle args = new Bundle();
        args.putBoolean(CHOICE_MODE_SINGLE, isChoiceModelSingle);
        fragment.setArguments(args);
        return fragment;
    }
    public static CalendarViewPagerFragment newInstance(boolean isChoiceModelSingle,List<Integer> weekList) {
        CalendarViewPagerFragment fragment = new CalendarViewPagerFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList("weekList", (ArrayList<Integer>) weekList);
        args.putBoolean(CHOICE_MODE_SINGLE, isChoiceModelSingle);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onPageChangeListener = (OnPageChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnDateClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isChoiceModelSingle = getArguments().getBoolean(CHOICE_MODE_SINGLE, false);
            if (getArguments().getIntegerArrayList("weekList")!=null){
                weekList=getArguments().getIntegerArrayList("weekList");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_viewpager, container, false);
        initViewPager(view);
        return view;
    }

    private void initViewPager(View view) {
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        nextButton = (Button) view.findViewById(R.id.next_month_btn);
        previousButton = (Button) view.findViewById(R.id.previous_month_btn);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        tvDate.setText(DateUtils.getYear() + "年" + DateUtils.getMonth() + "月");
        final CalendarViewPagerAdapter myAdapter = new CalendarViewPagerAdapter(getChildFragmentManager(), isChoiceModelSingle,weekList);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(CalendarViewPagerAdapter.NUM_ITEMS_CURRENT);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int year = myAdapter.getYearByPosition(position);
                int month = myAdapter.getMonthByPosition(position);
                tvDate.setText(year + "年" + month + "月");
                onPageChangeListener.onPageChange(year, month);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });
    }

    public interface OnPageChangeListener {
        void onPageChange(int year, int month);
    }
}
