package bukalapak.data;

public class PohonRejekiData {

    private static int currentUserWaterdrop;
    private static int nextStageWaterdrop;
    private static int totalWaterdropInPot;
    private static int levelUpTotalCount;

    public static int getCurrentUserWaterdrop() {
        return currentUserWaterdrop;
    }

    public static void setCurrentUserWaterdrop(int currentUserWaterdrop) {
        PohonRejekiData.currentUserWaterdrop = currentUserWaterdrop;
    }

    public static int getNextStageWaterdrop() {
        return nextStageWaterdrop;
    }

    public static void setNextStageWaterdrop(int nextStageWaterdrop) {
        PohonRejekiData.nextStageWaterdrop = nextStageWaterdrop;
    }

    public static int getTotalWaterdropInPot() {
        return totalWaterdropInPot;
    }

    public static void setTotalWaterdropInPot(int totalWaterdropInPot) {
        PohonRejekiData.totalWaterdropInPot = totalWaterdropInPot;
    }

    public static int getLevelUpTotalCount() {
        return levelUpTotalCount;
    }

    public static void setLevelUpTotalCount(int levelUpTotalCount) {
        PohonRejekiData.levelUpTotalCount = levelUpTotalCount;
    }
}
