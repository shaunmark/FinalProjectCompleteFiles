package com.example.finalprojectapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectapp.DataClasses.LogBookData
import kotlinx.android.synthetic.main.table_list_item_logbook.view.*

class LogBookRecyclerViewAdapter(private val logBookList: List<LogBookData>) : RecyclerView.Adapter<LogBookRecyclerViewAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_list_item_logbook, parent, false)
        return RowViewHolder(itemView)
    }

    private fun setHeaderBg(view: View) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg)
    }

    private fun setContentBg(view: View) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val rowPos = holder.adapterPosition

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.itemView.apply {
                setHeaderBg(logPlateNumberText)
                setHeaderBg(logNameText)
                setHeaderBg(logInTimeText)
                setHeaderBg(logRegistered)
                setHeaderBg(logOutDate)
                setHeaderBg(logOutTime)

                logPlateNumberText.text = "Plate No"
                logNameText.text = "Registered"
                logInTimeText.text = "IN Date"
                logRegistered.text = "IN Time"
                logOutDate.text = "OUT Date"
                logOutTime.text = "OUT Time"

            }
        } else {
            val modal = logBookList[rowPos - 1]

            holder.itemView.apply {
                setContentBg(logPlateNumberText)
                setContentBg(logNameText)
                setContentBg(logInTimeText)
                setContentBg(logRegistered)
                setContentBg(logOutDate)
                setContentBg(logOutTime)
                //deleted toString()
                logPlateNumberText.text = modal.Plate_Number
                logNameText.text = modal.Registered
                logInTimeText.text = modal.IN_Date
                logRegistered.text = modal.IN_Time
                if(modal.OUT_Date != null) {
                    logOutDate.text = modal.OUT_Date.toString()
                    logOutTime.text = modal.OUT_Time.toString()
                }
                else
                {
                    logOutDate.text = " ".toString()
                    logOutTime.text = " ".toString()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return logBookList.size + 1 // one more to add header row
    }

    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}