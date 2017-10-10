package com.yusong.club.ui.school.teacher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.school.teacher.bean.StudyVideo;

import org.apache.commons.lang.StringUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 视频介绍
 */
public class IntroduceFragment extends BaseFragment {
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_grade)
    TextView tvGrade;
    @InjectView(R.id.tv_type)
    TextView tvType;
    @InjectView(R.id.tv_score)
    TextView tvScore;
    @InjectView(R.id.tv_teacher)
    TextView tvTeacher;
    private StudyVideo studyVideo = null;

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_teacher_introduce, null);
    }

    @Override
    public void initData() {
        if (getArguments() != null && getArguments().getSerializable("studyVideo") != null) {
            studyVideo = (StudyVideo) getArguments().getSerializable("studyVideo");
            if (!StringUtils.isEmpty(studyVideo.getVideoName())) {
                tvTitle.setText("视频名称:\t" + studyVideo.getVideoName());
            } else {
                tvTitle.setText("视频名称:\t" + "暂无");
            }
            if (!StringUtils.isEmpty(studyVideo.getSubjectName())) {
                tvGrade.setText("课程名称:\t" + studyVideo.getSubjectName());
            } else {
                tvGrade.setText("课程名称:\t" + "暂无");
            }
            if (!StringUtils.isEmpty(studyVideo.getTeacherName())) {
                tvTeacher.setText("老师:\t" + studyVideo.getTeacherName());
            } else {
                tvTeacher.setText("老师:\t" + "暂无");
            }
            tvType.setText("分类:\t" + "教学视频");
            tvScore.setText(studyVideo.getScore() + "");
            tvContent.setText(studyVideo.getMemo());
        }
//        tvContent.setText("练一练教学视频是将教师要传授给学生的知识、技能等\n内容制作成视频形式，以辅助现代化多媒体教学。即帮\n助老师能够更加生动，形象的展现出在课堂上无法实际\n操作的内容，同时又真实的记录了教学内容，方便学习\n者能够随时随地反复学习，是现代化教学中必不可少的\n重要辅助工具。许多工科院校都频繁使用教学视频，以\n给同学们展示实际的施工过程，效果颇佳。");
    }

    @Override
    public void initListener() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
