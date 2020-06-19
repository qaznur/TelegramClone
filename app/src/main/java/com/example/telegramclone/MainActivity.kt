package com.example.telegramclone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.telegramclone.databinding.ActivityMainBinding
import com.example.telegramclone.ui.ChatsFragment
import com.example.telegramclone.ui.SettingsFragment
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class MainActivity : AppCompatActivity() {

    companion object {
        const val MENU_CREATE_GROUP_POSITION = 1
        const val MENU_SECRET_CHAT_POSITION = 2
        const val MENU_CREATE_CHANNEL_POSITION = 3
        const val MENU_CONTACTS_POSITION = 4
        const val MENU_CALLS_POSITION = 5
        const val MENU_FAVORITES_POSITION = 6
        const val MENU_SETTINGS_POSITION = 7
        const val MENU_INVITE_POSITION = 8
        const val MENU_HELP_POSITION = 9

        const val MENU_CHATS_FRAGMENT = "menu_chats_fragment"
        const val MENU_SETTINGS_FRAGMENT = "menu_settings_fragment"
    }

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mDrawer: Drawer
    private lateinit var mAccountHeader: AccountHeader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setSupportActionBar(mBinding.mainToolbar)
        createDrawer()
        startWithChats()
    }

    private fun startWithChats() {
        val fragment = ChatsFragment()
        openFragment(fragment, true, MENU_CHATS_FRAGMENT)
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainer, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    private fun createDrawer() {
        mAccountHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.drawer_header)
            .addProfiles(
                ProfileDrawerItem()
                    .withName("Zhakenger Nuradil")
                    .withEmail("+7(777) 111 22 33")
            ).build()

        mDrawer = DrawerBuilder()
            .withActivity(this)
            .withToolbar(mBinding.mainToolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mAccountHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100).withName("Создать группу").withIcon(R.drawable.ic_menu_create_group),
                PrimaryDrawerItem().withIdentifier(101).withName("Создать секретный чат").withIcon(R.drawable.ic_menu_secret),
                PrimaryDrawerItem().withIdentifier(102).withName("Создать канал").withIcon(R.drawable.ic_menu_create_channel),
                PrimaryDrawerItem().withIdentifier(103).withName("Контакты").withIcon(R.drawable.ic_menu_contacts),
                PrimaryDrawerItem().withIdentifier(104).withName("Звонки").withIcon(R.drawable.ic_menu_phone),
                PrimaryDrawerItem().withIdentifier(105).withName("Избранное").withIcon(R.drawable.ic_menu_favorite),
                PrimaryDrawerItem().withIdentifier(106).withName("Настройки").withIcon(R.drawable.ic_menu_settings),
                DividerDrawerItem(),
                PrimaryDrawerItem().withIdentifier(107).withName("Пригласить друзей").withIcon(R.drawable.ic_menu_invite),
                PrimaryDrawerItem().withIdentifier(107).withName("Вопросы о Telegram").withIcon(R.drawable.ic_menu_help)
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when (position) {
                        MENU_SETTINGS_POSITION -> {
                            val fragment = SettingsFragment()
                            openFragment(fragment, false, MENU_SETTINGS_FRAGMENT)
                        }
                    }
                    return false
                }

            })
            .build()
    }
}
