package ru.harlion.psy.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BindingHolder<VB : ViewBinding>(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    parent: ViewGroup,
    val binding: VB = inflate(
        LayoutInflater.from(parent.context), parent, false
    )
) : RecyclerView.ViewHolder(binding.root)