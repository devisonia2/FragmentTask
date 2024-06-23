package com.sonia.fragmentlifecycle

import activityinterface.ActivityInterface
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sonia.fragmentlifecycle.databinding.FragmentFirstBinding

/// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment() :Fragment(), ActivityInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var btnchangefromfragment: Button?=null
    var mainActivity: MainActivity?=null
    var counter=0
    var binding: FragmentFirstBinding?=null

    constructor(parcel: Parcel) : this() {
        param1 = parcel.readString()
        param2 = parcel.readString()
        counter = parcel.readInt()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Toast.makeText(requireContext(),"onAttach",Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity=activity as MainActivity
        mainActivity?.activityInterface=this
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentFirstBinding.inflate(layoutInflater)
        binding?.btnreset?.setOnClickListener {
            counter=0
            mainActivity?.changeBtnText(counter.toString())
        }
        binding?.btnincrease?.setOnClickListener {
            counter++
            mainActivity?.changeBtnText(counter.toString())
        }
        binding?.btndecrease?.setOnClickListener {
            counter--
            mainActivity?.changeBtnText(counter.toString())
        }
        binding?.btnpassFromFragment?.setOnClickListener {
            if (binding?.value?.text?.trim().isNullOrEmpty()) {
                binding?.value?.error = "Enter Value"
            }
            else{
                mainActivity?.changeText("${binding?.value?.text?.toString()?.trim()}")
            }

        }
        return  binding?.root
        //return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(requireContext(), "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Toast.makeText(requireContext(), "onViewStateRestored", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        btnchangefromfragment=view.findViewById(R.id.btnchangefromfragment)

    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(requireContext(), "onResume", Toast.LENGTH_SHORT).show()
    }
    override fun onPause() {
        super.onPause()
        Toast.makeText(requireContext(), "onPause", Toast.LENGTH_SHORT).show()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(requireContext(), "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(requireContext(), "onSaveInstanceState", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(requireContext(), "onDestroyView", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(requireContext(), "onDestroy", Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()
        Toast.makeText(requireContext(), "onDetach", Toast.LENGTH_SHORT).show()
    }

    override fun changeBtnText(text:String) {
        binding?.value?.setText(text)
    }

    override fun changeColor(color: Int) {
        when(color){
            1->binding?.llFragment?.setBackgroundColor(ContextCompat.getColor(mainActivity!!,R.color.red))
            2->binding?.llFragment?.setBackgroundColor(ContextCompat.getColor(mainActivity!!,R.color.green))
            3->binding?.llFragment?.setBackgroundColor(ContextCompat.getColor(mainActivity!!,R.color.blue))
            }
        }
    }

