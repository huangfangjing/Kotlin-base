package com.example.kotlindemo.view.activity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlindemo.R
import com.example.kotlindemo.base.DatabindingActivity
import com.example.kotlindemo.databinding.ActivityMainBinding
import com.example.kotlindemo.model.natives.Tab
import com.example.kotlindemo.view.fragment.TaskListFragment
import com.example.kotlindemo.widget.FragmentTabHost

class MainActivity : DatabindingActivity<ActivityMainBinding>() {

    private var tabHost: FragmentTabHost? = null
    private var tabs: ArrayList<Tab>? = null

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        tabHost = mContentView.tabhost
        tabHost!!.setup(this, supportFragmentManager, R.id.realtabcontent)

        val teach =
            Tab(TaskListFragment::class.java, "教学", R.drawable.selector_bottom_tab_pager_teach)
        val mine = Tab(TaskListFragment::class.java, "我的", R.drawable.selector_bottom_tab_tmine)
        tabs = ArrayList()
        tabs!!.add(teach)
        tabs!!.add(mine)

        for (tab in tabs!!) {
            //新建4个TabSpec，并且设置好它的Indicator
            val view = View.inflate(this, R.layout.tab_indicator, null)
            val tabSpec = tabHost!!.newTabSpec(tab.text)
            val tvTabTtxt = view.findViewById<TextView>(R.id.txt_indicator)
            tvTabTtxt.text = tab.text
            val iconTab = view.findViewById<ImageView>(R.id.icon_tab)
            iconTab.setImageResource(tab.icon)
            tabSpec.setIndicator(view)
            //把每个TabSpec添加到FragmentTabHost里面
            tabHost!!.addTab(tabSpec, tab.fragment, null)
        }

    }
}