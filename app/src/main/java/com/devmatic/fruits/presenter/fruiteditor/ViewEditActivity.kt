package com.devmatic.fruits.presenter.fruiteditor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import com.devmatic.fruits.R
import com.devmatic.fruits.data.utils.getIntentExtra
import com.devmatic.fruits.presenter.editfruit.EditFruitFragment
import com.devmatic.fruits.presenter.viewfruit.ViewFruitFragment



class ViewEditActivity : AppCompatActivity() {
    private var currentfragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_edit)
        val id = getIntentExtra<Long>(intent, "id")
        val edit = getIntentExtra<Boolean>(intent, "edit") ?: false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        selectFragment(id, edit)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return true
    }

    private fun selectFragment(id: Long?, edit: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        val bundle = Bundle()
        if (id != null) {
            if (edit) {
                currentfragment = EditFruitFragment()
                bundle.putLong("id", id)
                bundle.putBoolean("edit", edit)
            } else {
                currentfragment = ViewFruitFragment()
                bundle.putLong("id", id)
            }
        } else {
            currentfragment = EditFruitFragment()
        }
        currentfragment?.arguments = bundle
        fragmentTransaction.replace(R.id.frameContainer, currentfragment)
        fragmentTransaction.commit()
    }
}
