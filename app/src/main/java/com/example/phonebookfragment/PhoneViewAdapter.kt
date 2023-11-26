package com.example.phonebookfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView


class PhoneViewAdapter(val items: ArrayList<PhoneModel>,val listener: PhoneViewAdapter.ItemClickListener?): BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    interface ItemClickListener {
        fun ItemClick(position: Int)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var itemView: View
        var itemViewHolder: ItemViewHolder

        if (convertView == null) {
            itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.phone_item, parent, false)

            itemViewHolder = ItemViewHolder(itemView)
            itemView.tag = itemViewHolder
        } else {
            itemView = convertView
            itemViewHolder = itemView.tag as ItemViewHolder
        }

        itemViewHolder.phoneName.text=items[position].name
        val firstLetter:String= items[position].name[0].toString()
        itemViewHolder.phoneImage.text=firstLetter

        return itemView
    }

    inner class ItemViewHolder(itemView: View) {

            val phoneName= itemView.findViewById<TextView>(R.id.text_name)
            val phoneImage= itemView.findViewById<TextView>(R.id.text_avatar)




    }
}