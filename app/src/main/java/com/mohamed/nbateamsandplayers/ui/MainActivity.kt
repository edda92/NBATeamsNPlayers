package com.mohamed.nbateamsandplayers.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mohamed.nbateamsandplayers.R
import com.mohamed.nbateamsandplayers.databinding.ActivityMainBinding
import com.mohamed.nbateamsandplayers.ui.fragments.PlayerDetailFragment
import com.mohamed.nbateamsandplayers.ui.fragments.PlayersFragment
import com.mohamed.nbateamsandplayers.ui.fragments.base.BaseAbstractFragment

class MainActivity : AppCompatActivity(), BaseAbstractFragment.OnBaseInteractionListener {

    private lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityBinding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onContextItemSelected(item)

        }
    }

    override fun hideProgress() {
        activityBinding.progressContainer.visibility = View.GONE
    }

    override fun showProgress() {
        activityBinding.progressContainer.visibility = View.VISIBLE
    }

    override fun showErrorDialog(title: String?, message: String?) {
        MaterialAlertDialogBuilder(applicationContext)
            .setTitle(title ?: getString(R.string.alert_title))
            .setMessage(message ?: getString(R.string.alert_message))
            .setNeutralButton(R.string.alert_button) {
                dialog, _ ->
                onBackPressed()
                dialog.dismiss()
            }
            .show()
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }


}
