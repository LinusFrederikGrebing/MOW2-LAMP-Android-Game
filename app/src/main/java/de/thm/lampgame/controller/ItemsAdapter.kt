package de.thm.lampgame.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.thm.lampgame.model.DataItem
import de.thm.lampgame.model.Database.ACTIVE_TYPE
import de.thm.lampgame.model.Database.LOCKED_TYPE
import de.thm.lampgame.model.Database.UNLOCKED_TYPE
import de.thm.lampgame.databinding.ActivedesignBinding
import de.thm.lampgame.databinding.LockeddesignBinding
import de.thm.lampgame.databinding.UnlockeddesignBinding


class ItemsAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class ActiveViewHolder(val activeBinding: ActivedesignBinding, val listener : OnItemClickListener) : RecyclerView.ViewHolder(activeBinding.root),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: DataItem.Active){
            activeBinding.active = item
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    class LockedViewHolder(val lockedBinding: LockeddesignBinding, val listener : OnItemClickListener) : RecyclerView.ViewHolder(lockedBinding.root),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: DataItem.Locked){
            lockedBinding.locked = item
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

     class UnlockedViewHolder(val unlockedBinding: UnlockeddesignBinding, val listener : OnItemClickListener) : RecyclerView.ViewHolder(unlockedBinding.root),
         View.OnClickListener {
         init {
             itemView.setOnClickListener(this)
         }

         fun bind(item: DataItem.Unlocked){
             unlockedBinding.unlocked = item
         }

         override fun onClick(v: View?) {
             val position = adapterPosition
             if (position != RecyclerView.NO_POSITION) {
                 listener.onItemClick(position)
             }
         }
    }



    private val itemList = arrayListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            UNLOCKED_TYPE -> LockedViewHolder(LockeddesignBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener)
            LOCKED_TYPE -> UnlockedViewHolder(UnlockeddesignBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener)
            ACTIVE_TYPE -> ActiveViewHolder(ActivedesignBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener)
            else -> throw java.lang.IllegalArgumentException("invalid test type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LockedViewHolder -> holder.bind(itemList[position] as DataItem.Locked)
            is UnlockedViewHolder -> holder.bind(itemList[position] as DataItem.Unlocked)
            is ActiveViewHolder -> holder.bind(itemList[position] as DataItem.Active)
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position]){
            is DataItem.Locked -> UNLOCKED_TYPE
            is DataItem.Unlocked -> LOCKED_TYPE
            is DataItem.Active -> ACTIVE_TYPE
            else -> throw IllegalArgumentException("invalid type")
        }
    }
    fun updateList(updatedList: List<Any>){
        itemList.clear()
        itemList.addAll(updatedList)
        notifyDataSetChanged()
    }
    interface OnItemClickListener {

        fun onItemClick(position: Int)
    }
}


/*


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
*/