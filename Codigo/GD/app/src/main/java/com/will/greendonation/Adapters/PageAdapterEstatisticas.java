package com.will.greendonation.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.will.greendonation.Activies.Coletador.EstatisticasColetador;
import com.will.greendonation.Activies.Doador.EstatisticaDoador;
import com.will.greendonation.Activies.Entregues;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Sistema;

/**
 * Created by Will Neto on 15/06/2017.
 */

public class PageAdapterEstatisticas extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapterEstatisticas(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if(Sistema.usuario instanceof Coletador) {
                    EstatisticasColetador tab1 = new EstatisticasColetador();
                    return tab1;
                }
                else{
                    EstatisticaDoador tab1 = new EstatisticaDoador();
                    return tab1;
                }
            case 1:
                Entregues tab2 = new Entregues();
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
