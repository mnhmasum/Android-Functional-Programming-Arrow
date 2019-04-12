package com.artifactslab.newspan.features.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.artifactslab.helloarrow.R
import com.artifactslab.newspan.dto.ArticlesItem
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsActivityView {
    override fun showMessage(string: String) {
        runOnUiThread {
            //Toast.makeText(this, "$string", Toast.LENGTH_SHORT).show()
            //textView.text = textView.text.toString() + "\n\n" + string
        }
    }

    override fun showLoading() {
        runOnUiThread {

        }
    }

    override fun hideLoading() {
        runOnUiThread {

        }
    }

    override fun showList(list: List<ArticlesItem>) {
        runOnUiThread {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        textView.text = intent.getStringExtra("desc")
    }

}
