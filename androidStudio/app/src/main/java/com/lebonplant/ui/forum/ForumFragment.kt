package com.lebonplant.ui.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProvider
import android.widget.*
import com.lebonplant.databinding.FragmentForumBinding
import com.lebonplant.ui.forum.postadapter.PostAdapter
import java.util.*
import kotlin.collections.ArrayList
import com.lebonplant.ui.forum.post.Post

class ForumFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var forumViewModel: ForumViewModel
    private var _binding: FragmentForumBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        forumViewModel =
                ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ForumViewModel::class.java)

        _binding = FragmentForumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val testListView: ListView = binding.dynamicList
        val post1: Post = Post(0, "Jean-Rachid", "FIRST", "This is the first post")
        val post2: Post = Post(1, "Jean-Rachid", "Phewwww", "Sounds like i am alone here")


        val elementsContainer = ArrayList<Post>(Arrays.asList(post1, post2))
        val hiddenElementsContainer = ArrayList<Post>(Arrays.asList(post1, post2))

        var postAdapter = PostAdapter(activity!!.applicationContext, elementsContainer)

        testListView.adapter = postAdapter

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterContainer(hiddenElementsContainer,elementsContainer, newText)
                postAdapter.notifyDataSetChanged()
                return true
            }

        })
        binding.refreshButton.setOnClickListener {
            Toast.makeText(activity!!.applicationContext, "click", Toast.LENGTH_SHORT).show()
            elementsContainer.add(post1);
            hiddenElementsContainer.add(post1);
            postAdapter.notifyDataSetChanged();
        }
        return root
    }

    fun filterContainer(src: ArrayList<Post>, dst: ArrayList<Post>, match: String?) {
        dst.removeAll( { s -> true})
        dst.addAll(src.filter { p -> if(match == null) true else p.body.contains(match, true)
                || p.header.contains(match, true)})
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}