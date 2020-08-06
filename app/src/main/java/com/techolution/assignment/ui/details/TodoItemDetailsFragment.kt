package com.techolution.assignment.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.techolution.assignment.R
import com.techolution.assignment.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_todo_item_detail.*
import javax.inject.Inject

class TodoItemDetailsFragment : DaggerFragment() {

    private var itemId: String? = null
    private lateinit var todoItemDetailViewModel: TodoItemDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var title: TextView
    lateinit var description: TextView
    lateinit var isDone: AppCompatCheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getString("item_id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoItemDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(TodoItemDetailViewModel::class.java)
        title = view.findViewById(R.id.todo_title)
        description = view.findViewById(R.id.todo_description)
        isDone = view.findViewById(R.id.status)
        isDone.visibility = View.INVISIBLE


        if (!itemId.isNullOrBlank()) {
            todoItemDetailViewModel.getItem(itemId!!.toInt()).observe(viewLifecycleOwner, Observer {
                title.text = it.title
                (activity as AppCompatActivity)?.supportActionBar?.title = "${it.title}"
                description.text = it.description
                isDone.visibility = View.VISIBLE
                Log.i("isChecked","isCheckd:${it.isChecked}")
                isDone.isChecked = it.isChecked
                Log.i("isChecked","isCheckdFragment:${it}+ ${it.id}")
            })

        }else{
            (activity as AppCompatActivity)?.supportActionBar?.title = getString(R.string.add_task)
        }

        save_button.setOnClickListener {
            if (itemId != null) {
                todoItemDetailViewModel.handleTodoUpdate(
                    itemId!!.toInt(),
                    title.text.toString(),
                    description.text.toString(),
                    isDone.isChecked
                ).observe(viewLifecycleOwner,
                    Observer {
                        if (it) findNavController().popBackStack()
                        else
                            Toast.makeText(
                                context,
                                "Please fill all the feilds",
                                Toast.LENGTH_SHORT
                            ).show()
                    })
            } else {
                todoItemDetailViewModel.handleTodoInsert(
                    title.text.toString(),
                    description.text.toString(),
                    isDone.isChecked
                ).observe(viewLifecycleOwner,
                    Observer {
                        if (it) findNavController().popBackStack()
                        else
                            Toast.makeText(
                                context,
                                "Please fill all the feilds",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                    })
            }
        }
    }
}

