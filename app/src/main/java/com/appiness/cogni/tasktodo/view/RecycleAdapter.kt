package com.appiness.cogni.tasktodo.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.appiness.cogni.tasktodo.R
import com.appiness.cogni.tasktodo.model.ApiResponse
import com.appiness.cogni.tasktodo.model.RowResponse

class RecycleAdapter(private var context: Context, private var responseList: List<RowResponse>) :
    RecyclerView.Adapter<RecycleAdapter.MViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_row, parent, false)

        return MViewHolder(view)
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val _row = responseList[position]
        //render

        holder.textViewititle.text = _row.title
        holder.description.text = _row.description
    }

    fun update(data: List<RowResponse>) {
        responseList = data
        notifyDataSetChanged()
    }


    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewititle: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val image: ImageView = view.findViewById(R.id.imageView)
    }
}