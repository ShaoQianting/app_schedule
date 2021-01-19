package com.fragmentdemo10_actionbar;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {
	private ActionBar actionBar;
	/**
	 * 设置三个整型常量，分别为0,1,2;分别对应 ：运动新闻、娱乐新闻、军事新闻。
	 */
	private final int SPORTS = 0;
	private final int ENTERTAINMENT = 1;
	private final int MILITARY = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actionBar = getActionBar();
		// 设置ActionBar的导航模式
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		/**
		 * 添加三个tab，分别为：体育新闻，娱乐新闻，军事新闻。
		 */
		actionBar.addTab(actionBar.newTab().setText("体育新闻")
				.setIcon(R.drawable.ic_launcher)
				.setTabListener(new MyTabListener()).setTag(SPORTS));
		actionBar.addTab(actionBar.newTab().setText("娱乐新闻")
				.setIcon(R.drawable.ic_launcher)
				.setTabListener(new MyTabListener()).setTag(ENTERTAINMENT));
		actionBar.addTab(actionBar.newTab().setText("军事新闻")
				.setIcon(R.drawable.ic_launcher)
				.setTabListener(new MyTabListener()).setTag(MILITARY));
	}

	class MyTabListener implements TabListener {

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			switch (Integer.parseInt(tab.getTag().toString())) {
			/**
			 * 对应体育新闻
			 */
			case SPORTS:
				ft.replace(R.id.main, new FragementA());
				break;
			/**
			 * 对应娱乐新闻
			 */
			case ENTERTAINMENT:
				ft.replace(R.id.main, new FragementB());
				break;
			/**
			 * 对应军事新闻
			 */
			case MILITARY:
				ft.replace(R.id.main, new FragementC());
				break;
			default:
				break;
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {

		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {

		}

	}
}
