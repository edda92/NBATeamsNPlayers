package com.mohamed.nbateamsandplayers.ui.fragments.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseAbstractFragment: Fragment() {

    abstract fun showEmptyList()

    private lateinit var listener: OnBaseInteractionListener

    protected fun showProgress(){
        listener.showProgress()
    }

    protected fun hideProgress(){
        listener.hideProgress()
    }

    protected fun showErrorDialog(title: String?, message: String?){
        listener.showErrorDialog(title, message)
    }

    protected fun setTitle(title: String){
        listener.setTitle(title)
    }

    interface OnBaseInteractionListener{
        fun hideProgress()
        fun showProgress()
        fun showErrorDialog(title: String?, message: String?)
        fun setTitle(title: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnBaseInteractionListener){
            listener = context
        }
    }
}