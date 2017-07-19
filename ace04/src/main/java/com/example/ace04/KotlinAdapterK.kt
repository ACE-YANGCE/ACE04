package com.example.ace04

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * Created by ace on 2017/7/19 0019.
 */
class KotlinAdapterK(val list: ArrayList<ItemBean>, val context: Context, val listener: OnItemClickListerner) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 展开状态：0代表标题栏、1代表Grid列、2代表List列
    val COLUMN_CLASSIFY_TITLE: Int = 0
    val COLUMN_CLASSIFY_GRID: Int = 1
    val COLUMN_CLASSIFY_LIST: Int = 2
    val inflater: LayoutInflater? = LayoutInflater.from(context)

    interface OnItemClickListerner {
        fun OnClassifyItemClick(view: View, position: Int, type: Int)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            COLUMN_CLASSIFY_TITLE -> {
                val viewHolderTitle = holder as VHTitle
                viewHolderTitle.titleTv.text = list?.get(position).name
                if (listener != null) {
                    viewHolderTitle.titleLay.setOnClickListener {
                        listener?.OnClassifyItemClick(viewHolderTitle.itemView, position, COLUMN_CLASSIFY_TITLE)
                    }
                }
            }
            COLUMN_CLASSIFY_GRID -> {
                val viewHolderItem = holder as VHGrid
                viewHolderItem.itemTv.text = list?.get(position).name
                Glide.with(context).load(R.mipmap.ic_bottom_music)
                        .crossFade().centerCrop().into(viewHolderItem.itemIv)
                if (listener != null) {
                    viewHolderItem.itemLay.setOnClickListener {
                        listener?.OnClassifyItemClick(viewHolderItem.itemView, position, COLUMN_CLASSIFY_GRID)
                    }
                }
            }
            COLUMN_CLASSIFY_LIST -> {
                val viewHolderItem = holder as VHGrid
                viewHolderItem.itemTv.text = list?.get(position).name
                Glide.with(context).load(R.mipmap.ic_bottom_navigation)
                        .crossFade().centerCrop().into(viewHolderItem.itemIv)
                if (listener != null) {
                    viewHolderItem.itemLay.setOnClickListener {
                        listener?.OnClassifyItemClick(viewHolderItem.itemView, position, COLUMN_CLASSIFY_LIST)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        var view: View
        var viewHolder: RecyclerView.ViewHolder? = null
        if (COLUMN_CLASSIFY_TITLE == viewType) {
            view = inflater!!.inflate(R.layout.recycle_item_title, parent, false)
            viewHolder = VHTitle(view)
        } else if (COLUMN_CLASSIFY_GRID == viewType) {
            view = inflater!!.inflate(R.layout.recycle_item_grid, parent, false)
            viewHolder = VHGrid(view)
        } else if (COLUMN_CLASSIFY_LIST == viewType) {
            view = inflater!!.inflate(R.layout.recycle_item_list, parent, false)
            viewHolder = VHGrid(view)
        }
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        var viewType = COLUMN_CLASSIFY_TITLE
        if (list?.get(position).sort == 0)
            viewType = COLUMN_CLASSIFY_TITLE
        if (list?.get(position).sort == 1)
            viewType = COLUMN_CLASSIFY_GRID
        if (list?.get(position).sort == 2)
            viewType = COLUMN_CLASSIFY_LIST
        return viewType
    }
}

class VHTitle(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView!!.findViewById(R.id.service_column_title_tv) as TextView
    val titleLay: LinearLayout = itemView!!.findViewById(R.id.service_column_title_lay) as LinearLayout
}

class VHGrid(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemTv: TextView = itemView!!.findViewById(R.id.service_column_item_tv) as TextView
    val itemIv: ImageView = itemView!!.findViewById(R.id.service_column_item_iv) as ImageView
    val itemLay: LinearLayout = itemView!!.findViewById(R.id.service_column_item_lay) as LinearLayout
}