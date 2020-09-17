package com.kedacom.kotlindemo.hencoder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.kedacom.kotlindemo.R
import com.kedacom.kotlindemo.databinding.ActivityHencoderBinding
import com.kedacom.kotlindemo.hencoder.flp.FlipBoardFragment
import com.kedacom.kotlindemo.hencoder.zan.LikeThumbFragment
import kotlinx.android.synthetic.main.activity_hencoder.view.*

/**
 * @author keda
 */
class HenCoderActivity : AppCompatActivity() {
    val pages = arrayOf(FlipBoardFragment(), LikeThumbFragment(), BaseWidgetFragment(), BaseWidgetFragment())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind: ActivityHencoderBinding = DataBindingUtil.setContentView(this, R.layout.activity_hencoder)

        bind.viewPager.run {
            adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(position: Int): Fragment {
                    return pages[position]
                }

                override fun getCount(): Int {
                    return pages.size
                }

                override fun getPageTitle(position: Int): CharSequence? {
                    return pages[position].mTitle
                }
            }
        }
        bind.tabLayout.run {
            pages.forEach {
                addTab(newTab().setText(it.mTitle))
            }
            setupWithViewPager(bind.viewPager)
        }


    }
}