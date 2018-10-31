package com.example.merve.issues.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabItem
import android.support.v4.view.ViewPager

import android.support.design.widget.TabLayout;
import android.widget.TextView
import com.example.merve.issues.adapter.PageAdapter
import com.example.merve.issues.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Main2Activity : AppCompatActivity(),TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private lateinit var mAuth:FirebaseAuth
    private  lateinit var tcSigOut:TextView
    private lateinit var tabLayout: TabLayout
    private lateinit  var viewPaper:ViewPager
    private lateinit var pageAdapter: PageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initialisePaging()
    }
    private fun initialisePaging()
    {
        tabLayout = findViewById(R.id.tablayout)
        viewPaper = findViewById(R.id.main_screen)
        mAuth = FirebaseAuth.getInstance()
        tcSigOut = findViewById(R.id.signOut)

        initialiseEvents()
    }
    private fun initialiseEvents()
    {
        pageAdapter = PageAdapter(getSupportFragmentManager(), tabLayout.tabCount)
        viewPaper.adapter = pageAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPaper.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        viewPaper.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabLayout) {})




        tcSigOut.setOnClickListener {
            val currentUser = mAuth.currentUser
            mAuth.signOut();
            updateUI(currentUser)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
        } else {
            val intent = Intent(this@Main2Activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageScrollStateChanged(state: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageSelected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
