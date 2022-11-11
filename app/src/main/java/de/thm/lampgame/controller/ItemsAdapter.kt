package de.thm.lampgame.controller


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.thm.lampgame.model.Items
import de.thm.lampgame.R

class ItemsAdapter(
    private val exampleList: List<Items>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ItemsAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shopview_item_layout,
            parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.itemImage)
        holder.textView1.text = currentItem.itemName
    }

    override fun getItemCount() = exampleList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView1: TextView = itemView.findViewById(R.id.textView)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
