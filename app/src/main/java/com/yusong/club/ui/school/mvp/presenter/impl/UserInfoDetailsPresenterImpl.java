package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.SchoolManager;
import com.yusong.club.ui.school.mvp.entity.UserInfoDetails;
import com.yusong.club.ui.school.mvp.implView.IUserInfoDetailsView;
import com.yusong.club.utils.ActivityConstants;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * ***********************************************
 * **                ┏┓   ┏┓                    **
 * **                ┏┛┻━━━┛┻┓                  **
 * **                ┃       ┃                  **
 * **                ┃   ━   ┃                  **
 * **                ┃ ┳┛ ┗┳ ┃                  **
 * **                ┃       ┃                  **
 * **                ┃   ┻   ┃                  **
 * **                ┃       ┃                  **
 * **                ┗━┓   ┏━┛                  **
 * **                  ┃   ┃                    **
 * **                  ┃   ┃                    **
 * **                  ┃   ┗━━━┓                **
 * **                  ┃       ┣┓               **
 * **                  ┃       ┏┛               **
 * **                  ┗┓┓┏━┳┓┏┛                **
 * **                   ┃┫┫ ┃┫┫                 **
 * **                  ┗┻┛ ┗┻┛                  **
 * ***********************************************
 * **               神兽助我 扬我神威              **
 * ***********************************************
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/22 09:44 </li>
 * </ul>
 */
public class UserInfoDetailsPresenterImpl extends BasePresenterImpl<IUserInfoDetailsView> {

    public UserInfoDetailsPresenterImpl(IUserInfoDetailsView v, Context mContext) {
        super(v, mContext);
    }

    public void queryInfoDetails(int type, int id, String token) {


        if (type == ActivityConstants.Teachar_TYPE) {
            //查询老师
            queryTeacharInfo(token, id);
        } else if (type == ActivityConstants.Parent_TYPE) {
            //查询家长
            queryParentInfo(token, id);
        } else if (type == ActivityConstants.Student_TYPE) {
            //查询学生
            queryStudentInfo(token, id);
        } else if (type == ActivityConstants.Admin_TYPE) {
            queryAdmin(token, id);
        }


    }

    private void queryAdmin(String token, int id) {
        Subscription subscription = HttpUtil.getInstance().querySchoolManagerInfo(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<SchoolManager>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<SchoolManager> result) {
                        mView.showAdminInfo(result.data);
                    }
                });
        addSubcribe(subscription);
    }

    private void queryStudentInfo(String token, int id) {

        Subscription subscription = HttpUtil.getInstance().queryStudentInfo(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<UserInfoDetails>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<UserInfoDetails> result) {

                        mView.showStudentInfo(result.data);
                    }
                });
        addSubcribe(subscription);
    }

    private void queryParentInfo(String token, Integer id) {

        Subscription subscription = HttpUtil.getInstance().queryParentInfo(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<UserInfoDetails>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<UserInfoDetails> result) {

                        mView.showParentInfo(result.data);

                    }
                });
        addSubcribe(subscription);
    }

    private void queryTeacharInfo(String token, Integer id) {

        Subscription subscription = HttpUtil.getInstance().queryTeacharInfo(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<UserInfoDetails>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<UserInfoDetails> result) {
                        mView.showTeacharInfo(result.data);
                    }
                });
        addSubcribe(subscription);
    }
}