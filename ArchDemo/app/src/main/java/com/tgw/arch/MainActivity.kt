package com.tgw.arch

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.tgw.arch.okhttp.BaseUrls
import com.tgw.arch.okhttp.IResponseHandler
import com.tgw.arch.okhttp.OkClient
import com.tgw.arch.viewmodel.ExhibitViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: ExhibitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        viewModel.requestVisitorRetrofit().observe(this, Observer { bean ->
            Log.d("TGW", "---mainActivity bean.msg: " + bean.message + Thread.currentThread().name)
            Log.d("TGW", "---mainActivity bean.toString: $bean")
        })
    }

    private fun requestVisitor() {
        OkClient.getInstance().get(BaseUrls.getHost() + "api/biz/visitor", object : IResponseHandler {
            override fun onFailure(statusCode: Int, errorMsg: String) {
                Log.d("TGW", "----errorMsg: {$errorMsg}")
            }

            override fun onProgress(currentBytes: Long, totalBytes: Long) {
                Log.d("TGW", "----currentBytes: {$currentBytes}, totalBytes: {$totalBytes}")
            }

            override fun onSuccess(statusCode: Int, response: String) {
                Log.d("TGW", "----response: {$response}")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}