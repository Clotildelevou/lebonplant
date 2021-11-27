package com.lebonplant.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProvider
import android.widget.*
import com.lebonplant.databinding.FragmentDashboardBinding
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val testListView: ListView = binding.dynamicList;

        var elementsContainer = ArrayList(Arrays.asList("Virat Kohli", "Rohit Sharma", "Steve Smith",
            "Kane Williamson", "Ross Taylor"))
        var customAdapter = ArrayAdapter(activity!!.applicationContext,
            android.R.layout.simple_list_item_1, elementsContainer) //TODO replace sample list by API call

        testListView.adapter = customAdapter;

        binding.refreshButton.setOnClickListener {
            Toast.makeText(activity!!.applicationContext, "click", Toast.LENGTH_SHORT).show()
            elementsContainer.add("New item");
            customAdapter.notifyDataSetChanged();
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}