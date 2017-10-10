package com.joybar.librarycalendar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.joybar.librarycalendar.R;
import com.joybar.librarycalendar.adapter.CalendarGridViewAdapter;
import com.joybar.librarycalendar.controller.CalendarDateController;
import com.joybar.librarycalendar.data.CalendarDate;
import com.joybar.librarycalendar.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;


/**
 * Created by joybar on 4/27/16.
 */
public class CalendarViewFragment extends Fragment {

    private static final String YEAR = "year";
    private static final String TYPE = "type";
    private static final String MONTH = "month";
    private static final String CHOICE_MODE_SINGLE = "choice_mode_single";
    private boolean isChoiceModelSingle;
    private int mYear;
    private int mType;
    private int mMonth;
    private GridView mGridView;
    private List<Integer> weekList = new ArrayList<>();
    private OnDateClickListener onDateClickListener;
    private OnDateCancelListener onDateCancelListener;
    private OnWeekChooseListener onWeekChooseListener;
    private CalendarGridViewAdapter mCalendarGridViewAdapter;

    public CalendarViewFragment() {
    }

    public static CalendarViewFragment newInstance(int year, int month) {
        CalendarViewFragment fragment = new CalendarViewFragment();
        Bundle args = new Bundle();
        args.putInt(YEAR, year);
        args.putInt(MONTH, month);
        fragment.setArguments(args);
        return fragment;
    }

    public static CalendarViewFragment newInstance(int year, int month, boolean isChoiceModelSingle) {
        CalendarViewFragment fragment = new CalendarViewFragment();
        Bundle args = new Bundle();
        args.putInt(YEAR, year);
        args.putInt(MONTH, month);
        args.putBoolean(CHOICE_MODE_SINGLE, isChoiceModelSingle);
        fragment.setArguments(args);
        return fragment;
    }

    public static CalendarViewFragment newInstance(int year, int month, boolean isChoiceModelSingle, List<Integer> weekList) {
        CalendarViewFragment fragment = new CalendarViewFragment();
        Bundle args = new Bundle();
        args.putInt(YEAR, year);
        args.putIntegerArrayList("weekList", (ArrayList<Integer>) weekList);
        args.putInt(MONTH, month);
        args.putBoolean(CHOICE_MODE_SINGLE, isChoiceModelSingle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onDateClickListener = (OnDateClickListener) context;
            if (!isChoiceModelSingle) {
                //多选
                onDateCancelListener = (OnDateCancelListener) context;
            }
            onWeekChooseListener = (OnWeekChooseListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnDateClickListener or OnDateCancelListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mYear = getArguments().getInt(YEAR);
            mMonth = getArguments().getInt(MONTH);
            mType = getArguments().getInt(TYPE);
            isChoiceModelSingle = getArguments().getBoolean(CHOICE_MODE_SINGLE, false);
            if (getArguments().getIntegerArrayList("weekList") != null) {
                weekList = getArguments().getIntegerArrayList("weekList");
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        mGridView = (GridView) view.findViewById(R.id.gv_calendar);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final List<CalendarDate> mListDataCalendar;//日历数据
        mListDataCalendar = CalendarDateController.getCalendarDate(mYear, mMonth);
        mCalendarGridViewAdapter = new CalendarGridViewAdapter(mListDataCalendar);
        mGridView.setAdapter(mCalendarGridViewAdapter);


        final List<CalendarDate> finalMListDataCalendar = mListDataCalendar;
        if (isChoiceModelSingle) {
            mGridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        } else {
            mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        }
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CalendarDate calendarDate = ((CalendarGridViewAdapter) mGridView.getAdapter()).getListData().get(position);
                if (isChoiceModelSingle) {
                    //单选
                    if (finalMListDataCalendar.get(position).isInThisMonth()) {
                        onDateClickListener.onDateClick(calendarDate);
                    } else {
                        mGridView.setItemChecked(position, false);
                    }
                } else {
                    //多选
                    if (finalMListDataCalendar.get(position).isInThisMonth()) {
                        // mGridView.getCheckedItemIds()
                        if (!mGridView.isItemChecked(position)) {
                            onDateCancelListener.onDateCancel(calendarDate);
                        } else {
                            onDateClickListener.onDateClick(calendarDate);
                        }
                    } else {
                        mGridView.setItemChecked(position, false);
                    }

                }
            }
        });
        mGridView.post(new Runnable() {
            @Override
            public void run() {
                //需要默认选中当天
                List<CalendarDate> mListData = ((CalendarGridViewAdapter) mGridView.getAdapter()).getListData();
                int count = mListData.size();
                Matcher m;
                for (int i = 0; i < count; i++) {
                    if (mListData.get(i).getSolar().solarDay == DateUtils.getDay()
                            && mListData.get(i).getSolar().solarMonth == DateUtils.getMonth()
                            && mListData.get(i).getSolar().solarYear == DateUtils.getYear()) {
                        if (null != mGridView.getChildAt(i) && mListData.get(i).isInThisMonth()) {
                            onWeekChooseListener.onWeekChoose(mListData);
                            onDateClickListener.onDateClick(mListData.get(i));
                            mGridView.setItemChecked(i, true);
                        }
                    }
                }
                for (int i = 0; i < count; i++) {
                    if (mListData.get(i).getSolar().solarMonth == DateUtils.getMonth()
                            && mListData.get(i).getSolar().solarYear == DateUtils.getYear()) {
                        if (null != mGridView.getChildAt(i) && mListData.get(i).isInThisMonth()) {
                            String today = mListData.get(i).getSolar().solarYear + "-" + mListData.get(i).getSolar().solarMonth + "-" + mListData.get(i).getSolar().solarDay;
                            for (int k = 0; k < weekList.size(); k++) {
                                switch (k) {
                                    case 0:
                                        if (weekList.get(k) == 1 && DateUtils.getWeekday(today).equals("周日")) {
                                            mListData.get(i).setRemind(true);
                                        }
                                        break;
                                    case 1:
                                        if (weekList.get(k) == 1 && DateUtils.getWeekday(today).equals("周一")) {
                                            mListData.get(i).setRemind(true);
                                        }
                                        break;
                                    case 2:
                                        if (weekList.get(k) == 1 && DateUtils.getWeekday(today).equals("周二")) {
                                            mListData.get(i).setRemind(true);
                                        }
                                        break;
                                    case 3:
                                        if (weekList.get(k) == 1 && DateUtils.getWeekday(today).equals("周三")) {
                                            mListData.get(i).setRemind(true);
                                        }
                                        break;
                                    case 4:
                                        if (weekList.get(k) == 1 && DateUtils.getWeekday(today).equals("周四")) {
                                            mListData.get(i).setRemind(true);
                                        }
                                        break;
                                    case 5:
                                        if (weekList.get(k) == 1 && DateUtils.getWeekday(today).equals("周五")) {
                                            mListData.get(i).setRemind(true);
                                        }
                                        break;
                                    case 6:
                                        if (weekList.get(k) == 1 && DateUtils.getWeekday(today).equals("周六")) {
                                            mListData.get(i).setRemind(true);
                                        }
                                        break;
                                }
                            }
                        }
                    }
                }
                mCalendarGridViewAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            if (null != mGridView) {
                // mGridView.setItemChecked(mCurrentPosition, false);
                mGridView.clearChoices();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface OnDateClickListener {
        void onDateClick(CalendarDate calendarDate);
    }

    public interface OnDateCancelListener {
        void onDateCancel(CalendarDate calendarDate);
    }

    public interface OnWeekChooseListener {
        void onWeekChoose(List<CalendarDate> mListData);
    }
}
