package com.artifactslab.newspan.features.main

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import arrow.core.Either
import arrow.effects.IO
import arrow.syntax.function.pipe
import com.artifactslab.helloarrow.R
import com.artifactslab.newspan.dto.ArticlesItem
import com.artifactslab.newspan.features.details.DetailsActivity
import com.artifactslab.newspan.retrofit.apiClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {
    override fun showMessage(string: String) {
        runOnUiThread {
            //Toast.makeText(this, "$string", Toast.LENGTH_SHORT).show()
            //textView.text = textView.text.toString() + "\n\n" + string
        }
    }

    override fun showLoading() {
        runOnUiThread {
            progressBar.visibility = VISIBLE
        }
    }

    override fun hideLoading() {
        runOnUiThread {
            progressBar.visibility = INVISIBLE
        }
    }

    override fun showList(list: List<ArticlesItem>) {

        var openDetailsActivity = fun(value: String) {
            val intent = Intent(this, DetailsActivity::class.java)
            var bundle = Bundle()
            bundle.putString("desc", value)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        runOnUiThread {
            recyclerViewNewsHeadlines.layoutManager = LinearLayoutManager(this)



            recyclerViewNewsHeadlines.adapter = MainAdapter(list) { news -> openDetailsActivity(news.description + "\n\n" + news.content) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiClient = apiClient()
        val mainActivityContext = MainActivityContext(this, apiClient)
        getNews().run(mainActivityContext)
    }

}
