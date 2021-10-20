package bukalapak.data;

import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class InspirationData {
    private static String stockImageUrl = "http://www.staging53.vm/uploads/inspiration/5a19996666eb2c3f90fb26b5/original/data.jpg";
    private static String stockThumbnailUrl = "http://www.staging53.vm/uploads/inspiration/5a19996666eb2c3f90fb26b5/s-600-750/data.jpg";
    private static String productUrl = "http://www.staging53.vm/p/fashion-wanita/jam-tangan/5o-jual-jam-tangan-bagus";
    private static String userUrl = "http://www.staging53.vm/gaudi-official";
    private static String videoUrl = "https://www.youtube.com/watch?v=ZCzWIGOCeBM";
    private static String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
    private static String shortDescription = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m";
    private static List<Double> totalLikes = new ArrayList<>();


    public static String getStockImageUrl() {
        return stockImageUrl;
    }

    public static String getStockThumbnailUrl() {
        return stockThumbnailUrl;
    }

    public static String getProductUrl() {
        return productUrl;
    }

    public static String getUserUrl() {
        return userUrl;
    }

    public static String getVideoUrl() {
        return videoUrl;
    }

    public static String getDescription() {
        return description;
    }

    public static String getShortDescription() {
        return shortDescription;
    }

    public static void setTotalLikePost(Integer index, Double totalLike) {
        if (index == 0) {
            Assert.fail("Index should start from 1");
        } else {
            if (totalLikes.isEmpty()) {
                totalLikes.add(totalLike);
            } else if (totalLikes.get(index - 1) != null) {
                totalLikes.set(index - 1, totalLike);
            }
        }
    }

    public static List<Double> getTotalLikes() {
        return totalLikes;
    }
}