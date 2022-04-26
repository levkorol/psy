package ru.harlion.psy.ui.exercise


import android.os.Bundle
import android.view.View
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentExListBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.edit.EditExTextRecyclerFragment
import ru.harlion.psy.utils.replaceFragment


class ExListFragment : BindingFragment<FragmentExListBinding>(FragmentExListBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val (title, textInfo) = requireArguments().getIntArray("TEXTS_IDS_LIST_FG")!!
        binding.titleToolbar.text = resources.getText(title)

        when(requireArguments().getSerializable("ENUM")) {
            TypeEx.IDEAS_DIARY -> {
               binding.addBtnMain.setOnClickListener {
                   replaceFragment(
                       EditExTextRecyclerFragment.newInstance(
                           R.string.theme_ideas,
                           R.string.ex_idea_hint,
                           R.string.new_idea
                       ), true
                   )
               }
            }
            TypeEx.WISH_DIARY -> {

            }
            TypeEx.FREE_WRITING -> {

            }
            TypeEx.HIGHLIGHTS_ALBUM -> {

            }
            TypeEx.GRATITUDE_DIARY -> {

            }
            TypeEx.SELF_ESTEEM -> {
                binding.addBtnMain.setOnClickListener {
                    replaceFragment(
                        EditExTextRecyclerFragment.newInstance(
                            R.string.theme_facts,
                            R.string.ex_self_hint,
                            R.string.fact_about_me
                        ), true
                    )
                }
            }
            TypeEx.FAIL_DIARY -> {

            }
            TypeEx.ACTS_SELF_LOVE -> {

            }
            TypeEx.MY_AMBULANCE -> {

            }
            TypeEx.PERFECT_LIFE -> {

            }
            TypeEx.SUCCESS_DIARY -> {

            }
            TypeEx.WORK_WITH_BELIEFS -> {

            }
            TypeEx.LIFE_RULES -> {
                binding.addBtnMain.setOnClickListener {
                    replaceFragment(
                        EditExTextRecyclerFragment.newInstance(
                            R.string.sphere_life,
                            R.string.ex_rules_hint,
                            R.string.new_rule
                        ), true
                    )
                }
            }
            TypeEx.POSITIVE_BELIEFS -> {
                binding.addBtnMain.setOnClickListener {
                    replaceFragment(
                        EditExTextRecyclerFragment.newInstance(
                            R.string.sphere_life,
                            R.string.ex_idea_hint,
                            R.string.new_belief
                        ), true
                    )
                }
            }
        }
    }

    companion object {
        fun newInstance(title : Int, textInfo : Int, typeEx: TypeEx) = ExListFragment().apply {
            arguments = Bundle().apply {
                putIntArray("TEXTS_IDS_LIST_FG", intArrayOf(title, textInfo))
                putSerializable("ENUM", typeEx)
            }
        }
    }
}