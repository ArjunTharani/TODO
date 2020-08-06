package com.techolution.assignment.ui.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.techolution.assignment.R
import com.techolution.assignment.db.entities.TodoItem

class TodoItemsAdapter(var itemClickListner: (Int) -> Unit) :
    ListAdapter<TodoItem, RecyclerView.ViewHolder>(DiffCallback()) {
    class DiffCallback : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return (oldItem.title == newItem.title && oldItem.description == newItem.description)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TodoSingleItem(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false),
            itemClickListner
        )
    }

    class TodoSingleItem(
        itemView: View?,
        var itemClickListner: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView!!) {
        fun onBind(
            currentList: MutableList<TodoItem>,
            position: Int
        ) {
            val taskName = itemView.findViewById<TextView>(R.id.task)
            val taskStatus = itemView.findViewById<ImageView>(R.id.task_status)
            taskName.text = currentList[position].title
            if(currentList[position].isChecked){
                taskStatus.setImageResource(R.drawable.ic_baseline_check_24)
            }
            itemView.setOnClickListener { itemClickListner(currentList[position].id) }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TodoSingleItem).onBind(currentList,position)
    }

    fun getTodoAt(adapterPosition: Int): TodoItem {
        return getItem(adapterPosition)
    }

}
