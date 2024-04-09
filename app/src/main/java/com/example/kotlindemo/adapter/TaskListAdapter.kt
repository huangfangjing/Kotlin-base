package com.example.kotlindemo.adapter

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import com.example.kotlindemo.R
import com.example.kotlindemo.adapter.base.BaseRecyclerAdapter
import com.example.kotlindemo.adapter.base.BaseViewHolder
import com.example.kotlindemo.databinding.AdapterTaskListItemBinding
import com.example.kotlindemo.model.entity.Task
import com.example.kotlindemo.utils.DatetimeUtil

class TaskListAdapter(
    context: Context?,
    dataList: List<Task>,
    layoutId: Int,
    click: OnClickListener?
) :
    BaseRecyclerAdapter<Task>(context, dataList, layoutId, click) {

    lateinit var mItemBinding: AdapterTaskListItemBinding

    fun getCurrentSubjectName(): String {
        return "数学"
    }

    override fun setItemData(holder: BaseViewHolder, position: Int, task: Task) {
        mItemBinding = holder.binding as AdapterTaskListItemBinding

        mItemBinding.title.setText(task.getJobname())
        mItemBinding.item.setTag(task)
        mItemBinding.item.setOnClickListener(mOnClickListener)

        if (task.getPeriodType() != null && !task.getPeriodType().isEmpty()) {
            when (task.getPeriodType()) {
                "b" -> mItemBinding.period.setText("课前")
                "i" -> mItemBinding.period.setText("课中")
                "a" -> mItemBinding.period.setText("课后")
            }
        }

        if (task.getState() != null && !task.getState().isEmpty()) {
            if (task.getState().equals("ing")) {
                mItemBinding.paperState.setText("进行中")
                mItemBinding.paperState.setTextColor(mContext.resources.getColor(R.color.green_new))
            } else if (task.getState().equals("end")) {
                mItemBinding.paperState.setText("已结束")
                mItemBinding.paperState.setTextColor(mContext.resources.getColor(R.color.grey_new))
            } else if (task.getState().equals("noBegin")) {
                mItemBinding.paperState.setText("未开始")
                mItemBinding.paperState.setTextColor(mContext.resources.getColor(R.color.grey_new))
            }
        }
        if (!TextUtils.isEmpty(task.interType) && task.interType.equals("r")) {
            mItemBinding.interType.setVisibility(View.VISIBLE)
        } else {
            mItemBinding.interType.setVisibility(View.GONE)
        }

        mItemBinding.subjectName.setText(getCurrentSubjectName())
        if (getCurrentSubjectName() != null) {
            when (getCurrentSubjectName()) {
                "数学" -> {
                    mItemBinding.subjectName.setTextColor(-0xc46f01)
                    mItemBinding.subjectName.setBackgroundResource(R.drawable.task_list_subject_bg_shuxue)
                }
                "英语" -> {
                    mItemBinding.subjectName.setTextColor(-0x89c9d)
                    mItemBinding.subjectName.setBackgroundResource(R.drawable.task_list_subject_bg_yingyu)
                }
                else -> {
                    mItemBinding.subjectName.setTextColor(-0xd94cb7)
                    mItemBinding.subjectName.setBackgroundResource(R.drawable.task_list_subject_bg_yuwen)
                }
            }
        }
        if ("t" == task.getType() || "e" == task.getType()) {
            mItemBinding.correctLayout.setVisibility(View.VISIBLE)
        } else {
            mItemBinding.correctLayout.setVisibility(View.GONE)
        }
        if (task.getType() != null) {
            when (task.getType()) {
                "t" -> {
                    mItemBinding.type.setText("练习")
                    mItemBinding.type.setTextColor(Color.WHITE)
                    mItemBinding.type.setBackgroundResource(R.drawable.shape_rectangle_round_corner_message_type_weike)
                }
                "e" -> {
                    mItemBinding.type.setText("考试")
                    mItemBinding.type.setTextColor(Color.WHITE)
                    mItemBinding.type.setBackgroundResource(R.drawable.shape_rectangle_round_corner_message_type_weike)
                }
            }
            if (!TextUtils.isEmpty(task.interType) && task.interType.equals("r")) {
                //朗读
                mItemBinding.type.setVisibility(View.GONE)
            } else {
                mItemBinding.type.setVisibility(View.VISIBLE)
            }
        }
        if (!TextUtils.isEmpty(task.interType) && task.interType.equals("r")) {
            mItemBinding.correntNum.setText("已检查：" + task.getCorrectNum())
            mItemBinding.uncorrectNum.setText("未检查：" + task.getUnCorrectNum())
        } else {
            mItemBinding.correntNum.setText("已批改：" + task.getCorrectNum())
            mItemBinding.uncorrectNum.setText("未批改：" + task.getUnCorrectNum())
        }

        mItemBinding.endTime.setText(
            String.format(
                "%s %s开始  |  %s %s结束",
                DatetimeUtil.toTimeBySingleMessage2(task.getStart_time()),
                DatetimeUtil.getFormatDatetime2(task.getStart_time(), "H:mm"),
                DatetimeUtil.toTimeBySingleMessage2(task.getEnd_time()),
                DatetimeUtil.getFormatDatetime2(task.getEnd_time(), "H:mm")
            )
        )
        mItemBinding.dayTime.setText(
            String.format(
                "%s  %s",
                DatetimeUtil.getFormatDatetime2(task.getCreate_time(), "M月dd日"),
                DatetimeUtil.toHMWeekday(task.getCreate_time())
            )
        )

        if (position != 0 && DatetimeUtil.getFormatDatetime2(
                mDataList[position].create_time,
                "M-dd"
            )
                .equals(
                    DatetimeUtil.getFormatDatetime2(
                        mDataList[position - 1].create_time,
                        "M-dd"
                    )
                )
        ) {
            mItemBinding.dayTime.setVisibility(View.GONE)
            mItemBinding.line.setVisibility(View.VISIBLE)
        } else {
            mItemBinding.dayTime.setVisibility(View.VISIBLE)
            mItemBinding.line.setVisibility(View.GONE)
        }
    }
}