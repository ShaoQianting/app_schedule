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
	 * �����������ͳ������ֱ�Ϊ0,1,2;�ֱ��Ӧ ���˶����š��������š��������š�
	 */
	private final int SPORTS = 0;
	private final int ENTERTAINMENT = 1;
	private final int MILITARY = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actionBar = getActionBar();
		// ����ActionBar�ĵ���ģʽ
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		/**
		 * �������tab���ֱ�Ϊ���������ţ��������ţ��������š�
		 */
		actionBar.addTab(actionBar.newTab().setText("��������")
				.setIcon(R.drawable.ic_launcher)
				.setTabListener(new MyTabListener()).setTag(SPORTS));
		actionBar.addTab(actionBar.newTab().setText("��������")
				.setIcon(R.drawable.ic_launcher)
				.setTabListener(new MyTabListener()).setTag(ENTERTAINMENT));
		actionBar.addTab(actionBar.newTab().setText("��������")
				.setIcon(R.drawable.ic_launcher)
				.setTabListener(new MyTabListener()).setTag(MILITARY));
	}

	class MyTabListener implements TabListener {

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			switch (Integer.parseInt(tab.getTag().toString())) {
			/**
			 * ��Ӧ��������
			 */
			case SPORTS:
				ft.replace(R.id.main, new FragementA());
				break;
			/**
			 * ��Ӧ��������
			 */
			case ENTERTAINMENT:
				ft.replace(R.id.main, new FragementB());
				break;
			/**
			 * ��Ӧ��������
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
