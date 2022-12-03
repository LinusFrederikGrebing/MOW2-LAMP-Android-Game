package de.thm.lampgame.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.thm.lampgame.databinding.ActivedesignBinding
import de.thm.lampgame.databinding.LockeddesignBinding
import de.thm.lampgame.databinding.UnlockeddesignBinding
import de.thm.lampgame.model.shop.DataItem
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.Database.ACTIVE_TYPE
import de.thm.lampgame.model.shop.Database.LOCKED_TYPE
import de.thm.lampgame.model.shop.Database.UNLOCKED_TYPE


class ItemsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // depending on the active shop, a different item list should be loaded from the ShopActivity
    companion object {
        var itemList = Database.getItemsMaps()
    }

    // creates a viewHolder for all three cases - Locked, Unlocked & Active
    class ActiveViewHolder(
        private val activeBinding: ActivedesignBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(activeBinding.root),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: DataItem.Active) {
            activeBinding.active = item
        }

        override fun onClick(v: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    class LockedViewHolder(
        private val lockedBinding: LockeddesignBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(lockedBinding.root),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: DataItem.Locked) {
            lockedBinding.locked = item
        }

        override fun onClick(v: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    class UnlockedViewHolder(
        private val unlockedBinding: UnlockeddesignBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(unlockedBinding.root),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: DataItem.Unlocked) {
            unlockedBinding.unlocked = item
        }

        override fun onClick(v: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            UNLOCKED_TYPE -> LockedViewHolder(
                LockeddesignBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener
            )
            LOCKED_TYPE -> UnlockedViewHolder(
                UnlockeddesignBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener
            )
            ACTIVE_TYPE -> ActiveViewHolder(
                ActivedesignBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener
            )
            else -> throw java.lang.IllegalArgumentException("invalid test type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LockedViewHolder -> holder.bind(itemList[position] as DataItem.Locked)
            is UnlockedViewHolder -> holder.bind(itemList[position] as DataItem.Unlocked)
            is ActiveViewHolder -> holder.bind(itemList[position] as DataItem.Active)
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is DataItem.Locked -> UNLOCKED_TYPE
            is DataItem.Unlocked -> LOCKED_TYPE
            is DataItem.Active -> ACTIVE_TYPE
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

