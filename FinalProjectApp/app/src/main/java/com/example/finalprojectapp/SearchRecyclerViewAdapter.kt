package com.example.finalprojectapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectapp.DataClasses.SearchResult
import kotlinx.android.synthetic.main.table_list_item_search.view.*

class SearchRecyclerViewAdapter(private val searchResult: List<SearchResult>) : RecyclerView.Adapter<SearchRecyclerViewAdapter.RowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_list_item_search, parent, false)
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
                setHeaderBg(deleteVehicleText)
                //setHeaderBg(searchNameText)
                setHeaderBg(searchInDateText)
                setHeaderBg(searchInTimeText)
                setHeaderBg(searchLogOutDate)
                setHeaderBg(searchLogOutTime)

                deleteVehicleText.text = "Plate No"
                //searchNameText.text = "Name"
                searchInDateText.text = "IN Date"
                searchInTimeText.text = "IN Time"
                searchLogOutDate.text = "OUT Date"
                searchLogOutTime.text = "OUT Time"
            }
        } else {
            val modal = searchResult[rowPos - 1]

            holder.itemView.apply {
                setContentBg(deleteVehicleText)
                //setContentBg(searchNameText)
                setContentBg(searchInDateText)
                setContentBg(searchInTimeText)
                setContentBg(searchLogOutDate)
                setContentBg(searchLogOutTime)
                //deleted toString()
                deleteVehicleText.text = modal.Plate_Number
                //searchNameText.text = "Poori"
                searchInDateText.text = modal.IN_Date
                searchInTimeText.text = modal.IN_Time
                searchLogOutDate.text = modal.OUT_Date
                searchLogOutTime.text = modal.OUT_Time
            }
        }
    }

    override fun getItemCount(): Int {
        return searchResult.size + 1 // one more to add header row
    }

    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}