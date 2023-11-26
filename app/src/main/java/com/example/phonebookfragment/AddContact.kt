package com.example.phonebookfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddContact.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddContact : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)
        view.findViewById<Button>(R.id.add_btn).setOnClickListener {
            val name= view.findViewById<EditText>(R.id.input_name).text.toString()
            val phoneNumber= view.findViewById<EditText>(R.id.input_phone_number).text.toString()
            val email= view.findViewById<EditText>(R.id.input_email).text.toString()
            if(name.length >0 && phoneNumber.length>0 && email.length>0){
                HomeFragment.items.add(PhoneModel(name,phoneNumber,email))
                findNavController().navigate(R.id.homeFragment)
                Toast.makeText(getContext(), "Add contact successfully", Toast.LENGTH_LONG).show()
            }else{
              Toast.makeText(getContext(), "Please input all field", Toast.LENGTH_LONG).show()
            }

        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddContact.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddContact().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}