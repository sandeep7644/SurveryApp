package com.example.surveryapp.features.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.surveryapp.R
import com.example.surveryapp.features.ui.profile.UserProfileActivity
import com.example.surveryapp.features.ui.add_survey.AddSurveyActivity
import com.example.surveryapp.persistance.entities.Survey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dashboard_activity.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    val dashboardViewModel: DashboardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        setUpToolbar()
        val adapter = SurveyRecyclerviewAdapter()
        recyclerview.adapter = adapter
        dashboardViewModel.getSurveys().observe(this, object : Observer<List<Survey>> {
            override fun onChanged(t: List<Survey>?) {
                adapter.submitList(t)
            }


        })

        dashboardViewModel.logout.observe(this, object : Observer<Boolean> {
            override fun onChanged(t: Boolean) {
                if (true) {
                    finish()
                }
            }


        })


        add_survey.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, AddSurveyActivity::class.java))

        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                dashboardViewModel.logout()
            }
            R.id.profile -> {
                startActivity(Intent(this@DashboardActivity, UserProfileActivity::class.java))

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle("Dashboard")
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}