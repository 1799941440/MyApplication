package com.djkj.myapplication

import android.os.Bundle
import coil.load
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAc<MainPresenter<IView>>(), IView {

    override fun providePresenter(): MainPresenter<IView> = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv.load(R.mipmap.ic_launcher)
        presenter?.getOrg("dwdwdwdwd")
    }
}