package com.example.phonebookfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    private  lateinit var navController:NavController;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repeat(20) {
            val parts = HomeFragment.names[it].lowercase().split(" ")
            val firstName = parts.first()
            val lastName = parts.last()
            val domain = "example.com" // Thay đổi tên miền tùy ý

            HomeFragment.items.add(
                PhoneModel(
                    HomeFragment.names[it],
                    HomeFragment.phonebook[it],
                    "$firstName.$lastName@$domain"
                )
            )
        }
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        as NavHostFragment
         navController=navHostFragment.navController
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.add_contact){
            navController.navigate(R.id.addContact)
            return true;
        }else{
            return super.onOptionsItemSelected(item)
        }

    }
}