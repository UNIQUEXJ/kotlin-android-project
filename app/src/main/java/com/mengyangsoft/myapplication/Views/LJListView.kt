package com.mengyangsoft.myapplication.Views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.widget.AdapterView
import android.widget.ListView

class LJListView : ListView {
    lateinit var clickItem: (Int) -> Unit
    lateinit var removeItem: (Int) -> Unit

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @SuppressLint("NewApi")
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            clickItem(position)
        }
        onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, position, _ ->
            val builder = AlertDialog.Builder(this.context)
            builder.setMessage("delete?")
            builder.setTitle("tip")
            builder.setPositiveButton("ok", { _, _ ->
                removeItem(position)
            })
            builder.setNegativeButton("cancel", {_, _ ->})
            builder.create().show()
            true
        }
    }
}
