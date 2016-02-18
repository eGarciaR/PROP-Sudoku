/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.presentation;

import controllers.domain.StatisticsDomainCtrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author eric
 */
public class StatisticsPresentationCtrl {

    private static StatisticsPresentationCtrl statisticsPresentationCtrl;
    private final PresentationCtrl presentationCtrl;
    private final StatisticsDomainCtrl statisticsDomainCtrl;

    private StatisticsPresentationCtrl(PresentationCtrl presCtrl) {
        presentationCtrl = presCtrl;
        statisticsDomainCtrl = presentationCtrl.getStatisticsDomainCtrl();
    }

    public static StatisticsPresentationCtrl getInstance(PresentationCtrl presCtrl) {
        if (statisticsPresentationCtrl == null) {
            statisticsPresentationCtrl = new StatisticsPresentationCtrl(presCtrl);
        }
        return statisticsPresentationCtrl;
    }

    public ArrayList<HashMap<String, Object>> getRanking() {
        return statisticsDomainCtrl.getRanking();
    }

    public HashSet<HashMap<String, Object>> getRecords() {
        return statisticsDomainCtrl.getRecords();
    }

}
