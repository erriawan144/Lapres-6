package com.example.lapres_6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class TodoFragment : Fragment() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var buttonDeleteSelected: Button
    private lateinit var listViewTasks: ListView
    private lateinit var tasks: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var selectedTasks: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo, container, false)

        editTextTask = view.findViewById(R.id.editTextTask)
        buttonAddTask = view.findViewById(R.id.buttonAddTask)
        buttonDeleteSelected = view.findViewById(R.id.buttonDeleteSelected)
        listViewTasks = view.findViewById(R.id.listViewTasks)

        tasks = ArrayList()
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_multiple_choice, tasks)
        listViewTasks.adapter = adapter
        listViewTasks.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        selectedTasks = ArrayList()

        buttonAddTask.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyDataSetChanged()
                editTextTask.text.clear()
            }
        }

        buttonDeleteSelected.setOnClickListener {
            val checkedItemPositions = listViewTasks.checkedItemPositions
            for (i in checkedItemPositions.size() - 1 downTo 0) {
                val position = checkedItemPositions.keyAt(i)
                if (checkedItemPositions.valueAt(i)) {
                    selectedTasks.add(tasks[position])
                    tasks.removeAt(position)
                }
            }
            listViewTasks.clearChoices()
            adapter.notifyDataSetChanged()
        }

        return view
    }
}