package ru.harlion.psy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<VB : ViewBinding> protected constructor(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {
    private var _viewBinding: VB? = null
    protected val binding get() = _viewBinding!!

    final override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflate(inflater, container, false).also {
        _viewBinding = it
    }.root

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}