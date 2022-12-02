package com.saket.goldensample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.saket.ui.CreateTodoFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val createButton = rootView.findViewById<Button>(R.id.btnCreate)
        createButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view,CreateTodoFragment())
                .commit()
        }
        return rootView
    }
}