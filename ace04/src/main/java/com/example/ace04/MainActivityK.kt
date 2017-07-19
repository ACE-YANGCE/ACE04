package com.example.ace04

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.example.ace04.KotlinAdapterK.OnItemClickListerner
import kotlinx.android.synthetic.main.activity_recycle.*

class MainActivityK : AppCompatActivity(), OnItemClickListerner {

    // 展开状态：0代表标题栏、1代表Grid列、2代表List列
    val COLUMN_CLASSIFY_TITLE: Int = 0
    val COLUMN_CLASSIFY_GRID: Int = 1
    val COLUMN_CLASSIFY_LIST: Int = 2
    val COLUMN_ITEM_GRID_SIZE: Int = 3
    var dataList: ArrayList<ItemBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)

        initData()

        var gManager: GridLayoutManager = GridLayoutManager(this, COLUMN_ITEM_GRID_SIZE!!)
        gManager.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == 0 || position == 7 || position == 13) {
                    return COLUMN_ITEM_GRID_SIZE!!
                } else {
                    return 1
                }
            }
        })
        main_rv.layoutManager = gManager
        main_rv.adapter = KotlinAdapterK(dataList!!, this, this)
        main_rv.itemAnimator = DefaultItemAnimator()
    }

    override fun OnClassifyItemClick(view: View, position: Int, type: Int) {
        when (type) {
            COLUMN_CLASSIFY_GRID, COLUMN_CLASSIFY_LIST ->
                Toast.makeText(this, dataList?.get(position)?.name, Toast.LENGTH_SHORT).show()
        }
    }

    fun initData() {
        dataList = ArrayList<ItemBean>()
        val bean = ItemBean()
        bean.name = "服务分类"
        bean.sort = 0
        dataList?.add(bean)
        for (i in 0..5) {
            val bean = ItemBean()
            bean.name = "服务子分类" + (i + 1)
            bean.sort = 1
            dataList?.add(bean)
        }
        val bean1 = ItemBean()
        bean1.name = "新闻分类"
        bean1.sort = 0
        dataList?.add(bean1)
        for (i in 0..4) {
            val bean1 = ItemBean()
            bean1.name = "新闻子分类" + (i + 1)
            bean1.sort = 1
            dataList?.add(bean1)
        }
        val bean2 = ItemBean()
        bean2.name = "其他分类"
        bean2.sort = 0
        dataList?.add(bean2)
        for (i in 0..2) {
            val bean2 = ItemBean()
            bean2.name = "其他子分类" + (i + 1)
            bean2.sort = 2
            dataList?.add(bean2)
        }
    }
}
