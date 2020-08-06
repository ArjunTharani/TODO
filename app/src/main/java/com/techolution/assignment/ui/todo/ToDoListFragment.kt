package com.techolution.assignment.ui.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.techolution.assignment.R
import com.techolution.assignment.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToDoListFragment : DaggerFragment() {

    private lateinit var todoAdapter: TodoItemsAdapter
    private lateinit var todoViewModel: ToDoListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var todoRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoViewModel = ViewModelProvider(this, viewModelFactory).get(ToDoListViewModel::class.java)
        todoRecyclerView = view.findViewById(R.id.toDoRecyclerview)
        todoAdapter = TodoItemsAdapter { id ->
            findNavController().navigate(R.id.nav_detail, Bundle().apply {
                putString("item_id","$id")
            })
        }
        todoRecyclerView.adapter = todoAdapter
        todoViewModel.getTodoItemsList().observe(viewLifecycleOwner, Observer {
            todoAdapter.submitList(it)
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewLifecycleOwner.lifecycleScope.launch {
                    todoViewModel.deleteTodoItem(todoAdapter.getTodoAt(viewHolder.adapterPosition))
                }
                Toast.makeText(activity, "Todo deleted", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(todoRecyclerView)

    }
}