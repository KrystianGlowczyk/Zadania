package krglow;

import krglow.model.ConiferousTree;
import krglow.model.DeciduousTree;
import krglow.model.Season;


public class Main {

    public static void main(String[] args) {

        // Tworzenie drzewa liściastego i iglastego
        DeciduousTree deciduousTree = new DeciduousTree();
        ConiferousTree coniferousTree = new ConiferousTree();

        // Rozwijanie obu drzew
        System.out.println("Rozwój drzewa liściastego:");
        deciduousTree.grow();
        System.out.println("Liczba gałęzi w drzewie liściastym: " + deciduousTree.getBranches().size());
        int totalDeciduousLeaves = deciduousTree.getBranches().stream().mapToInt(branch -> branch.getLeaves().size()).sum();
        System.out.println("Liczba liści w drzewie liściastym: " + totalDeciduousLeaves);
        System.out.println();

        System.out.println("Rozwój drzewa iglastego:");
        coniferousTree.grow();
        System.out.println("Liczba gałęzi w drzewie iglastym: " + coniferousTree.getBranches().size());
        int totalConiferousLeaves = coniferousTree.getBranches().stream().mapToInt(branch -> branch.getLeaves().size()).sum();
        System.out.println("Liczba igieł w drzewie iglastym: " + totalConiferousLeaves);
        System.out.println();

        // Zmiana pory roku na jesień dla drzewa liściastego
        System.out.println("Zmiana pory roku na jesień dla drzewa liściastego:");
        deciduousTree.changeSeason(Season.FALL);
        totalDeciduousLeaves = deciduousTree.getBranches().stream().mapToInt(branch -> branch.getLeaves().size()).sum();
        System.out.println("Liczba liści w drzewie liściastym po jesieni: " + totalDeciduousLeaves);
        System.out.println();

        // Zmiana pory roku na zimę dla drzewa iglastego
        System.out.println("Zmiana pory roku na zimę dla drzewa iglastego:");
        coniferousTree.changeSeason(Season.WINTER);
        System.out.println("Zdrowie drzewa iglastego po zimie: " + coniferousTree.getHealth());

    }

}
