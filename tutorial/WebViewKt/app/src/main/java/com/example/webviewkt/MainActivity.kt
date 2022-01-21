package com.example.webviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true   // Enable JavaScript

        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문 */
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문 */

        webView.loadUrl("https://www.naver.com")
    }

    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.webView)
        if (webView.canGoBack()) {  // 웹사이트에서 뒤로 갈 페이지가 있다면
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}