package cn.neoclub.rxjavademo.ui;

import android.support.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.neoclub.rxjavademo.R;
import cn.neoclub.rxjavademo.base.SimpleActivity;
import cn.neoclub.rxjavademo.model.event.RxBus;
import cn.neoclub.rxjavademo.util.DateUtil;

/**
 * Created by renjialiang on 16/12/2.
 */
public class CalendarActivity extends SimpleActivity {

    @BindView(R.id.view_calender)
    MaterialCalendarView viewCalender;

    CalendarDay mDate;

    @Override
    protected int getLayout() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initEventAndData() {
        viewCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        viewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });
    }

    @OnClick(R.id.tv_calender_enter)
    void onClickOk() {
        RxBus.getDefault().post(mDate);
        finish();
    }
}
