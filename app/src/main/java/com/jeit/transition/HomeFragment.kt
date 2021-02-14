package com.jeit.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jeit.transition.databinding.FragmentMainBinding

class HomeFragment: Fragment(), TestAdapter.Callback {

    private lateinit var mBinding:FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.run {
            rcvTest.layoutManager = GridLayoutManager(context,2)
            rcvTest.adapter = TestAdapter().apply {
                addCallback(this@HomeFragment)
            }
        }
    }

    override fun onClick(ivTest: ImageView, transitionName: String) {
        ivTest.transitionName = transitionName
        val extras = FragmentNavigatorExtras(ivTest to ivTest.transitionName)
    }
}