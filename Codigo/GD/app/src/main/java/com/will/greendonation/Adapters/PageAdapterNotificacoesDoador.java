package com.will.greendonation.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.will.greendonation.Activies.Doador.DoacoesEmAndamento;
import com.will.greendonation.Activies.Doador.DoacoesEmEspera;

/**
 * Created by Will Neto on 15/06/2017.
 */

public class PageAdapterNotificacoesDoador extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapterNotificacoesDoador(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DoacoesEmEspera tab1 = new DoacoesEmEspera();
                return tab1;
            case 1:
                DoacoesEmAndamento tab2 = new DoacoesEmAndamento();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
