package com.example.phonebookfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(),PhoneViewAdapter.ItemClickListener {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    companion object{
        val items= arrayListOf<PhoneModel>();
        val phonebook = arrayListOf(
            "0812345678",
            "0854321987",
            "0876543210",
            "0832143654",
            "0865432109",
            "0823456789",
            "0843210987",
            "0887654321",
            "0810987654",
            "0845678901",
            "0856789012",
            "0832109876",
            "0865432109",
            "0876543210",
            "0821098765",
            "0845678901",
            "0812345678",
            "0832143654",
            "0865432109",
            "0887654321"
        )
        val names = arrayListOf(
            "John Smith",
            "Emily Johnson",
            "Michael Williams",
            "Sarah Brown",
            "Christopher Davis",
            "Jessica Miller",
            "Matthew Wilson",
            "Olivia Anderson",
            "David Taylor",
            "Sophia Thomas",
            "Daniel Martinez",
            "Isabella Garcia",
            "Andrew Martinez",
            "Mia Robinson",
            "Joseph Lee",
            "Abigail Thompson",
            "William Hall",
            "Ava Young",
            "James Clark",
            "Charlotte Perez"
        )


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        val adapter = PhoneRVAdapter(items, this)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val adapter= PhoneViewAdapter(items, this)
        val listView=view.findViewById<ListView>(R.id.list_view)
        listView.adapter=adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // Open the context menu
            ItemClick(position)
        }

//
//        val recyclerView = findViewById<RecyclerView>(R.id.rcv_phonebook)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = adapter
        registerForContextMenu(listView)
        return view
    }

    override fun ItemClick(position: Int) {
        val phonebook = items[position]
        val bundle = Bundle()
        bundle.putString("NAME", phonebook.name)
        bundle.putString("EMAIL", phonebook.email)
        bundle.putString("PHONE_NUMBER", phonebook.phoneNumber)

        findNavController().navigate(R.id.detailFragment, bundle)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(0, v.id, 0, "call")
        menu.add(0, v.id, 0, "send email")
        menu.add(0, v.id, 0, "send sms")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info: AdapterView.AdapterContextMenuInfo = item.getMenuInfo() as AdapterView.AdapterContextMenuInfo;
        super.onContextItemSelected(item)
        val position= info.position
        val phoneNumber=items[position].phoneNumber
        val email=items[position].email
        if (item.title === "call") {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))

            startActivity(intent)
            Log.v("TAG", "call ")
        } else if (item.title === "send email") {
            val intent=Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email)) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                // You can also attach multiple items by passing an ArrayList of Uris
            }
            startActivity(intent)
            Log.v("TAG", "email")
        } else if (item.title === "send sms") {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$phoneNumber"))
            intent.putExtra("sms_body", "Content")
            startActivity(intent)


            Log.v("TAG", "sms $phoneNumber")
        }
        return true
    }



}