package mobi.jedi.example.sixtcarlist.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import mobi.jedi.example.sixtcarlist.R
import mobi.jedi.example.sixtcarlist.presentation.list.CarListFragment
import mobi.jedi.example.sixtcarlist.presentation.map.MapFragment

class MainActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            Intent(context, MainActivity::class.java)
                .run(context::startActivity)

            (context as? Activity)
                ?.run {
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager(view_pager)
    }

    private fun initViewPager(viewPager: ViewPager) {
        viewPager.adapter = PagerAdapter(
            supportFragmentManager,
            arrayOf({ CarListFragment.newInstance() }, { MapFragment.newInstance() }),
            arrayOf(getString(R.string.list_tab_title), getString(R.string.map_tab_title))
        )
    }

    private class PagerAdapter(
        fragmentManager: FragmentManager,
        val fragments: Array<(() -> Fragment)>,
        val titles: Array<CharSequence>
    ) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position].invoke()

        override fun getPageTitle(position: Int): CharSequence? = titles[position]

        override fun getCount(): Int = fragments.size
    }
}
