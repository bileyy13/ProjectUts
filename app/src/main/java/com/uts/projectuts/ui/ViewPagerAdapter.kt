package com.uts.projectuts.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.uts.projectuts.ui.about.AboutFragment
import com.uts.projectuts.ui.dashboard.DashboardFragment

class ViewPagerAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DashboardFragment()
            1 -> AboutFragment()
            else -> throw IllegalArgumentException(
                "Posisi tab tidak valid"
            )
        }
    }
}
