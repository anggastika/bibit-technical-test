package bukalapak.utils;

import bukalapak.data.*;
import com.bukalapak.salad.util.LogUtil;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.sf.json.test.JSONAssert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.NotFoundException;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static bukalapak.TestInstrument.dotenv;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APICall {

    private final static Logger LOGGER = LogManager.getLogger(APICall.class);
    private Response response;
    private RequestSpecification requestSpecification;

    public void setForAPIcall() {
        APIData.setApiUrl(dotenv.get("API_URL"));
        APIData.setAccountsUrl(dotenv.get("ACCOUNTS_URL"));
        APIData.setAppVersion(dotenv.get("API_APP_VERSION"));
        APIData.setUserAgent(dotenv.get("API_USER_AGENT"));
        APIData.setIdentity(dotenv.get("API_IDENTITY"));
        APIData.setBukalapakIdentity(dotenv.get("API_BUKALAPAK_IDENTITY"));
        APIData.setClientId(dotenv.get("API_CLIENT_ID"));
        APIData.setClientSecretId(dotenv.get("API_CLIENT_SECRET"));
        LOGGER.info("Done setting property for API Call");
    }

    public void setUserBasicToken(String userType) {
        String tmpUserType = userType.replaceAll(" ", "_").toUpperCase();
        String userPass = dotenv.get(tmpUserType + "_USERNAME") + ":" + dotenv.get(tmpUserType + "_PASSWORD");
        String encodedUserPass = Base64.getEncoder().encodeToString(userPass.getBytes(StandardCharsets.UTF_8));
        String basicAuth = "Basic " + encodedUserPass;

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", basicAuth)
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());
        response = requestSpecification.post("/v2/authenticate.json")
                .then()
                .statusCode(200).extract().response();

        String token = response.path("token").toString();
        String userId = response.path("user_id").toString();
        String decodedBasicToken = userId + ":" + token;
        String encodedBasicToken = Base64.getEncoder().encodeToString(decodedBasicToken.getBytes(StandardCharsets.UTF_8));

        String basicToken = "Basic " + encodedBasicToken;
        LOGGER.info("Authorization API v2 : " + basicToken);
        APIData.setAuthToken(basicToken);
    }

    public void setUserAuthv4(String userType) {
        String tmpUserType = userType.replaceAll(" ", "_").toUpperCase();

        Map<String, String> login = new HashMap<>();

        login.put("client_id", APIData.getClientId());
        login.put("client_secret", APIData.getClientSecretId());
        login.put("grant_type", "password");
        login.put("scope", "public user store");
        login.put("username", dotenv.get(tmpUserType + "_USERNAME"));
        login.put("password", dotenv.get(tmpUserType + "_PASSWORD"));

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getAccountsUrl())
                .contentType("application/json")
                .body(login)
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity())
                .log().all();


        response = requestSpecification.post("oauth/token")
                .then()
                .statusCode(200).extract().response();

        String token = response.path("access_token");
        String bearerToken = "Bearer " + token;
        LOGGER.info("Authorization API v4 : " + bearerToken);
        APIData.setAccessToken(token);
        APIData.setAuthToken(bearerToken);
    }

    public String getUserID(String userType) {
        String tmpUserType = userType.replaceAll(" ", "_").toUpperCase();
        String userPass = dotenv.get(tmpUserType + "_USERNAME") + ":" + dotenv.get(tmpUserType + "_PASSWORD");
        String encodedUserPass = Base64.getEncoder().encodeToString(userPass.getBytes(StandardCharsets.UTF_8));
        String basicAuth = "Basic " + encodedUserPass;

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", basicAuth)
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());
        response = requestSpecification.post("/v2/authenticate.json")
                .then()
                .statusCode(200).extract().response();

        String userId = response.path("user_id").toString();
        return userId;
    }

    public ArrayList<String> getProductsIDFromUser(String sellerID) {
        ArrayList<String> listProductID;
        response = RestAssured.given().
                header("User-Agent", APIData.getUserAgent()).
                contentType("application/json").
                when().
                get("https://api.bukalapak.com/v2/users/" + sellerID + "/products.json?page=1&per_page=6").
                then().
                body("status", is("OK")).
                statusCode(200).extract().response();

        listProductID = response.path("products.id");
        for (String productID : listProductID)
            LOGGER.info(productID);
        return listProductID;
    }

    public ArrayList<String> getCartItemsID() {
        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());

        response = requestSpecification.get("https://api.bukalapak.com/v2/carts.json")
                .then()
                .statusCode(200).extract().response();

        ArrayList<String> idItems = response.path("cart.items.id");
        LOGGER.info(idItems);
        return idItems;
    }

    public ArrayList<String> getCartItemsIDV4() {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        response = requestSpecification.get("/carts")
                .then()
                .statusCode(200).extract().response();

        ArrayList<String> idItems = response.path("data.items.id");
        LOGGER.info(idItems);
        return idItems;
    }

    public Object[] getSellersInCart() {
        ArrayList<String> carts;
        Object[] arrayObject;

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());
        response = requestSpecification.get("https://api.bukalapak.com/v2/carts.json")
                .then()
                .statusCode(200).extract().response();

        carts = response.path("cart.seller.name");

        arrayObject = carts.toArray();
        for (int i = 0; i < arrayObject.length; i++) {
            LOGGER.info("sellersnya bernama..." + arrayObject[i].toString().replace("[", "").replace("]", ""));
            arrayObject[i] = arrayObject[i].toString().replace("[", "").replace("]", "");
        }
        return arrayObject;
    }

    public Object[] getCartItemsName() {
        ArrayList<String> items;
        Object[] arrayObject;

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());
        response = requestSpecification.get("https://api.bukalapak.com/v2/carts.json")
                .then()
                .statusCode(200).extract().response();

        items = response.path("cart.items.name");
        arrayObject = items.toArray();
        for (int i = 0; i < arrayObject.length; i++) {
            LOGGER.info("itemsnya bernama..." + arrayObject[i].toString().replace("[", "").replace("]", ""));
            arrayObject[i] = arrayObject[i].toString().replace("[", "").replace("]", "");
        }

        return arrayObject;
    }

    private int getResponsePathSize(String path) {
        return response.path(path + ".size()");
    }

    private void responsePathShouldBe(String path, Object value) {
        APIData.getResponse().then().assertThat().body(path, equalTo(value));
    }

    private void responseStatusCodeShouldBe(int statusCode) {
        APIData.getResponse().then().statusCode(statusCode);
    }

    private String getResponsePathReplacedArray(String path) {
        return APIData.getResponse().path(path).toString().replace("[", "").replace("]", "");
    }

    public boolean isCartEmptyAPIv2() {
        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());

        response = requestSpecification.get("https://api.bukalapak.com/v2/carts.json")
                .then().extract().response();

        return getResponsePathSize("cart.items.id") == 0;
    }

    public boolean isCartEmptyAPIv4() {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        response = requestSpecification.get("/carts")
                .then().extract().response();

        return getResponsePathSize("data.items.id") == 0;
    }

    public void removeFromCart() {
        Object[] arrayObject = getCartItemsID().toArray();
        LOGGER.info("ada barang di cart berjumlah = " + arrayObject.length);
        for (int i = 0; i < arrayObject.length; i++) {
            LOGGER.info("Try deleting from cart..." + arrayObject[i].toString().replace("[", "").replace("]", ""));
            arrayObject[i] = arrayObject[i].toString().replace("[", "").replace("]", "");
        }

        for (int i = 0; i < arrayObject.length; i++) {
            requestSpecification = RestAssured.given()
                    .baseUri(APIData.getApiUrl())
                    .contentType(APIData.getContentType())
                    .header("Authorization", APIData.getAuthToken())
                    .header("Bukalapak-App-Version", APIData.getAppVersion())
                    .header("User-Agent", APIData.getUserAgent())
                    .header("Identity", APIData.getIdentity())
                    .header("Bukalapak-Identity", APIData.getBukalapakIdentity());

            response = requestSpecification.delete("https://api.bukalapak.com/v2/carts/item/" + arrayObject[i].toString() + ".json").
                    then().extract().response();
            response.then().assertThat().body("message", containsString("telah dihapus dari Keranjang Belanja"));
        }

        // Handle multiple item in one seller
        while (!isCartEmptyAPIv2()) {
            removeFromCart();
        }
    }

    public void removeFromCartV4() {
        Object[] arrayObject = getCartItemsIDV4().toArray();
        LOGGER.info("ada barang di cart berjumlah = " + arrayObject.length);
        for (int i = 0; i < arrayObject.length; i++) {
            LOGGER.info("Try deleting from cart..." + arrayObject[i].toString().replace("[", "").replace("]", ""));
            arrayObject[i] = arrayObject[i].toString().replace("[", "").replace("]", "");
        }

        for (int i = 0; i < arrayObject.length; i++) {
            requestSpecification = RestAssured.given()
                    .baseUri(dotenv.get("API_URL"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + APIData.getAccessToken())
                    .header("Accept", "application/vnd.bukalapak.v4+json")
                    .log().all();

            response = requestSpecification.delete("/carts/items/" + arrayObject[i].toString()).
                    then().extract().response();
            response.then().assertThat().body("message", containsString("Produk telah dihapus dari keranjang"));
        }

        // Handle multiple item in one seller
        while (!isCartEmptyAPIv4()) {
            removeFromCartV4();
        }
    }

    public void addToCart(String sellerCredential, String quantity) {
        ArrayList<String> listProductID;
        String tmpSellerCredential = sellerCredential.replaceAll(" ", "_").toUpperCase();

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());
        response = requestSpecification.get("/v2/products.json?page=1&per_page=6&sort_by=Termurah&username=" +
                dotenv.get(tmpSellerCredential + "_USERNAME")).
                then().statusCode(200).extract().response();

        listProductID = response.path("products.id");
        AssertionError e = null;
        int amount = Integer.parseInt(quantity);
        for (int i = 0; i < amount; i++) {
            try {
                LOGGER.info("Try adding to cart..." + i);
                response =
                        requestSpecification.
                                post("https://api.bukalapak.com/v2/carts/add_product/" + listProductID.get(i) + ".json").
                                then().
                                body("status", is("OK")).
                                body("message", containsString("telah ditambahkan ke Keranjang Belanja")).
                                statusCode(200).extract().response();
            } catch (AssertionError e1) {
                e = e1;
            }
        }
        if (e != null) {
            LOGGER.info("ERROR catched!");
            throw new AssertionError(e.getMessage());
        }
    }

    public void addToCartSpecificProductAPIv2(String productID) {
        String tmpProductID = productID.toUpperCase();

        if (tmpProductID.startsWith("ENV:")) {
            tmpProductID = dotenv.get(tmpProductID.substring(4));
        }

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());

        response = requestSpecification.post("https://api.bukalapak.com/v2/carts/add_product/" + tmpProductID + ".json")
                .then()
                .extract()
                .response();

        response.then().assertThat().body("message", containsString("telah ditambahkan ke Keranjang Belanja"));
    }

    public void addToCartSpecificProductAPIv4(String productId, int quantity) {
        String tmpProductId = productId;
        if (productId.startsWith("ENV:")) {
            tmpProductId = dotenv.get(productId.substring(4));
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("quantity", quantity);
        payload.put("product_id", tmpProductId);

        sendRequestWithBody("post", "/carts/items", payload);
        if (response.statusCode() != 201) {
            throw new NullPointerException(response.getBody().asString());
        }
    }

    public ItemData getFirstItemFromCart() {
        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());
        response = requestSpecification.get("https://api.bukalapak.com/v2/carts.json").
                then().extract().response();

        String name = response.path("cart.items.name").toString().replace("[", "").replace("]", "");
        String harga = response.path("cart.items.price").toString().replace("[", "").replace("]", "");
        Double price = Double.parseDouble(harga);
        String stok = response.path("cart.items.stock").toString().replace("[", "").replace("]", "");
        Double stock = Double.parseDouble(stok);
        String seller_name = response.path("cart.items.product.seller_name").toString().replace("[", "").replace("]", "");

        ItemData itemData = new ItemData();
        itemData.setItemName(name);
        itemData.setSellerName(seller_name);
        itemData.setItemPrice(price);
        itemData.setItemStock(stock);
        return itemData;
    }

    public void changeBukaBikeUserStatus(String userType, String state) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);

        response = requestSpecification.patch("_exclusive/gowes/users/" + dotenv.get(userType + "_SUID") + "/status?state=" + state)
                .then()
                .statusCode(200).extract().response();
    }

    /**
     * Get access token for API exclusive on specific environment
     *
     * @param environment
     */
    public void getTokenForAPIExclusive(String environment) {
        String client_id = dotenv.get("API_CLIENT_ID_STAGING_" + environment);
        String client_secret = dotenv.get("API_CLIENT_SECRET_STAGING_" + environment);

        try {
            requestSpecification = RestAssured.given()
                    .contentType("multipart/form-data")
                    .multiPart("grant_type", "password")
                    .multiPart("username", "ragapinilih")
                    .multiPart("password", "ragapinilih")
                    .multiPart("client_id", client_id)
                    .multiPart("client_secret", client_secret)
                    .multiPart("scope", "public user");

            response = requestSpecification
                    .post("http://accounts.staging" + environment + ".vm/oauth/token")
                    .then().extract().response();

            if (response.statusCode() != 200) {
                LOGGER.error("Something went wrong in API GET exclusive token with status code " + response.statusCode());
            } else {
                LOGGER.info("GET access_token success wiht status code : " + response.statusCode());
                APIData.setAccessTokenExclusiveStaging(response.path("access_token"));
            }
        } catch (Exception exception) {
            LOGGER.error("Something went wrong when get exclusive token : " + exception);
        }
    }

    /**
     * POST inspiration at staging 53
     *
     * @param environment
     * @param accessTokenExclusiveStaging
     * @param type
     * @param influencer
     * @param title
     */
    public void postCustomInspiration(String environment, String accessTokenExclusiveStaging, String type, String influencer, String title) {
        Map<String, Object> inspiration = new HashMap<>();
        ArrayList<Map> contents = new ArrayList<>();
        ArrayList<Map> tags = new ArrayList<>();

        switch (type) {
            case "product tag":
                Map<String, Object> contentProductTag = new HashMap<>();
                Map<String, Object> tagProduct = new HashMap<>();
                Map<String, Object> category = new HashMap<>();
                Map<String, Object> tagProductCoord = new HashMap<>();

                tagProductCoord.put("x", 0.8);
                tagProductCoord.put("y", 0.7);
                category.put("id", 223);
                category.put("name", "Fashion Wanita");
                tagProduct.put("name", "product");
                tagProduct.put("url", InspirationData.getProductUrl());
                tagProduct.put("category", category);
                tagProduct.put("coord", tagProductCoord);
                tags.add(tagProduct);
                contentProductTag.put("type", "image");
                contentProductTag.put("url", InspirationData.getStockImageUrl());
                contentProductTag.put("width", 100);
                contentProductTag.put("height", 100);
                contentProductTag.put("tags", tags);
                contents.add(contentProductTag);
                inspiration.put("title", title);
                inspiration.put("influencer_name", influencer);
                inspiration.put("description", InspirationData.getDescription());
                inspiration.put("published", true);
                inspiration.put("content_type", "image");
                inspiration.put("contents", contents);

                LOGGER.info("POST an inspiration post type : product tag");
                break;
            case "user tag":
                Map<String, Object> contentUserTag = new HashMap<>();
                Map<String, Object> tagUser = new HashMap<>();
                Map<String, Object> tagUserCoord = new HashMap<>();

                tagUserCoord.put("x", 0.8);
                tagUserCoord.put("y", 0.7);
                tagUser.put("name", "product");
                tagUser.put("url", InspirationData.getUserUrl());
                tagUser.put("coord", tagUserCoord);
                tags.add(tagUser);
                contentUserTag.put("type", "image");
                contentUserTag.put("url", InspirationData.getStockImageUrl());
                contentUserTag.put("width", 100);
                contentUserTag.put("height", 100);
                contentUserTag.put("tags", tags);
                contents.add(contentUserTag);
                inspiration.put("title", title);
                inspiration.put("influencer_name", influencer);
                inspiration.put("description", InspirationData.getDescription());
                inspiration.put("published", true);
                inspiration.put("content_type", "image");
                inspiration.put("contents", contents);

                LOGGER.info("POST an inspiration post type : user tag");
                break;
            case "multiple image":
                Map<String, Object> contentImage1 = new HashMap<>();
                Map<String, Object> contentImage2 = new HashMap<>();
                Map<String, Object> contentImage3 = new HashMap<>();

                contentImage1.put("type", "image");
                contentImage1.put("url", InspirationData.getStockImageUrl());
                contentImage1.put("width", 100);
                contentImage1.put("height", 100);

                contentImage2.put("type", "image");
                contentImage2.put("url", InspirationData.getStockImageUrl());
                contentImage2.put("width", 100);
                contentImage2.put("height", 100);

                contentImage3.put("type", "image");
                contentImage3.put("url", InspirationData.getStockImageUrl());
                contentImage3.put("width", 100);
                contentImage3.put("height", 100);

                contents.add(contentImage1);
                contents.add(contentImage2);
                contents.add(contentImage3);

                inspiration.put("title", title);
                inspiration.put("influencer_name", influencer);
                inspiration.put("description", InspirationData.getDescription());
                inspiration.put("published", true);
                inspiration.put("content_type", "image");
                inspiration.put("contents", contents);

                LOGGER.info("POST an inspiration post type : multiple image");
                break;
            case "video":
                Map<String, Object> contentVideo = new HashMap<>();

                contentVideo.put("type", "video");
                contentVideo.put("url", InspirationData.getVideoUrl());
                contentVideo.put("thumbnail", InspirationData.getStockThumbnailUrl());
                contents.add(contentVideo);
                inspiration.put("title", title);
                inspiration.put("influencer_name", influencer);
                inspiration.put("description", InspirationData.getDescription());
                inspiration.put("published", true);
                inspiration.put("content_type", "video");
                inspiration.put("contents", contents);

                LOGGER.info("POST an inspiration post type : video");
                break;
            default:
                Map<String, Object> contentNoTag = new HashMap<>();

                contentNoTag.put("type", "image");
                contentNoTag.put("url", InspirationData.getStockImageUrl());
                contentNoTag.put("width", 100);
                contentNoTag.put("height", 100);
                contents.add(contentNoTag);
                inspiration.put("title", title);
                inspiration.put("influencer_name", influencer);
                inspiration.put("description", InspirationData.getDescription());
                inspiration.put("published", true);
                inspiration.put("content_type", "image");
                inspiration.put("contents", contents);

                LOGGER.info("POST an inspiration post type : no tag");
        }

        Gson rawPayload = new Gson();
        String payload = rawPayload.toJson(inspiration);

        postAnInspiration(environment, accessTokenExclusiveStaging, payload);
    }

    public void postAnInspiration(String environment, String accessTokenExclusiveStaging, Object inspiration) {
        try {
            requestSpecification = RestAssured.given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + accessTokenExclusiveStaging);

            response = requestSpecification
                    .body(inspiration)
                    .when().post("http://api.staging" + environment + ".vm/_exclusive/inspirations/posts")
                    .then().extract().response();

            if (response.statusCode() != 201) {
                Assert.fail("Something went wrong in API POST inspiration with status code " + response.statusCode());
                LOGGER.error("Something went wrong in API POST inspiration with status code " + response.statusCode());
            } else {
                LOGGER.info("POST Inspiration success with status code " + response.statusCode() + " and post_id " + response.path("data.id"));
                APIData.setInspirationIds(response.path("data.id").toString());
            }
        } catch (Exception exception) {
            Assert.fail("Something went wrong when post an inspiration : " + exception);
            LOGGER.error("Something went wrong when post an inspiration : " + exception);
        }
    }

    /**
     * Delete all created inspiration post by id
     */
    public void deleteCreatedInspirationPosts(String environment) {
        List<String> post_ids = APIData.getInspirationIds();

        for (int i = 0; i < post_ids.size(); i++) {
            try {
                requestSpecification = RestAssured.given()
                        .header("Authorization", "Bearer " + APIData.getAccessTokenExclusiveStaging())
                        .contentType("application/json");

                response = requestSpecification
                        .when().delete("http://api.staging" + environment + ".vm/_exclusive/inspirations/posts/" + post_ids.get(i))
                        .then().extract().response();

                if (response.statusCode() != 202) {
                    LOGGER.error("Something went wrong in API Delete inspiration with status code " + response.statusCode());
                } else {
                    LOGGER.info("Success delete inspiration post with id " + post_ids.get(i) + " and status code : " + response.statusCode());
                }
            } catch (Exception exception) {
                LOGGER.error("Something went wrong when delete an inspiration : " + exception);
            }
        }
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPostpaidOptions(ArrayList<Map<String, Object>> preInquiryData, String option) {
        for (Map<String, Object> data : preInquiryData) {
            if (data.get("name").toString().toLowerCase().contains(option.toLowerCase())) {
                return (int) data.get("id");
            }
        }

        return 0;
    }

    public void inquirePostpaidNumber(String product) {
        String customerNumberPath = "data.customer_number";
        String inquiryEndpoint = "";
        String payload;
        String rawPayload = "";
        String[] numbers = {};
        boolean needPreInquiryData = false;
        String preInquiryEndpoint = "";
        ArrayList<Map<String, Object>> preInquiryData = new ArrayList<Map<String, Object>>();

        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .contentType("application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Bukalapak-App-Version", dotenv.get("API_APP_VERSION"))
                .header("User-Agent", dotenv.get("API_USER_AGENT"))
                .header("Identity", dotenv.get("API_IDENTITY"))
                .log().all();

        String tmpProduct = product.toLowerCase();

        try {
            switch (tmpProduct) {
                case "bpjs":
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.MONTH, 5);
                    Date date = cal.getTime();
                    DateFormat dateFormat = new SimpleDateFormat("M y");
                    String[] next = dateFormat.format(date).split(" ");

                    inquiryEndpoint = "/bpjs-kesehatan/inquiries";
                    numbers = dotenv.get("POSTPAID_BPJS_NUMBERS").split(",");
                    rawPayload = "{" +
                            "\"customer_number\": \"%s\"," +
                            "\"paid_until\": { \"month\": " + next[0] + ", \"year\": " + next[1] + " }" +
                            "}";
                    break;

                case "cable tv":
                    inquiryEndpoint = "/cable-tv/inquiries";
                    needPreInquiryData = true;
                    numbers = dotenv.get("POSTPAID_CABLE_TV_NUMBERS").split(",");
                    preInquiryEndpoint = "/cable-tv/billers";
                    rawPayload = "{" +
                            "\"customer_number\": \"%s\"," +
                            "\"biller_id\": %d" +
                            "}";
                    break;

                case "credit card bill":
                    inquiryEndpoint = "/credit-card-bills/inquiries";
                    needPreInquiryData = true;
                    numbers = dotenv.get("POSTPAID_CREDIT_CARD_NUMBERS").split(",");
                    preInquiryEndpoint = "/credit-card-bills/billers";
                    rawPayload = "{" +
                            "\"customer_number\": \"%s\"," +
                            "\"biller_id\": %d" +
                            "}";
                    break;

                case "electricity":
                    inquiryEndpoint = "/electricities/postpaid-inquiries";
                    numbers = dotenv.get("POSTPAID_ELECTRICITY_NUMBERS").split(",");
                    rawPayload = "{" +
                            "\"customer_number\": \"%s\"" +
                            "}";
                    break;

                case "multifinance":
                    inquiryEndpoint = "/multifinance/inquiries";
                    needPreInquiryData = true;
                    numbers = dotenv.get("POSTPAID_MULTIFINANCE_NUMBERS").split(",");
                    preInquiryEndpoint = "/multifinance/billers";
                    rawPayload = "{" +
                            "\"customer_number\": \"%s\"," +
                            "\"biller_id\": %d" +
                            "}";
                    break;

                case "pdam":
                    inquiryEndpoint = "/pdam/inquiries";
                    needPreInquiryData = true;
                    numbers = dotenv.get("POSTPAID_PDAM_NUMBERS").split(",");
                    preInquiryEndpoint = "/pdam/operators";
                    rawPayload = "{" +
                            "\"customer_number\": \"%s\"," +
                            "\"operator_id\": %d" +
                            "}";
                    break;

                case "phone credit":
                    inquiryEndpoint = "/phone-credits/postpaid-inquiries";
                    numbers = dotenv.get("POSTPAID_PHONE_CREDIT_NUMBERS").split(",");
                    rawPayload = "{" +
                            "\"customer_number\": \"%s\"" +
                            "}";
                    break;

                case "telkom":
                    inquiryEndpoint = "/telkom-postpaids/inquiries";
                    numbers = dotenv.get("POSTPAID_TELKOM_NUMBERS").split(",");
                    rawPayload = "{" +
                            "\"customer_number\": \"%s\"" +
                            "}";
                    break;

                default:
                    break;
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }

        if (needPreInquiryData) {
            Response response = requestSpecification.get(preInquiryEndpoint);
            preInquiryData = response.path("data");
        }

        LOGGER.info("Inquiry Endpoint: " + inquiryEndpoint);
        for (String number : numbers) {
            String[] creds = number.split("\\|");
            int additionalData = 0;

            if (needPreInquiryData) {
                number = creds[1];
                additionalData = getPostpaidOptions(preInquiryData, creds[0]);
            } else {
                number = creds[0];
            }

            payload = String.format(rawPayload, number, additionalData);

            Response response = requestSpecification.body(payload).post(inquiryEndpoint);

            LOGGER.info("Payload: " + payload);
            LOGGER.info("Response: " + response.getBody().asString());

            try {
                PostpaidData.setCustomerNumber(response.path(customerNumberPath).toString());

                switch (tmpProduct) {
                    case "bpjs":
                        PostpaidData.setMonth(Integer.parseInt(response.path("data.paid_until.month").toString()));
                        break;

                    case "credit card bill":
                        PostpaidData.setBank(response.path("data.biller.name").toString());
                        break;

                    case "cable tv":
                    case "multifinance":
                        PostpaidData.setBiller(response.path("data.biller.name").toString());
                        break;

                    case "pdam":
                        PostpaidData.setArea(response.path("data.operator.name").toString());
                        break;

                    default:
                        break;
                }

                break;
            } catch (Exception ex) {
                continue;
            }
        }

        if (PostpaidData.getCustomerNumber() == null) {
            // The message "All customer numbers are unavailable!" has been removed due to the method only accept boolean param.
            Assume.assumeTrue(false);
        }
    }

    public void getUserAddress() {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);

        response = requestSpecification.get("/addresses")
                .then()
                .statusCode(200).extract().response();
    }

    public void deleteNonPrimaryAddress(String credentialUser) {
        setUserAuthv4(credentialUser);
        getUserAddress();
        ArrayList idAddresses = response.path("data.id");
        ArrayList isPrimaryAddress = response.path("data.primary");

        Object[] arrayObject = idAddresses.toArray();
        for (int i = 0; i < arrayObject.length; i++) {
            if (isPrimaryAddress.get(i).toString().equals("false")) {
                arrayObject[i] = arrayObject[i].toString().replace("[", "").replace("]", "");
                response = requestSpecification.delete("/addresses/" + arrayObject[i].toString());
            }
        }
    }

    public String getStoreId(String storeUsername) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);

        response = requestSpecification.get("/stores/" + storeUsername + "/profile");
        LOGGER.info(("Response Is: " + response.getBody().asString()));
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        String storeId = response.path("data.id").toString();
        return storeId;
    }

    public String getProductId(String storeId, String itemName) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);

        response = requestSpecification.get("/products?store_id=" + storeId + "&keywords=" + itemName);

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        ArrayList<Map<String, Object>> product = response.path("data");
        if (product.size() != 1) {
            throw new NullPointerException(
                    "There's " + product.size() + " item with Keyword: " + itemName + "\n" +
                            response.getBody().asString());
        }
        String productId = product.get(0).get("id").toString();
        return productId;
    }

    public int getItemPrice(String storeId, String itemName) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);

        response = requestSpecification.get("/products?store_id=" + storeId + "&keywords=" + itemName);
        LOGGER.info(("Response Is: " + response.getBody().asString()));
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        ArrayList<Map<String, Object>> product = response.path("data");
        if (product.size() != 1) {
            throw new NullPointerException(
                    "There's more than 1 item or no item with Keyword: " + itemName + "\n" +
                            response.getBody().asString());
        }
        return Integer.parseInt(product.get(0).get("price").toString());
    }

    public String getSellerName(String storeId) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);

        response = requestSpecification.get("/stores/" + storeId);

        LOGGER.info(("Response Seller Name Is: " + response.getBody().asString()));
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        Map<String, Object> store = response.path("data");
        return store.get("name").toString();
    }


    public void addItemToCart(DataTable cartDetail) {
        Map<String, String> cd = cartDetail.asMap(String.class, String.class);
        String idStore = getStoreId(cd.get("STORE_USERNAME"));
        String idProduct = getProductId(idStore, cd.get("PRODUCT_NAME"));
        String storeName = getSellerName(idStore);
        int itemPrice = getItemPrice(idStore, cd.get("PRODUCT_NAME"));

        CartData.setQuantityItem(Integer.parseInt(cd.get("QTY")));
        CartData.setExpectedProductName(cd.get("PRODUCT_NAME"));
        CartData.setItemPrice(itemPrice);
        CartData.setStoreName(storeName);
        CheckoutData.setProductName(cd.get("PRODUCT_NAME"));
        CheckoutData.setProductName(cd.get("PRODUCT_NAME"));
        CheckoutData.setProductPrice(itemPrice);
        CheckoutData.setProductQuantity(cd.get("QTY"));

        Map<String, Object> cart_detail = new HashMap<>();
        cart_detail.put("STORE_USERNAME", cd.get("STORE_USERNAME"));
        cart_detail.put("STORE_NAME", storeName);
        cart_detail.put("PRODUCT_NAME", cd.get("PRODUCT_NAME"));
        cart_detail.put("ITEM_PRICE", itemPrice);
        cart_detail.put("QTY", cd.get("QTY"));

        ArrayList<Map<String, Object>> currentCartDetails = CartData.getCartDetails();
        currentCartDetails.add(cart_detail);
        CartData.setCartDetails(currentCartDetails);
        CheckoutData.setCartDetails(currentCartDetails);

        Map<String, Object> payload = new HashMap<>();
        payload.put("quantity", cd.get("QTY"));
        payload.put("product_id", idProduct);

        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity())
                .body(payload)
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.post("/carts/items/");
        if (response.statusCode() != 201) {
            throw new NullPointerException(response.getBody().asString());
        }
        LOGGER.info(("Response Is: " + response.getBody().asString()));
        CartData.setItemPrice(getItemPrice(idStore, cd.get("PRODUCT_NAME")));
        CartData.setQuantityItem(Integer.parseInt(cd.get("QTY")));
    }

    public void addNewAddress(DataTable addressDetail) {
        Map<String, String> address = addressDetail.asMap(String.class, String.class);
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("title", address.get("Label"));
        payload.put("name", address.get("Name"));
        payload.put("phone", address.get("Phone Number"));

        Map<String, Object> addressObject = new HashMap<String, Object>();
        addressObject.put("province", address.get("Province"));
        addressObject.put("city", address.get("City"));
        addressObject.put("district", address.get("District"));
        addressObject.put("address", address.get("Full Address"));
        addressObject.put("postal_code", address.get("Postal Code"));
        addressObject.put("latitude", null);
        addressObject.put("longitude", null);

        payload.put("address", addressObject);

        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .body(payload)
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.post("/addresses");


        if (response.statusCode() != 201) {
            throw new NullPointerException(response.getBody().asString());
        }
        LOGGER.info(("Response Is: " + response.getBody().asString()));

        CartData.setExpectedTitle(address.get("Label"));
        CartData.setExpectedName(address.get("Name"));
        CartData.setExpectedPhone(address.get("Phone Number"));
        CartData.setExpectedProvince(address.get("Province"));
        CartData.setExpectedCity(address.get("City"));
        CartData.setExpectedDistrict(address.get("District"));
        CartData.setExpectedAddress(address.get("Full Address"));
        CartData.setExpectedPostCode(address.get("Postal Code"));
    }

    public void setStoreStatus(String user, String status) {
        String storeId = getStoreId(dotenv.get(user + "_USERNAME"));
        Map<String, Object> payload = new HashMap<>();
        payload.put("state", status);
        if (status.equals("close")) {
            Map<String, Object> stateOptions = new HashMap<>();
            stateOptions.put("closed_start", LocalDate.now().toString());
            stateOptions.put("closed_end", LocalDate.now().plusDays(1L).toString());
            stateOptions.put("closed_reason", "Maaf sedang tutup lapaknya");
            payload.put("state_options", stateOptions);
            List<Map<String, Object>> itemOnCart = CartData.getCartDetails()
                    .stream()
                    .filter(i -> !i.get("STORE_USERNAME").equals(user.toLowerCase()))
                    .collect(Collectors.toList());
            ArrayList<Map<String, Object>> tempArray = new ArrayList<Map<String, Object>>(itemOnCart);
            CartData.setInvalidItemQty(CartData.getInvalidItemQty() + (CartData.getCartDetails().size() - itemOnCart.size()));
            CartData.setCartDetails(tempArray);
        }

        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .body(payload)
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.patch("/stores/" + storeId + "/status");

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
    }

    public void sendMessage(String typeMessage, String user) {
        sendMessage(typeMessage, user, "public");
    }

    public void sendMessage(String typeMessage, String user, String typeEndpoint) {
        String userID = dotenv.get(user + "_ID");
        Map<String, Object> payload = new HashMap<>();
        switch (typeMessage) {
            case "text":
                payload.put("partner_id", userID);
                payload.put("content", CHATData.validMessage);
                break;
            case "product link":
                payload.put("partner_id", userID);
                payload.put("content", CHATData.productLink);
                break;
            case "grey keyword":
                payload.put("partner_id", userID);
                payload.put("content", CHATData.greyKeyword);
                break;
            case "black keyword":
                payload.put("partner_id", userID);
                payload.put("content", CHATData.blackKeyword);
                break;
            case "product type":
                Map<String, Object> type = new HashMap<>();
                type.put("id", dotenv.get("CHAT_PRODUCT_NUMBER_ID"));
                type.put("name", dotenv.get("CHAT_PRODUCT_NAME"));
                type.put("reference_type", "product_type_chat");
                type.put("type", "product");
                type.put("url", dotenv.get("CHAT_PRODUCT_URL"));

                List<Map<String, Object>> payloadTypes = new ArrayList<Map<String, Object>>();
                payloadTypes.add(type);

                payload.put("partner_id", userID);
                payload.put("content", CHATData.validMessage);
                payload.put("types", payloadTypes);
                break;
            default:
                break;
        }

        String url = ("exclusive".equals(typeEndpoint) ? "/_exclusive" : "") + "/chat/messages";
        sendRequestWithBody("post", url, payload);
        response.then().assertThat().statusCode(201);
    }

    public void deleteChatRoom(String user) {
        String userID = dotenv.get(user + "_ID");
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();
        LOGGER.debug(requestSpecification);
        response = requestSpecification.delete("/chat/rooms?partner_ids=" + userID);
    }

    public String getProductIdFromStore(String itemName, Boolean sold) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);

        if (sold == true) {
            response = requestSpecification.get("/stores/me/products?keywords=" + itemName + "&product_type=sold");
        } else {
            response = requestSpecification.get("/stores/me/products?keywords=" + itemName);
        }

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        ArrayList<Map<String, Object>> product = response.path("data");
        if (product.size() != 1) {
            throw new NullPointerException(
                    "There's " + product.size() + " item with Keyword: " + itemName + "\n" +
                            response.getBody().asString());
        }
        return product.get(0).get("id").toString();
    }

    public void setQuantityItemOnStore(String itemName, int qty, Boolean sold) {
        String productId = getProductIdFromStore(itemName, sold);
        String url = String.format("/products/%s", productId);
        Map<String, Object> payload = new HashMap<>();
        payload.put("stock", qty);

        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .body(payload)
                .log().all();

        LOGGER.debug(requestSpecification);

        response = requestSpecification.patch(url);

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        if (qty == 0) {
            List<Map<String, Object>> itemOnCart = CartData.getCartDetails()
                    .stream()
                    .filter(i -> !i.get("PRODUCT_NAME").equals(itemName))
                    .collect(Collectors.toList());
            ArrayList<Map<String, Object>> tempArray = new ArrayList<Map<String, Object>>(itemOnCart);
            CartData.setCartDetails(tempArray);
            CartData.setInvalidItemQty(CartData.getInvalidItemQty() + 1);
        }
    }

    public String getProductIdFromStore(String itemName) {
        ArrayList<Map<String, Object>> productList;
        String path = String.format("/stores/me/products?keywords=%1$s", itemName);
        sendRequest("get", path);
        LogUtil.info(("Response Is: " + APIData.getResponse().getBody().asString()));

        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }

        productList = APIData.getResponse().path("data");

        if (productList.isEmpty()) {
            path += "&product_type=sold";
            sendRequest("get", path);
            LogUtil.info(("Response Is: " + APIData.getResponse().getBody().asString()));
            productList = APIData.getResponse().path("data");
        }

        ArrayList<Map<String, Object>> product = new ArrayList<>();

        // filtering product based on keyword
        for (Map<String, Object> dataProduct : productList) {
            if (dataProduct.get("name").equals(itemName)) product.add(dataProduct);
        }

        if (product.size() != 1) {
            throw new NullPointerException(
                    "There's " + product.size() + " item with Keyword: " + itemName + "\n" +
                            APIData.getResponse().getBody().asString());
        }
        return product.get(0).get("id").toString();
    }

    public void setQuantityItemOnStore(String itemName, int qty) {
        String productId = getProductIdFromStore(itemName);
        String url = String.format("/products/%s", productId);
        Map<String, Object> payload = new HashMap<>();
        payload.put("stock", qty);
        sendRequestWithBody("patch", url, payload);
        LogUtil.info(("Response Is: " + APIData.getResponse().getBody().asString()));
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        if (qty == 0) {
            List<Map<String, Object>> itemOnCart = CartData.getCartDetails()
                    .stream()
                    .filter(i -> !i.get("PRODUCT_NAME").equals(itemName))
                    .collect(Collectors.toList());
            ArrayList<Map<String, Object>> tempArray = new ArrayList<Map<String, Object>>(itemOnCart);
            CartData.setCartDetails(tempArray);
            CartData.setInvalidItemQty(CartData.getInvalidItemQty() + 1);
        }
    }


    public void setTransactionState(String transactionId, String state) {
        Map<String, Object> payload = new HashMap<String, Object>();
        Map<String, Object> state_options = new HashMap<String, Object>();
        payload.put("state", state);

        if (state.equals("rejected")) {
            state_options.put("reject_reason", "Ada kesibukan lain yang sifatnya mendadak");
            payload.put("state_options", state_options);
        } else if (state.equals("cancelled")) {
            state_options.put("reason", "other");
            state_options.put("notes", "cancel transaction");
            payload.put("state_options", state_options);
        }

        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .body(payload)
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.put("/transactions/" + transactionId + "/status");
        if (response.statusCode() == 200 || response.statusCode() == 202) {
            LOGGER.info("Success Set Transaction, Response Is: " + response.getBody().asString());
            return;
        }
        LOGGER.info(("Failed to set Transaction State to " + state + ", with Status Code: " + response.getBody().asString()));
        throw new NullPointerException(response.getBody().asString());
    }

    public void getInvoices() {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);
        response = requestSpecification.get("/invoices");
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        LOGGER.info(("Transaction: " + response.getBody().asString()));
    }

    public String getSellerUsername(String storeId) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);
        response = requestSpecification.get("/stores/" + storeId);
        LOGGER.info(("Response Seller Username Is: " + response.getBody().asString()));

        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        return response.path("data.owner.username").toString();
    }

    public void retrieveTransaction(String transactionId) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);
        response = requestSpecification.get("/transactions/" + transactionId);
        DANAData.setInvoiceID(response.path("data.invoice_id").toString());

        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        LOGGER.info(("Transaction: " + response.getBody().asString()));
    }

    public void cancelPaidInvoice() {
        try {
            getInvoices();
            ArrayList<Map<String, Object>> invoices = response.path("data");
            String transactionId = ((ArrayList<Map<String, Object>>) invoices
                    .stream()
                    .filter(i -> i.get("state").equals("paid"))
                    .findFirst()
                    .map(i -> i.get("transactions"))
                    .get()).get(0).get("id").toString();
            setTransactionState(transactionId, "cancelled");
            retrieveTransaction(transactionId);
            setUserAuthv4(getSellerUsername(response.path("data.store.id").toString()) + "_STORE");
            setTransactionState(transactionId, "cancelled");
        } catch (Exception e) {
            LOGGER.info("Failed to cancel paid transaction, Error: " + e);
        }
    }

    public void cancelUnpaidInvoice(String invoiceID) {
        Map<String, Object> payload = new HashMap<String, Object>();
        Map<String, Object> state_options = new HashMap<String, Object>();
        payload.put("state", "cancelled");
        state_options.put("reason", "other");
        state_options.put("notes", "cancel unpaid invoice");
        payload.put("state_options", state_options);
        sendRequestWithBody("patch", "/invoices/" + invoiceID + "/status", payload);
        response = APIData.getResponse();

        if (response.statusCode() == 200 || response.statusCode() == 202) {
            LOGGER.info("Success cancel unpaid Invoice, Response Is: " + response.getBody().asString());
        } else {
            LOGGER.info(("Failed to cancel unpaid Invoice, with Status Code: " + response.getBody().asString()));
            throw new NullPointerException(response.getBody().asString());
        }
    }

    public void retrievePaymentsInfo() {
        try {
            requestSpecification = RestAssured.given()
                    .baseUri(dotenv.get("API_URL"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + APIData.getAccessToken())
                    .header("Accept", "application/vnd.bukalapak.v4+json")
                    .log().all();

            LOGGER.debug(requestSpecification);
            response = requestSpecification.get("/_exclusive/info/payments");

            if (response.statusCode() != 200) {
                LOGGER.info("Failed with Status Code : " + response.getBody().asString());
            }
            LOGGER.info("Payment Info" + response.getBody().asString());
            ArrayList<Map<String, Object>> paymentsInfo = response.path("data");
            TransactionData.setPaymentsinfo(paymentsInfo);
        } catch (Exception e) {
            LOGGER.info("Failed to retrieve Payment Info");
        }
    }

    public void retrievePaymentsInfoVirtualAccount() {
        try {
            requestSpecification = RestAssured.given()
                    .baseUri(dotenv.get("API_URL"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + APIData.getAccessToken())
                    .header("Accept", "application/vnd.bukalapak.v4+json")
                    .log().all();

            LOGGER.debug(requestSpecification);
            response = requestSpecification.get("/_exclusive/info/payments/virtual-accounts");

            if (APIData.getResponse().statusCode() != 200) {
                LOGGER.info("Failed with Status Code : " + response.getBody().asString());
            }
            LOGGER.info("Payment Info Virtual Account" + response.getBody().asString());
            ArrayList<Map<String, Object>> paymentsInfoVirtualAccount = response.path("data");
            TransactionData.setPaymentsinfoVirtualAccount(paymentsInfoVirtualAccount);
        } catch (Exception e) {
            LOGGER.info("Failed to retrieve Payment Info Virtual Account");
        }
    }

    public String getDANAPocketDetails() {
        try {
            requestSpecification = RestAssured.given()
                    .baseUri(dotenv.get("API_URL"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + APIData.getAccessToken())
                    .header("Accept", "application/vnd.bukalapak.v4+json")
                    .log().all();

            LOGGER.debug(requestSpecification);
            response = requestSpecification.get("/_exclusive/e-wallets/dana/pockets");

            if (response.statusCode() != 200) {
                LOGGER.info("Failed with Status Code : " + response.getBody().asString());
            }
            LOGGER.info("DANA Pocket" + response.getBody().asString());
            return response.path("data[0].label");
        } catch (Exception e) {
            LOGGER.info("Failed to retrieve DANA Pocket");
            return null;
        }
    }

    public int getDANAPocketTotal() {
        try {
            requestSpecification = RestAssured.given()
                    .baseUri(dotenv.get("API_URL"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + APIData.getAccessToken())
                    .header("Accept", "application/vnd.bukalapak.v4+json")
                    .log().all();

            LOGGER.debug(requestSpecification);
            response = requestSpecification.get("/_exclusive/e-wallets/dana/pockets");

            if (response.statusCode() != 200) {
                LOGGER.info("Failed with Status Code : " + response.getBody().asString());
            }
            LOGGER.info("Total DANA Pocket " + response.path("meta.total"));
            return (int) response.path("meta.total");
        } catch (Exception e) {
            LOGGER.info("Failed to retrieve DANA Pocket");
            return 0;
        }
    }

    public void retrieveCart() {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity())
                .log().all();
        LOGGER.debug(requestSpecification);
        response = requestSpecification.get("/carts");
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        LOGGER.info(("Response Is: " + response.getBody().asString()));
    }

    private HashMap getMapResponsePath(String path) {
        return response.path(path);
    }

    public String getSelectedUserAddressId(String address) {
        String addr = "";
        getUserAddress();
        ArrayList<Map<String, Object>> addresses = response.path("data");
        if (address.toLowerCase().equals("primary")) {
            addr = addresses.stream()
                    .filter(
                            t -> t.get("primary").equals(true)
                    ).collect(Collectors.toList())
                    .get(0)
                    .get("id").toString();
        } else if (address.toLowerCase().equals("last_used")) {
            addr = addresses
                    .get(0)
                    .get("id").toString();
        }
        if (addr.equals("")) {
            throw new NullPointerException("No Such Address: \n" + response.getBody().asString());
        }
        return addr;
    }

    public void createTransaction(DataTable transactionDetail) {
        Map<String, String> td = transactionDetail.asMap(String.class, String.class);
        ArrayList<String> itemIds = new ArrayList<String>();
        addItemToCart(transactionDetail);
        retrieveCart();
        itemIds.add(getMapResponsePath("data.items[0]").get("id").toString());

        String carrier = (td.get("CARRIER") == null) ? "J&T REG" : td.get("CARRIER");
        registerTransaction(
                getStoreId(td.get("STORE_USERNAME")),
                itemIds,
                carrier,
                getSelectedUserAddressId(td.get("ADDRESS"))
        );
        String transactionId = getMapResponsePath("data").get("id").toString();
        CSIData.setNomorTransaksi(getMapResponsePath("data").get("transaction_id").toString());
        TransactionData.setIdTransaksi(transactionId);
        TransactionData.setNomorTransaksi(getMapResponsePath("data").get("transaction_id").toString());
        CheckoutData.setTrxId(transactionId);
        registerInvoice(transactionId, td.get("PAYMENT_TYPE"));
    }

    public void registerTransaction(String storeId, ArrayList<String> itemIds, String carrier, String addressId) {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("store_id", storeId);
        payload.put("item_ids", itemIds);
        payload.put("carrier", carrier);
        Map<String, Object> consignee = new HashMap<String, Object>();
        consignee.put("id", addressId);
        payload.put("consignee", consignee);
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity())
                .body(payload)
                .log().all();
        LOGGER.debug(requestSpecification);
        response = requestSpecification.post("/transactions");
        if (response.statusCode() != 201) {
            throw new NullPointerException(response.getBody().asString());
        }
        LOGGER.info(("Response Is: " + response.getBody().asString()));
    }

    public void registerInvoice(String transactionId, String paymentType) {
        Map<String, Object> payload = new HashMap<String, Object>();
        ArrayList<Map<String, Object>> transactions = new ArrayList<Map<String, Object>>();
        Map<String, Object> transaction = new HashMap<String, Object>();
        String tmpPaymentType = paymentType;
        transaction.put("id", transactionId);
        transaction.put("type", "product");
        transactions.add(0, transaction);
        if (paymentType.startsWith("va")) {
            String bank = paymentType.substring(3);
            tmpPaymentType = "virtual_account";
            payload.put("virtual_account_bank", bank);
        }
        payload.put("transactions", transactions);
        payload.put("payment_type", tmpPaymentType);
        sendRequestWithBody("post", "/invoices", payload);
        response = APIData.getResponse();

        if (response.statusCode() != 201) {
            throw new NullPointerException(response.getBody().asString());
        }
        LOGGER.info(("Response Is: " + response.getBody().asString()));
        String invoiceId = getMapResponsePath("data").get("payment_id").toString();
        CSIData.setNomorTagihan(invoiceId);
        CheckoutData.setInvoiceNumber(invoiceId);
    }

    public void makePayment(String paymentId, String method) {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .body("")
                .log().all();
        LOGGER.debug(requestSpecification);
        response = requestSpecification.post(String.format("/payments/%1s/%1s", paymentId, method));
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        LOGGER.info(("Response Is: " + response.getBody().asString()));
    }

    public void setPushBalance(String credentialUser) {
        setUserAuthv4(credentialUser);
        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity());
        LOGGER.debug(requestSpecification);
        response = requestSpecification.get("/pushes/balance");
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        PROMData.setSisaPush(Integer.parseInt(getMapResponsePath("data").get("balance").toString()));
        LOGGER.info("Sisa Push : " + PROMData.getSisaPush());
    }

    public String[] getDANARefundMutation(String payMethod, String user) throws InterruptedException {
        setUserAuthv4(user);
        String url = "/_exclusive/e-wallets/dana/mutations";

        if (payMethod.equals("Credits")) url = "/_exclusive/e-wallets/dana/credits/mutations";
        for (int i = 0; i < 10; i++) {
            sendRequest("get", url + "?offset=0&limit=1");
            if (getResponsePath("data[0].type").equals("credit")) break;
            //wait 5s to recheck DANA refund mutation
            Thread.sleep(5000);
        }

        if (getResponsePath("data[0].type").equals("credit")) {
            String type = getResponsePath("data[0].type");
            String amount = getResponsePath("data[0].amount");

            if (payMethod.equals("Credits")) {
                String status = getResponsePath("data[0].status");
                String description = getResponsePath("data[0].description");
                return new String[]{amount, type, status, description};
            } else {
                String action = getResponsePath("data[0].action");
                String invoice = getResponsePath("data[0].action_reference_no");
                return new String[]{amount, type, action, invoice};
            }
        } else {
            Assert.fail("failed to get dana refund mutation, type = " + getResponsePath("data[0].type"));
            return new String[0];
        }
    }

    public void unbindDANA(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("delete", "/_exclusive/e-wallets/dana/profile");
        response = APIData.getResponse();

        if (response.statusCode() == 200) {
            LOGGER.info("unbind success");
        } else if (response.statusCode() == 404) {
            LOGGER.info("user already unbound");
        } else {
            Assert.fail("unbind error: " + response.getBody().asString());
        }
    }

    /**
     * this method is used to set the headers for API call
     */
    private void setHeaders() {
        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("If-None-Match", "")
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", "identity-api-testing");
        APIData.setRequestSpecification(requestSpecification);
    }

    /**
     * this method is used to send any request for API call
     *
     * @param method   api call method
     * @param endpoint endpoint to be hit
     */
    private void sendRequest(String method, String endpoint) {
        LOGGER.info("endpoint :" + endpoint);
        setHeaders();
        switch (method.toLowerCase()) {
            case "get":
                response = APIData.getRequestSpecification().get(endpoint);
                break;
            case "post":
                response = APIData.getRequestSpecification().post(endpoint);
                break;
            case "put":
                response = APIData.getRequestSpecification().put(endpoint);
                break;
            case "delete":
                response = APIData.getRequestSpecification().delete(endpoint);
                break;
            case "patch":
                response = APIData.getRequestSpecification().patch(endpoint);
                break;
            default:
                LogUtil.info(method + "is not defined");
                break;
        }
        APIData.setResponse(response);
        LOGGER.info(("Response " + method + " " + endpoint + ": " + APIData.getResponse().getBody().asString()));
    }

    /**
     * this method is used to send any request for API call using request body
     *
     * @param method   api call method
     * @param endpoint endpoint to be hit
     * @param body     request body
     */
    private void sendRequestWithBody(String method, String endpoint, Object body) {
        setHeaders();
        String payload = constructJson(body);
        switch (method.toLowerCase()) {
            case "get":
                response = APIData.getRequestSpecification().body(payload).get(endpoint);
                break;
            case "post":
                response = APIData.getRequestSpecification().body(payload).post(endpoint);
                break;
            case "put":
                response = APIData.getRequestSpecification().body(payload).put(endpoint);
                break;
            case "delete":
                response = APIData.getRequestSpecification().body(payload).delete(endpoint);
                break;
            case "patch":
                response = APIData.getRequestSpecification().body(payload).patch(endpoint);
                break;
            default:
                LogUtil.info(method + "is not defined");
                break;
        }
        APIData.setResponse(response);
        LOGGER.info(("Response " + method + " " + endpoint + ": " + APIData.getResponse().getBody().asString()));
    }

    /**
     * this method is used to get response as array
     *
     * @param path response path to get
     * @return response path as array
     */
    private ArrayList<String> getArrayResponsePath(String path) {
        return APIData.getResponse().path(path);
    }

    /**
     * this method is used to get response as array
     *
     * @param path response path to get
     * @return response path as array
     */
    private ArrayList<Integer> getArrayResponsePathInteger(String path) {
        return APIData.getResponse().path(path);
    }

    /**
     * this method is used to construct json for request body
     *
     * @return json representation
     */
    private String constructJson(Object requestBody) {
        Gson rawPayload = new Gson();
        return rawPayload.toJson(requestBody);
    }

    private String getResponsePath(String path) {
        return APIData.getResponse().path(path).toString();
    }

    public void removeAllPromotedPushProducts(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/paid-promotion/promoted-pushes/products");
        LOGGER.info(APIData.getResponse().asString());
        ArrayList idItems = getArrayResponsePath("data.id");
        ArrayList statusItems = getArrayResponsePath("data.promoted_details.active");
        ArrayList<Integer> position = new ArrayList<>();
        // To get the position of active product
        for (int i = 0; i < idItems.size(); i++) {
            if (statusItems.get(i).equals(true)) {
                position.add(i);
            }
        }
        ArrayList<String> idActiveItems = new ArrayList<>();
        // To get id of the active products
        if (!position.isEmpty()) {
            for (int i = 0; i < position.size(); i++) {
                idActiveItems.add(idItems.get(position.get(i)).toString());
                LOGGER.info("ID barang " + idActiveItems.get(i));
            }
        }
        // To unset or the product
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("bid_value", 400);
        requestBody.put("start_time", LocalDate.now().toString() + "T17:00:00.000Z");
        requestBody.put("end_time", LocalDate.now().plusDays(7).toString() + "T17:00:00.000Z");
        requestBody.put("active", false);

        if (!idActiveItems.isEmpty()) {
            for (Object idItem : idActiveItems) {
                String url = "/promoted-pushes/products/" + idItem.toString();
                sendRequestWithBody("patch", url, requestBody);
                response.then().assertThat().statusCode(200);
                LOGGER.info("Promoted with id : " + idItem.toString() + " has been unset!");
            }
        } else {
            LOGGER.warn("No active products are found!");
        }
    }

    public String getIdPromotedGrup(String campaignTitle) {
        sendRequest("get", "/promoted-pushes/campaigns");
        LOGGER.info(APIData.getResponse().asString());
        // Get id for the item
        ArrayList idItems = getArrayResponsePath("data.id");
        ArrayList itemsName = getArrayResponsePath("data.name");
        String idItem = "";
        for (int i = 0; i < idItems.size(); i++) {
            if (itemsName.get(i).equals(campaignTitle)) {
                idItem = idItems.get(i).toString();
                break;
            }
        }
        return idItem;
    }

    public void updateStatusPromotedSatuan(String action, String productName) {
        String idProduct = getProductID(productName);
        String url = "/promoted-pushes/products/" + idProduct;
        Map<String, Object> payload = new HashMap<>();
        if (action.equalsIgnoreCase("activated")) {
            payload.put("active", true);
        }
        else {
            payload.put("active", false);
        }
        payload.put("bid_value", 200);
        payload.put("start_time", LocalDate.now().toString() + "T00:00:00.000Z");
        payload.put("end_time", LocalDate.now().plusDays(7).toString() + "T23:59:59.999Z");
        sendRequestWithBody("patch", url, payload);
        responseStatusCodeShouldBe(200);
    }

    public void updateStatusPromotedGroup(String campaignTitle, String action) {
        String idPromotedGrup = getIdPromotedGrup(campaignTitle);
        String campaignStatus = getStatusPromotedGroup(campaignTitle);
        String url = "/promoted-pushes/campaigns/" + idPromotedGrup + "/status";
        if (action.equals("deactivated")) {
            if (campaignStatus.equals("active")) {
                sendRequest("patch", url);
            }
        } else {
            if (campaignStatus.equals("dormant")) {
                sendRequest("patch", url);
            }
        }
        responseStatusCodeShouldBe(200);
    }

    public String getStatusPromotedGroup(String campaignTitle) {
        sendRequest("get", "/promoted-pushes/campaigns");
        ArrayList campaignTitles = getArrayResponsePath("data.name");
        ArrayList campaignStates = getArrayResponsePath("data.state");
        String campaignState = "";
        for (int i = 0; i < campaignTitles.size(); i++) {
            if (campaignTitles.get(i).equals(campaignTitle)) {
                campaignState = campaignStates.get(i).toString();
                break;
            }
        }
        return campaignState;
    }

    private String getProductIDWithOffset(String productName, int offset) {
        sendRequest("get", "/stores/me/products?limit=90&offset=" + offset + "&product_type=available&sort=name");
        // Get id for the item
        LOGGER.info("Response GET product is: " + APIData.getResponse().getBody().asString());
        ArrayList idItems = getArrayResponsePath("data.id");
        ArrayList itemsName = getArrayResponsePath("data.name");
        String idItem = "";
        for (int i = 0; i < idItems.size(); i++) {
            if (itemsName.get(i).equals(productName)) {
                idItem = idItems.get(i).toString();
                break;
            }
        }
        LOGGER.info("Response GET product id: " + idItem);
        return idItem;
    }

    private String getProductID(String productName) {
        // Get product id up to 3 times (checking up to 270 products)
        int offset = 0;
        int limitTry = 0;
        String productID = "";
        while (productID.isEmpty() && limitTry < 3) {
            productID = getProductIDWithOffset(productName, offset);
            // 90 is the default limit get products
            offset = offset + 90;
            limitTry++;
        }
        if (productID.isEmpty()) {
            LOGGER.warn("product id for " + productName + " can not be found after 3 times of trying or checking 270 products!");
        }
        return productID;
    }

    public void setPromotedPushProduct(String credentialUser, String productName, String bid) {
        setUserAuthv4(credentialUser);
        String idProduct = getProductID(productName);
        // To promote product
        Map<String, Object> payload = new HashMap<>();
        payload.put("active", true);
        payload.put("bid_value", Integer.parseInt(bid));
        payload.put("start_time", LocalDate.now().toString() + "T00:00:00.000Z");
        payload.put("end_time", LocalDate.now().plusDays(7).toString() + "T23:59:59.999Z");
        String url = "/promoted-pushes/products/" + idProduct;
        sendRequestWithBody("patch", url, payload);
        response.then().assertThat().statusCode(200);
        LOGGER.info("Promoted with id : " + idProduct + " has been set!");
    }

    public void patchPromotedKeywords(String productName) {
        String idProduct = getProductID(productName);
        String url = "/paid-promotion/promoted-keywords/products/" + idProduct + "/keywords";
        sendRequest("patch", url);
        // In case failed, we'll try up to 2 times to hit the endpoint
        if (response.statusCode() != 200) {
            for (int i = 0; i < 3; i++) {
                sendRequest("patch", url);
                if (response.statusCode() == 200) {
                    break;
                } else {
                    LOGGER.warn("Keywords cant be created! Please check!");
                }
            }
        }
    }

    public void setPromotedKeywordProduct(String productName, boolean state, String bid, String keyword) {
        String idProduct = getProductID(productName);
        // get existing keyword list included bid
        JsonArray listkeywords = getExistingPromotedKeywordSettings(idProduct);
        // set period and budget limit as default
        JsonObject promPeriod = new JsonObject();
        promPeriod.addProperty("all_time", true);
        JsonObject budgetLimit = new JsonObject();
        budgetLimit.addProperty("unlimited", true);
        budgetLimit.addProperty("limit_amount", 0);
        // prepare data new keyword
        JsonObject newKeyword = new JsonObject();
        newKeyword.addProperty("keyword", keyword);
        newKeyword.addProperty("bid_value", Integer.parseInt(bid));
        // add new keyword to settings
        listkeywords.add(newKeyword);
        // prepare all data settings
        JsonObject payload = new JsonObject();
        payload.addProperty("product_id", idProduct);
        payload.addProperty("active", state);
        payload.add("promotion_period", promPeriod);
        payload.add("budget_limit", budgetLimit);
        payload.add("keywords", listkeywords);
        String url = "/paid-promotion/promoted-keywords";
        sendRequestWithBody("patch", url, payload);
        // In case failed, we'll try up to 2 times to hit the endpoint
        if (response.statusCode() != 200) {
            for (int i = 0; i < 3; i++) {
                sendRequestWithBody("patch", url, payload);
                if (response.statusCode() == 200) {
                    break;
                } else {
                    LOGGER.warn("Promoted Keyword campaign cant be created! Please check!");
                }
            }
        }
        LOGGER.info("Promoted keyword with id : " + idProduct + " has been set!");
    }

    private JsonArray getExistingPromotedKeywordSettings(String idProduct) {
        JsonArray listExistingKeyword = new JsonArray();
        sendRequest("get", "/paid-promotion/promoted-keywords?product_id=" + idProduct);
        LOGGER.info("Response GET promoted keyword settings: " + APIData.getResponse().getBody().asString());
        ArrayList keywordsItems = getArrayResponsePath("data.keywords.keyword");
        ArrayList bidValueItems = getArrayResponsePath("data.keywords.bid_value");
        JsonObject objectKeyword = new JsonObject();
        for (int i = 0; i < keywordsItems.size(); i++) {
            objectKeyword.addProperty("keyword", keywordsItems.get(i).toString());
            objectKeyword.addProperty("bid_value", Integer.parseInt(bidValueItems.get(i).toString()));
            listExistingKeyword.add(objectKeyword);
        }
        return listExistingKeyword;
    }

    public void getPromotedKeywordProduct(String productName, boolean state, String bid, String keyword) {
        String idProduct = getProductID(productName);
        //check promoted keyword product is actived / deactived
        sendRequest("get", "/paid-promotion/promoted-keywords?product_id=" + idProduct);
        responsePathShouldBe("data.active", state);
        ArrayList keywordsItems = getArrayResponsePath("data.keywords.keyword");
        ArrayList bidValueItems = getArrayResponsePath("data.keywords.bid_value");
        String actualSelectedBidValue = "0";
        //check selected keyword should be match with bid value
        for (int i = 0; i < keywordsItems.size(); i++) {
            if (keywordsItems.get(i).equals(keyword)) {
                actualSelectedBidValue = bidValueItems.get(i).toString();
                break;
            }
        }
        JSONAssert.assertEquals("Bid value is: ", bid, String.valueOf(actualSelectedBidValue).replaceAll("[^0-9]", ""));
        responseStatusCodeShouldBe(200);
    }

    public int getWalletAmount(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/wallet");
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        LOGGER.info("availabe balance " + getResponsePath("data.available_balance"));
        return Integer.parseInt(getResponsePath("data.available_balance"));
    }

    public int getDanaBalance(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/_exclusive/e-wallets/dana/profile");
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        LOGGER.info("Dana balance " + getResponsePath("data.balance"));
        return Integer.parseInt(getResponsePath("data.balance"));
    }

    public int getCreditsBalance(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/wallet");
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        LOGGER.info("credits balance " + getResponsePath("data.nonwithdrawable_balance"));
        return Integer.parseInt(getResponsePath("data.nonwithdrawable_balance"));
    }

    public int getDompetBalance(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/wallet");
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        LOGGER.info("dompet balance " + getResponsePath("data.withdrawable_balance"));
        return Integer.parseInt(getResponsePath("data.withdrawable_balance"));
    }

    public void stopPromotedPushDailyBudget(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/promoted-pushes/budget");
        String status = getResponsePathReplacedArray("data.daily_budget_schedules");
        if (!status.isEmpty()) {
            JsonObject scheduleDetail = new JsonObject();
            scheduleDetail.addProperty("action", "deactivate");
            scheduleDetail.addProperty("weekday", 0);
            JsonArray scheduleOption = new JsonArray();
            scheduleOption.add(scheduleDetail);
            JsonObject payload = new JsonObject();
            payload.add("daily_budget_schedules", scheduleOption);
            LOGGER.info(String.valueOf(payload));
            sendRequestWithBody("patch", "/promoted-pushes/daily-budget", payload);
            LOGGER.info(("Response Is: " + APIData.getResponse().getBody().asString()));
            response.then().assertThat().statusCode(200);
            LOGGER.info("User : " + credentialUser + " has stopped the daily budget!");
        } else {
            LOGGER.warn("Daily budget isn't active! Nothing to do!");
        }
    }

    public int getPromotedBudget(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/promoted-pushes/budget");
        response.then().assertThat().statusCode(200);
        LOGGER.info("budget: " + getResponsePath("data.promoted_push_balance"));
        return Integer.parseInt(getResponsePath("data.promoted_push_balance"));
    }

    public void retrievePromotedPushBudget(String credentialUser) {
        PROMData.setSisaBudget(getPromotedBudget(credentialUser));
    }

    private String getIdIklanLapak(String promotionName) {
        sendRequest("get", "/paid-promotion/store-ads");
        // Get id for the item
        LOGGER.info("Response GET Iklan lapak is: " + APIData.getResponse().getBody().asString());
        ArrayList idItems = getArrayResponsePath("data.id");
        ArrayList itemsName = getArrayResponsePath("data.name");
        String idItem = "";
        for (int i = 0; i < idItems.size(); i++) {
            if (itemsName.get(i).equals(promotionName)) {
                idItem = idItems.get(i).toString();
                break;
            }
        }
        return idItem;
    }

    public void deleteIklanLapak(String credentialUser, String promotionName) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/paid-promotion/store-ads");
        String idIklanLapak = getIdIklanLapak(promotionName);
        sendRequest("delete", "/paid-promotion/store-ads/" + idIklanLapak);
    }

    public int getIklanLapakPrice(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/paid-promotion/store-ads/rules");
        return Integer.parseInt(getResponsePath("data.cost_per_view.general"));
    }

    public void sendLogInfoToSlack(String webhookChannel) {
        String user = APIData.getLogUserID() == null ? "<!here>" : APIData.getLogUserID();
        if (APIData.getLogMessage() != null && webhookChannel != null) {
            Object payload = "{\n" + "  \"text\": \"Hi " + user + ", please check it out\\n" + APIData.getLogMessage() + "\",\n" +
                    "}";

            response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(payload)
                    .post(webhookChannel);

            LOGGER.info(response.body().prettyPrint());
            response.then().statusCode(200).extract().response();
        }
    }

    public ArrayList<String> retrieveLeadGeneratorForm(String credentialUser, int productID) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/_exclusive/insurance-lead-generators/products/" + productID + "/submission-fields");
        LOGGER.info("Response GET Form Submission: " + APIData.getResponse().getBody().asString());

        return getArrayResponsePath("data.name");
    }

    public void setUserMainAddressByTitle(String credentialUser, String selectedTitle) {
        setUserAuthv4(credentialUser);
        String matchesAddressId = "";
        // make sure titles/labels are unique or not duplicated
        getUserAddress();
        ArrayList idAddresses = response.path("data.id");
        ArrayList titleAddress = response.path("data.title");
        Object[] arrayObject = idAddresses.toArray();
        for (int i = 0; i < arrayObject.length; i++) {
            if (titleAddress.get(i).toString().equals(selectedTitle)) {
                matchesAddressId = arrayObject[i].toString().replace("[", "").replace("]", "");
            }
        }
        if (matchesAddressId.equals("")) {
            throw new NullPointerException(selectedTitle + " title is not matched with address list");
        } else {
            sendRequest("patch", "/addresses/" + matchesAddressId + "/primary");
            response.then().assertThat().body("message", containsString("Alamatmu berhasil ditetapkan sebagai alamat utama"));
            response.then().assertThat().statusCode(200);
        }
    }

    public void setAuthTokenWithoutLogin() {
        Map<String, String> keyLogin = new HashMap<>();

        keyLogin.put("client_id", APIData.getClientId());
        keyLogin.put("client_secret", APIData.getClientSecretId());
        keyLogin.put("grant_type", "client_credentials");
        keyLogin.put("scope", "public");

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getAccountsUrl())
                .contentType("application/json")
                .body(keyLogin)
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity())
                .log().all();

        response = requestSpecification.post("oauth/token")
                .then()
                .statusCode(200).extract().response();

        String token = response.path("access_token");
        String bearerToken = "Bearer " + token;
        LOGGER.info("Authorization API v4 without Login: " + bearerToken);
        APIData.setAccessToken(token);
        APIData.setAuthToken(bearerToken);
    }

    public void registerNewUser(DataTable registerDetail) {
        Map<String, String> registerData = registerDetail.asMap(String.class, String.class);
        Map<String, Object> payload = new HashMap<String, Object>();

        payload.put("phone", registerData.get("Phone"));

        // Post to get send otp code
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity())
                .body(payload)
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.post("/_exclusive/users");

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        response.then().statusCode(401).extract().response();

        payload.put("phone", registerData.get("Phone"));
        payload.put("name", registerData.get("Name"));
        payload.put("password", registerData.get("Password"));
        payload.put("password_confirmation", registerData.get("Password"));
        payload.put("username", NCAData.generateRandomUsername("ncatestingprod", 5));

        // Post to get send otp code
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Bukalapak-Identity", APIData.getBukalapakIdentity())
                .header("Bukalapak-Phone-Token", dotenv.get("VALID_OTP_CODE"))
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .body(payload)
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.post("/_exclusive/users");

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        response.then().statusCode(201).extract().response();
    }

    public void deactiveTFA(String userType) {
        String tmpUserType = userType.replaceAll(" ", "_").toUpperCase();

        Map<String, String> authLogin = new HashMap<>();

        int randomNum = 10000 + new Random().nextInt(90000);
        String randomBLIdentity = APIData.getIdentity() + randomNum;

        authLogin.put("grant_type", "password");
        authLogin.put("username", dotenv.get(tmpUserType + "_USERNAME"));
        authLogin.put("password", dotenv.get(tmpUserType + "_PASSWORD"));
        authLogin.put("client_id", APIData.getClientId());
        authLogin.put("client_secret", APIData.getClientSecretId());
        authLogin.put("scope", "public user");

        requestSpecification = RestAssured.given()
                .baseUri(APIData.getAccountsUrl())
                .body(authLogin)
                .header("Content-Type", "application/json")

                .log().all();
        LOGGER.debug(requestSpecification);

        // challenge otp first
        response = requestSpecification.post("oauth/token")
                .then()
                .statusCode(401).extract().response();

        LOGGER.info("Response Is: " + response.getBody().asString());

        // request with otp header device id
        requestSpecification = RestAssured.given()
                .baseUri(APIData.getAccountsUrl())
                .body(authLogin)
                .header("Bukalapak-Identity", randomBLIdentity)
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .header("Content-Type", "application/json")
                .header("Bukalapak-OTP-Device-ID", randomBLIdentity)
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.post("oauth/token");

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        LOGGER.info(response.body().prettyPrint());

        response.then().statusCode(401).extract().response();

        // request add otp code
        requestSpecification = RestAssured.given()
                .baseUri(APIData.getAccountsUrl())
                .body(authLogin)
                .header("Bukalapak-Identity", randomBLIdentity)
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .header("Content-Type", "application/json")
                .header("Bukalapak-OTP-Device-ID", randomBLIdentity)
                .header("Bukalapak-OTP", dotenv.get("VALID_OTP_CODE"))
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.post("oauth/token");

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        response.then().statusCode(200).extract().response();

        String bl_otp_key = response.path("otp_key");
        String token = response.path("access_token");

        LOGGER.info("Bukalapak-OTP-Key : " + bl_otp_key);
        LOGGER.info("Authorization API v4 : " + token);

        // clear hashmap
        authLogin.clear();

        // beginning to deactivate otp with insert body
        authLogin.put("state", "inactive");

        // request endpoint tfa status
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .body(authLogin)
                .header("Content-Type", "application/json")
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .header("Authorization", "Bearer " + token)
                .header("Bukalapak-OTP-Key", bl_otp_key)
                .header("Bukalapak-OTP-Device-ID", randomBLIdentity)
                .log().all();
        LOGGER.debug(requestSpecification);

        response = requestSpecification.put("/users/tfa-status");

        LOGGER.info(("Response Is: " + response.getBody().asString()));
        response.then().statusCode(200).extract().response();
    }

    public void deleteSpecificProduct(String credentialUser, String productName) {
        setUserAuthv4(credentialUser);
        if (!getProductID(productName).isEmpty()) {
            sendRequest("delete", "/products/" + getProductID(productName));
            response.then().assertThat().statusCode(202);
            LOGGER.info("Product " + productName + " successfully deleted!");
        }
    }

    public void checkTransactionStatus(String transactionId, String status) {
        sendRequest("get", String.format("/transactions/%1s", transactionId));
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        TransactionData.setNomorTransaksi(getResponsePath("data.transaction_id"));
        TransactionData.setInvoiceNo(getResponsePath("data.invoice_id"));
        LOGGER.info("invoice_id:" + TransactionData.getInvoiceNo());
        LOGGER.info("transaksi :" + TransactionData.getNomorTransaksi());
        assertEquals("Transaction status doesn't match", status, getResponsePath("data.state"));
    }

    public void getIdTransactionBasedOnCourier(String context, String courier, String state) {
        String limit = "20";
        int indexCourier = 0;
        String tempCourier;
        boolean flagBukaExpress = false;
        sendRequest("get", String.format("transactions?context=%s&offset=0&limit=%1s" + "&sort=-date" + "&states=%2s", context, limit, state));
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }

        if ((courier).toLowerCase().contains("bukaexpress")) {
            tempCourier = courier.substring(courier.indexOf(" ") + 1);
            flagBukaExpress = true;
        } else {
            tempCourier = courier;
        }
        for (int i = 0; i < Integer.parseInt(limit); i++) {
            if (getResponsePath(String.format("data[%1s]" + ".delivery.requested_carrier", i)).equals(tempCourier)) {
                if ((getResponsePath(String.format("data[%1s]" + ".delivery.white_label_courier", i)).equals("true")) && (tempCourier.equalsIgnoreCase("J&T REG"))
                        && (!flagBukaExpress)) {
                    continue;
                }
                TransactionData.setNomorTransaksi(getResponsePath(String.format("data[%1s]" + ".transaction_id", i)));
                TransactionData.setIdTransaksi(getResponsePath(String.format("data[%1s].id", i)));
                if (!getResponsePath(String.format("data[%1s].delivery.tracking_number", i)).equals("")) {
                    XPRData.setLastShippingHistory(getResponsePath(String.format("data[%1s].delivery.history[0].status", i)));
                }
                indexCourier = i;
                break;
            }
        }
        if (courier.equals("Ambil Sendiri")) {
            XPRData.setKodeUnikAmbilSendiri(getResponsePath(String.format("data[%1s].delivery.tracking_number", indexCourier)));
            LOGGER.info("Kode Unik : " + XPRData.getKodeUnikAmbilSendiri());
        }
        LOGGER.info("id transaksi " + TransactionData.getNomorTransaksi());
    }

    public void getCheapestDenom(String product) {
        if (APIData.getNoTelp() != null) {
            hitDenomListEndpoint(product);
            getCheapestDenomList();
        } else {
            throw new NotFoundException("Please set APIData.setNoTelp()");
        }
    }

    private void hitDenomListEndpoint(String product) {
        String category = product.equals("Pulsa") ? "topup" : "package";
        String prefixNumber = APIData.getNoTelp().substring(0, 4);

        sendRequest("get", "/phone-credits/prepaid-products?prefix=" + prefixNumber + "&category=" + category + "&status[]=available");
        LOGGER.info(response.body().prettyPrint());
        response.then().statusCode(200).extract().response();
    }

    private void getCheapestDenomList() {
        List<String> dataList = response.jsonPath().get("data");

        if (dataList != null) {
            for (int index = 0; index < dataList.size(); index++) {
                getCheapestDenomNameAndPrice(index);
            }
        }
    }

    private void getCheapestDenomNameAndPrice(int index) {
        int denomPrice = Integer.parseInt(response.path("data[" + index + "].partner_package.package.price").toString());

        if (denomPrice < APIData.getHighestDenomPrice()) {
            APIData.setCheapestDenomName(response.path("data[" + index + "].partner_package.package.name").toString());
            APIData.setCheapestDenomPrice(response.path("data[" + index + "].partner_package.package.price").toString());
            APIData.setHighestDenomPrice(denomPrice);
        }
    }

    public String retrievePromotedBonusPercentage() {
        sendRequest("get", "/promoted-pushes/budget");
        String bonus;
        try {
            bonus = getResponsePath("data.promoted_bonus.percentage");
            LOGGER.info("User is super seller!");
        } catch (NullPointerException e) {
            bonus = "0";
            LOGGER.info("User is not Super Seller!");
        }
        LOGGER.info("Promoted Push bonus is " + bonus);
        return bonus;
    }

    public void turnOnPromotedDailyBudget(String credentialUser, String loanState) {
        setUserAuthv4(credentialUser);
        JsonObject scheduleDetail = new JsonObject();
        scheduleDetail.addProperty("amount", "500");
        scheduleDetail.addProperty("action", "activate");
        scheduleDetail.addProperty("weekday", 0);
        if (loanState.equalsIgnoreCase("true")) {
            scheduleDetail.addProperty("use_loan", true);
        } else {
            scheduleDetail.addProperty("use_loan", false);
        }
        JsonArray scheduleOption = new JsonArray();
        scheduleOption.add(scheduleDetail);
        JsonObject payload = new JsonObject();
        payload.add("daily_budget_schedules", scheduleOption);
        LOGGER.info(String.valueOf(payload));
        sendRequestWithBody("patch", "/promoted-pushes/daily-budget", payload);
        LOGGER.info(("Response Is: " + APIData.getResponse().getBody().asString()));
        response.then().assertThat().statusCode(200);
        LOGGER.info("User : " + credentialUser + " has turned on the daily budget with Loan " + loanState + "!");
    }

    /**
     * To create Iklan Lapak
     *
     * @param credentialUser User's credential
     * @param productID      Product id(s) for iklan lapak (the products are separated by comma)
     * @param category       id product category parent
     * @param limit          input true if budget unlimited, and input the budget if it's limited
     */
    public void createIklanLapak(String credentialUser, String productID, int category, String limit) {
        setUserAuthv4(credentialUser);
        // Remove the first Iklan Lapak campaign
        removeFirstIklanLapakCampaign(credentialUser);
        // Setting up the product for Iklan Lapak
        Gson gson = new Gson();
        ArrayList<String> productList = new ArrayList<>(Arrays.asList(productID.split("\\s*,\\s*")));
        // Setting up the budget for Iklan Lapak
        JsonObject budgetLimit = new JsonObject();
        if (limit.equalsIgnoreCase("unlimited")) {
            budgetLimit.addProperty("unlimited", true);
            budgetLimit.addProperty("limit_amount", 12000);
        } else {
            budgetLimit.addProperty("unlimited", false);
            budgetLimit.addProperty("limit_amount", Integer.valueOf(limit));
        }
        // Put it on together as payload
        JsonObject payload = new JsonObject();
        payload.addProperty("category_id", category);
        payload.add("product_ids", gson.toJsonTree(productList));
        payload.add("budget_limit", budgetLimit);
        // Hit the endpoint and send the payload
        LOGGER.info(String.valueOf(payload));
        sendRequestWithBody("post", "/paid-promotion/store-ads", payload);
        response.then().assertThat().statusCode(201);
        LOGGER.info(credentialUser + " has set an iklan lapak with product " + productID + "!");
    }

    /**
     * To remove first Iklan Lapak Campaign
     *
     * @param credentialUser Username
     */
    public void removeFirstIklanLapakCampaign(String credentialUser) {
        setUserAuthv4(credentialUser);
        String firstIklanLapakID = getLatestIklanLapakCampaignId();
        if (!firstIklanLapakID.isEmpty()) {
            sendRequest("delete", "/paid-promotion/store-ads/" + firstIklanLapakID);
            response.then().assertThat().statusCode(200);
        }
    }

    public void removeAllIklanLapakCampaign(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/paid-promotion/store-ads");
        response.then().assertThat().statusCode(200);
        String data = getResponsePath("data");
        if (!data.equals("[]")) {
            ArrayList<Integer> ids = getArrayResponsePathInteger("data.id");
            for (int i = 0; i < ids.size(); i++) {
                sendRequest("delete", "/paid-promotion/store-ads/" + ids.get(i));
                response.then().assertThat().statusCode(200);
            }
        }
    }

    private String getLatestIklanLapakCampaignId() {
        sendRequest("get", "/paid-promotion/store-ads");
        response.then().assertThat().statusCode(200);
        String data = getResponsePath("data");
        String campaignId = "";
        LOGGER.warn("data :" + data);
        if (!data.equals("[]")) {
            campaignId = getResponsePath("data[0].id");
        } else {
            LOGGER.info("Campaign Iklan Lapak still empty");
        }
        return campaignId;
    }

    /**
     * To update state campaign iklan lapak
     *
     * @param credentialUser User's credential
     * @param state          state only active or dormant
     */
    public void updateStateLatestIklanLapakCampaign(String credentialUser, String state) {
        setUserAuthv4(credentialUser);
        String latestCampaignId = getLatestIklanLapakCampaignId();
        if (latestCampaignId.isEmpty()) {
            Assert.fail("Iklan lapak campaign is empty");
        }
        sendRequest("get", "/paid-promotion/store-ads/" + latestCampaignId);
        if (!getResponsePath("data.state").equalsIgnoreCase(state)) {
            sendRequest("patch", "/paid-promotion/store-ads/" + latestCampaignId + "/status");
        }
    }

    /**
     * To create Promosi Otomatis based on limit budget
     *
     * @param credentialUser User's credential
     * @param productID      Product id(s) for iklan lapak (the products are separated by comma)
     * @param campaignName   Campaign's name
     */
    public void createPromotedOtomatisBudget(String credentialUser, String productID, String campaignName) {
        setUserAuthv4(credentialUser);
        // Remove the first otomatis campaign limited budget
        removeAllPromosiOtomatisCampaign(credentialUser);
        // Setting up the product for otomatis campaign limited budget
        Gson gson = new Gson();
        ArrayList<String> productList = new ArrayList<>(Arrays.asList(productID.split("\\s*,\\s*")));
        ArrayList<Integer> schedule = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 0));
        ArrayList<Integer> category = new ArrayList<>();
        ArrayList<Integer> label = new ArrayList<>();
        // Put it on together as payload
        JsonObject payload = new JsonObject();
        payload.add("product_ids", gson.toJsonTree(productList));
        payload.add("category_ids", gson.toJsonTree(category));
        payload.add("label_ids", gson.toJsonTree(label));
        payload.addProperty("limit_type", "budget");
        payload.addProperty("all_items", false);
        payload.addProperty("bid_value", 200);
        payload.addProperty("budget_limit", 1000);
        payload.addProperty("item_type", "product");
        payload.addProperty("end_time", "1970-01-01T00:00:00.000Z");
        payload.add("schedule_days", gson.toJsonTree(schedule));
        payload.addProperty("name", campaignName);
        // Hit the endpoint and send the payload
        LOGGER.info(String.valueOf(payload));
        sendRequestWithBody("post", "/promoted-pushes/campaigns", payload);
        response.then().assertThat().statusCode(201);
        LOGGER.info(credentialUser + " has set a promosi otomatis with product " + productID + "!");
    }

    /**
     * To remove first Promosi Otomatis Campaign
     *
     * @param credentialUser Username
     */
    public void removeAllPromosiOtomatisCampaign(String credentialUser) {
        setUserAuthv4(credentialUser);
        sendRequest("get", "/promoted-pushes/campaigns");
        response.then().assertThat().statusCode(200);
        try {
            if (!getResponsePath("data").isEmpty()) {
                ArrayList<Integer> ids = getArrayResponsePathInteger("data.id");
                for (int i = 0; i < ids.size(); i++) {
                    sendRequest("delete", "/promoted-pushes/campaigns/" + ids.get(i));
                    response.then().assertThat().statusCode(202);
                }
            }
        } catch (NullPointerException err) {
            LOGGER.info("User doesnt have any Promoted Push Grup!");
        }
    }

    public void processSentTransaction(String carrier) {
        sendRequest("get", "/transactions?context=sale&offset=0&limit=1&sort=-date");
        response.then().assertThat().statusCode(200);
        String transactionId = getMapResponsePath("data[0]").get("id").toString();

        Map<String, Object> payload = new HashMap<>();
        payload.put("state", "accepted");
        sendRequestWithBody("put", "/transactions/" + transactionId + "/status", payload);
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }

        Map<String, Object> stateOption = new HashMap<>();
        Random rand = new Random();
        stateOption.put("tracking_number", "BLTEST" + rand.nextInt(100000000));
        stateOption.put("carrier", carrier);
        stateOption.put("manual_switch", false);
        stateOption.put("manual_switch_voucher", false);

        Map<String, Object> deliveryPayload = new HashMap<>();
        deliveryPayload.put("state", "delivered");
        deliveryPayload.put("state_options", stateOption);

        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .body(deliveryPayload)
                .log().all();
        LOGGER.debug(requestSpecification);
        response = requestSpecification.put("/transactions/" + transactionId + "/status");
        LOGGER.info("Request : " + deliveryPayload.toString());
        LOGGER.info("Response : " + response.getBody().asString());
        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
    }

    public void postContactUs(String category) throws InterruptedException {
        int complaintId = 0;
        String categoryField = "permasalahan_kamu";
        switch (category.toLowerCase()) {
            case "pengembalian dana":
                complaintId = 1177;
                break;
            case "konfirmasi pembayaran":
                complaintId = 1000;
                break;
            case "pembatalan transaksi":
                complaintId = 316;
                categoryField = "alasan_pembatalan_transaksi";
                break;
            default:
        }
        Map<String, Object> detailComplaint = new HashMap<>();
        detailComplaint.put("name", "CSI Tester");
        detailComplaint.put("email", "csitester@test.com");
        detailComplaint.put("body", "This is just a test. Please ignore this");
        detailComplaint.put(categoryField, "This is just a test. Please ignore this");
        detailComplaint.put("transaction_id", TransactionData.getNomorTransaksi());
        detailComplaint.put("nomortelepon", dotenv.get("PHONE_NUMBER_COMPALINT"));

        Map<String, Object> payload = new HashMap<>();
        payload.put("detail", detailComplaint);

        String url = "/helps/categories/" + complaintId + "/contact-us";
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .body(payload)
                .log().all();
        LOGGER.debug(requestSpecification);
        response = requestSpecification.post(url);
        if (response.statusCode() != 201) {
            throw new NullPointerException(response.getBody().asString());
        }
        CSIData.setIdComplaint(getMapResponsePath("data.detail").get("discussion_id").toString());
        waitUntilCwpSync();
    }

    private void waitUntilCwpSync() throws InterruptedException {
        String url = "/complaints?as_buyer=true";
        for (int count = 1; count <= 10; count++) {
            sendRequest("get", url);
            String lastTransactionId = getMapResponsePath("data[0]").get("payment_id").toString();
            if (lastTransactionId.equals(TransactionData.getNomorTransaksi())) break;
            //wait 10s to recheck complaint is synced to cwp list
            Thread.sleep(10000);
        }
    }

    public void checkComplaintStatus(String idComplaint, String status) {
        String url = "/complaints/" + idComplaint;
        sendRequest("get", url);
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        assertEquals("Complaint status doesn't match", status, getResponsePath("data.state"));
    }

    public void setComplaintStatus(String status) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("state", status);
        payload.put("reason", "This is just a test please ignore this");
        String url = "/complaints/" + CSIData.getIdComplaint() + "/status";
        sendRequestWithBody("patch", url, payload);
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
    }

    public void getBukaEmasTotalPayment() {
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);
        response = requestSpecification.get("/bullion/transactions/" + InvestmentData.getBukaEmasTransactionId());
        LOGGER.info(("Response BukaEmas Total Payment for top up DANA is: " + response.getBody().asString()));

        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        InvestmentData.setNominalTopUpToDANA(response.path("data.total_payment").toString());
        LOGGER.info("total payment " + InvestmentData.getNominalTopUpToDANA());
    }

    public void getBukaEmasTransactionId() {
        String transactionId;
        requestSpecification = RestAssured.given()
                .baseUri(dotenv.get("API_URL"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIData.getAccessToken())
                .header("Accept", "application/vnd.bukalapak.v4+json")
                .log().all();

        LOGGER.debug(requestSpecification);
        response = requestSpecification.get("/_exclusive/bullion/transactions");
        LOGGER.info(("Response BukaEmas Transactions: " + response.getBody().asString()));

        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        } else {
            ArrayList<Map<String, Object>> transactionIds = response.path("data");
            transactionId = transactionIds.get(0).get("transaction_id").toString();
        }
        LOGGER.info("transaction id " + transactionId);
        InvestmentData.setBukaEmasTransactionId(transactionId);
    }

    public void getProductIdOnActiveEventLuckyDeals() {
        String path = "/_exclusive/lucky-deals/events/actives";
        sendRequest("get", path);
        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        LOGGER.info(("Active Event Lucky Deals: " + APIData.getResponse().getBody().asString()));
        LuckyDealsData.setEventIdLuckyDeals(Integer.parseInt(getMapResponsePath("data").get("id").toString()));
        LuckyDealsData.setProductIdLuckyDeals(Integer.parseInt(getMapResponsePath("data.products[0]").get("id").toString()));
    }

    public void setProductAvailability(String productId, String availability) {
        String url = "/products/" + dotenv.get(productId) + "/status";
        Map<String, Object> payload = new HashMap<>();
        payload.put("state", availability);

        sendRequestWithBody("patch", url, payload);
        response.then().assertThat().statusCode(200);
    }

    public void restockProduct(String productId, String stock) {
        String url = "/products/" + dotenv.get(productId);
        Map<String, Object> payload = new HashMap<>();
        payload.put("stock", Integer.parseInt(stock));

        sendRequestWithBody("patch", url, payload);
        response.then().assertThat().statusCode(200);
    }

    public void setProductAvailabilityAndQuantity(String productName, String quantity) {
        String productId;

        sendRequest("get", "/stores/me/products?keywords=" + productName + "&product_type=sold");

        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }

        ArrayList<Map<String, Object>> product = response.path("data");
        if (product.size() != 1) {
            productId = getProductIdFromStore(productName, false);
        } else {
            productId = product.get(0).get("id").toString();
            String url = "/products/" + productId + "/status";
            Map<String, Object> payload = new HashMap<>();
            payload.put("state", "available");
            sendRequestWithBody("patch", url, payload);
            response.then().assertThat().statusCode(200);
        }

        String url = String.format("/products/%s", productId);
        Map<String, Object> payload = new HashMap<>();
        payload.put("stock", Integer.valueOf(quantity));

        sendRequestWithBody("patch", url, payload);

        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
    }

    public void getPushPrice() {
        sendRequest("get", "/pushes/prices");
        PROMData.setPushPrice(Integer.parseInt(getResponsePath("data.amount")));
    }

    public void retrieveEventIdOnActiveEventPotongHarga() {
        sendRequest("get", "/_exclusive/potong-harga/events");

        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        LOGGER.info(("All Event Potong Harga: " + APIData.getResponse().getBody().asString()));
        String activeData = getMapResponsePath("data.active").toString();
        LOGGER.info("Data is: " + activeData);

        if (activeData.contains("id")) {
            PotongHargaData.setEventId(Integer.parseInt(getMapResponsePath("data.active").get("id").toString()));
            PotongHargaData.setEventName(getMapResponsePath("data.active").get("name").toString());
        } else {
            LOGGER.error("There are No Active Event Yet");
        }
    }

    public void retrieveProductByCategoryPotongHarga(String category) {
        sendRequest("get", "/_exclusive/potong-harga/events/" + PotongHargaData.getEventId() + "/products?category=" + category + "&limit=5");

        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        PotongHargaData.setTotalProductEvent(Integer.parseInt(getMapResponsePath("meta").get("total").toString()));
        PotongHargaData.setProductIdPotongHarga(Integer.parseInt(getMapResponsePath("data[0]").get("id").toString()));
        PotongHargaData.setProductIdMarketplace(getMapResponsePath("data[0]").get("product_id").toString());
        PotongHargaData.setProductSkuId(getMapResponsePath("data[0]").get("product_sku_id").toString());
        PotongHargaData.setProductNamePotongHarga(getMapResponsePath("data[0]").get("name").toString());
        PotongHargaData.setListProductName(getArrayResponsePath("data.name"));
        LOGGER.info("total :" + PotongHargaData.getTotalProductEvent());
        LOGGER.info("Product Id: " + PotongHargaData.getProductIdPotongHarga());
        LOGGER.info("Product Id Marketplace :" + PotongHargaData.getProductIdMarketplace());
        LOGGER.info("Product SKU Id :" + PotongHargaData.getProductSkuId());
        LOGGER.info("Product Name: " + PotongHargaData.getProductNamePotongHarga());
        LOGGER.info("Prouduc Name List: " + PotongHargaData.getListProductName());
    }

    public void retrieveUserHistoryPotongHarga() {
        sendRequest("get", "/_exclusive/potong-harga/histories");

        if (APIData.getResponse().statusCode() != 200) {
            throw new NullPointerException(APIData.getResponse().getBody().asString());
        }
        PotongHargaData.setProductNamePotongHarga(getMapResponsePath("data[0]").get("name").toString());
        PotongHargaData.setStateHistoryProduct(getMapResponsePath("data[0]").get("state").toString());
        PotongHargaData.setCurrentPriceHistoryProduct(Integer.parseInt(getMapResponsePath("data[0]").get("current_price").toString()));
    }

    /**
     * Get neo config by ID
     * API Blueprint: https://blueprint.bukalapak.io/exclusive/t/neo~v2~neo~config~post~exclusive~neo~configs
     *
     * @return String
     * @example: - input  : apiCall.getNeoConfigByID("neo_config_id_here");
     * - output : {"key_1":"value_11","key_2":"value_2"}
     * <p>
     * Refence on how to parse the output and get the value
     * - https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.5/com/google/gson/JsonObject.html
     * - https://www.baeldung.com/gson-string-to-jsonobject
     */
    public String getNeoConfigByID(String id) {
        setUserAuthv4("CONFIRMED");

        Map<String, ArrayList<String>> body = new HashMap<String, ArrayList<String>>();
        ArrayList<String> ids = new ArrayList<String>();
        ids.add(id);
        body.put("id", ids);

        sendRequestWithBody("post", "/_exclusive/neo/configs", body);
        return getResponsePath("data[0].data");
    }

    public void openStore(String storeIdEnv) {
        String url = "/stores/" + dotenv.get(storeIdEnv) + "/status";
        Map<String, Object> payload = new HashMap<>();
        payload.put("state", "open");

        sendRequestWithBody("patch", url, payload);
        response.then().assertThat().statusCode(200);
    }

    public void getProductDetails(String productId) {
        String url = "/products/" + dotenv.get(productId);

        sendRequest("get", url);
        response.then().assertThat().statusCode(200);

        PXData.setProductName(getResponsePath("data.name"));
    }

    public void getActiveVouchersLapak() {
        sendRequest("GET", "/premium-vouchers/me");
    }

    public void createVoucherLapak() {
        Map<String, Object> fixed_price_setting = new HashMap<String, Object>();
        fixed_price_setting.put("discount_amount", 10000);

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("premium_voucher_type", "fixed_price");
        payload.put("voucher_code", "");
        payload.put("start_time", "2022-12-10T00:00:00.000Z");
        payload.put("end_time", "2022-12-11T00:00:00.000Z");
        payload.put("min_purchase", 20000);
        payload.put("usage_limit", 10);
        payload.put("author_publish", true);
        payload.put("daily_usage_limit", 1);
        payload.put("period_usage_limit", 1);
        payload.put("fixed_price_setting", fixed_price_setting);

        LOGGER.info("Payload = " + payload);

        sendRequestWithBody("POST", "/premium-vouchers", payload);
    }

    public void setVoucherStatus(String voucherID, String status) {
        String url = "/premium-vouchers/" + voucherID + "/status";

        String payload = "{\"state\":\"" + status + "%s\"}";
        LOGGER.info("Setting status Voucher ID: " + voucherID + "with body: " + payload);

        sendRequestWithBody("PATCH", url, payload);
    }

    public void deactivateFirstActiveVoucherLapak() {
        getActiveVouchersLapak();

        if (response.getStatusCode() != 200)
            return;

        ArrayList<String> voucherIDs = getArrayResponsePath("data.id");
        if (!voucherIDs.isEmpty()) {
            setVoucherStatus(String.valueOf(voucherIDs.get(0)), "inactive");
        }
    }

    public void setVoucherLapakStatusWithCode(String voucherCode, String status) {
        getActiveVouchersLapak();
        try {
            String voucherID = String.valueOf(getResponsePath("data.find { it.voucher_code == '" + voucherCode + "' }.id"));
            setVoucherStatus(voucherID, status);
        } catch (NullPointerException err) {
            LOGGER.info("There is no active voucher lapak with code : " + voucherCode);
        }
    }

    /**
     * Method to make condition where seller has specific number of vouchers.
     *
     * @param total : expected total active voucher
     *              If currentTotalActive > total, then deactivate some voucher down to total
     *              If currentTotalActive < total, then create vouchers up to total
     */
    public void setCurrentTotalActiveVoucherLapak(int total) {
        LOGGER.info("Target number of active voucher : " + total);

        getActiveVouchersLapak();
        int currentTotalVoucher = getResponsePathSize("data");
        LOGGER.info("Current active voucher total : " + currentTotalVoucher);

        ArrayList<String> voucherIDs = getArrayResponsePath("data.id");
        LOGGER.info("Current active voucher IDs : " + voucherIDs);
        if (currentTotalVoucher > total) {
            for (int i = 0; i < currentTotalVoucher - total; i++) {
                setVoucherStatus(String.valueOf(voucherIDs.get(i)), "inactive");
                response.then().assertThat().statusCode(200);
            }
        } else {
            for (int i = 0; i < total - currentTotalVoucher; i++) {
                createVoucherLapak();
                response.then().assertThat().statusCode(201);
            }
        }
    }

    public void setAutoDonate(String credentialUser, String state) {
        setUserAuthv4(credentialUser);
        String url = "/_exclusive/e-wallets/dana/auto-donate-subscriptions/status";
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("state", state);
        sendRequestWithBody("patch", url, payload);
        response.then().assertThat().statusCode(200);
    }

    public void retrieveProductNabungDiskon() {
        sendRequest("get", "/_exclusive/reverse-installments/products");

        if (response.statusCode() != 200) {
            throw new NullPointerException(response.getBody().asString());
        }
        NabungDiskonData.setTotalProduct(Integer.parseInt(getMapResponsePath("meta").get("total").toString()));
        NabungDiskonData.setProductId(Integer.parseInt(getMapResponsePath("data[0]").get("id").toString()));
        NabungDiskonData.setProductName(getMapResponsePath("data[0]").get("name").toString());
        NabungDiskonData.setProductCurrentStock(Integer.parseInt(getMapResponsePath("data[0]").get("current_stock").toString()));
        NabungDiskonData.setProductTenor(Integer.parseInt(getMapResponsePath("data[0]").get("tenor").toString()));
    }

    public void setTopupBreaker(String usr, Integer amount, Integer times, String va) {
        setUserAuthv4(usr);
        String url = "/dana/topups/transactions";
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("amount", amount);

        for (int i = 0; i <= times; i++) {
            sendRequestWithBody("post", url, payload);
            if (response.getStatusCode() == 403) {
                LOGGER.info("Topup DANA already blocked");
                break;
            } else if (response.getStatusCode() == 201) {
                createDANAtopupVPTrx(getResponsePath("data.id"), va);
            } else {
                throw new NullPointerException(response.getBody().asString());
            }
        }
    }

    private void createDANAtopupVPTrx(String transactionId, String va) {
        Map<String, Object> payload = new HashMap<String, Object>();
        ArrayList<Map<String, Object>> transactions = new ArrayList<Map<String, Object>>();
        Map<String, Object> transaction = new HashMap<String, Object>();

        transaction.put("id", transactionId);
        transaction.put("type", "dana-topup");
        transactions.add(0, transaction);

        payload.put("transactions", transactions);
        payload.put("payment_type", "virtual_account");
        payload.put("virtual_account_bank", va.toLowerCase());

        sendRequestWithBody("post", "/invoices", payload);
        response.then().assertThat().statusCode(201);
    }

    public void createVoucherLapakWithBody(String body, String state) {
        Map<String, Object> payload = null;

        try {
            payload = JsonUtil.jsonToMap(body);
        } catch (Exception e) {
            LogUtil.error("invalid JSON input");
        }

        LocalDate startDate = LocalDate.now();

        if (!state.equalsIgnoreCase("active")) {
            startDate = LocalDate.now().plusDays(2);
        }

        LocalDate endDate = startDate.plusDays(3);

        payload.put("start_time", startDate + "T00:00:00.000Z");
        payload.put("end_time", endDate + "T23:59:59.00Z");
        LogUtil.info("Payload : " + payload);
        sendRequestWithBody("POST", "/premium-vouchers", payload);
        LogUtil.info("Response code : " + response.getStatusCode());
        LogUtil.info("Response Body : " + response.asString());
    }

    public void sendBrazePN(String externalUserId, String campaignId, String apiToken) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("campaign_id", campaignId);

        Map<String, Object> recipientsObject = new HashMap<String, Object>();
        recipientsObject.put("external_user_id", externalUserId);

        List<Map<String, Object>> recipients = new ArrayList<Map<String, Object>>();
        recipients.add(recipientsObject);

        payload.put("recipients", recipients);

        requestSpecification = RestAssured.given()
                .baseUri("https://rest.iad-06.braze.com")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiToken)
                .body(payload);
        response = requestSpecification.post("/campaigns/trigger/send");

        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("message", containsString("success"));
    }

    public String getActiveCampaignID(Integer index) {
        setUserAuthv4("CONFIRMED");
        sendRequest("get", "/search-filters?type=campaign");
        LogUtil.info(getResponsePath("data[" + index + "].values.value"));
        return getResponsePath("data[" + index + "].values.value");
    }

    public void blockUser(String sender, String type, String duration, String receiver) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("partner_id", dotenv.get(receiver + "_ID"));
        payload.put("action", type);
        if (!duration.equals("permanent")) {
            payload.put("period", 30);
        }
        String url = "/chat/rooms/";
        LogUtil.info(payload.toString());
        sendRequestWithBody("patch", url, payload);
        response.then().assertThat().statusCode(200);
    }

    public void checkLastMessage(String userType, String messageType) {
        String path = String.format("/chat/messages?limit=10&partner_id=%1$s", dotenv.get(userType + "_ID"));
        sendRequest("get", path);
        String content = getMapResponsePath("data.messages[0]").get("content").toString();
        assertEquals(content, CHATData.validMessage);
        if (!messageType.equals("text")) {
            String chatType = getMapResponsePath("data.messages[0].types[0]").get("type").toString();
            assertEquals(messageType, chatType);
        }
    }

    public String getStatusCourierToko(String credential, String storeName, String courier) {
        setUserAuthv4(credential);
        String storeId = getStoreId(storeName);
        sendRequest("get", "/stores/" + storeId + "/shipments");
        ArrayList<String> data = getArrayResponsePath("data");
        for (int i = 0; i < data.size(); i++) {
            if ((courier.toLowerCase()).contains(getResponsePath(String.format("data[%1s]" + ".courier_group", i)))) {
                ArrayList<String> dataCourier = getArrayResponsePath(String.format("data[%1s]" + ".couriers", i));
                for (int j = 0; j < dataCourier.size(); j++) {
                    if (courier.equalsIgnoreCase(getResponsePath(String.format("data[%1s]" + ".couriers[%1s]" + ".name", i, j)))) {
                        return getResponsePath(String.format("data[%1s]" + ".couriers[%1s]" + ".active", i, j));
                    }
                }
            }
        }
        return "false";
    }

    public void deleteAllMessageTemplates() {
        sendRequest("get", "/chat/message-templates");
        response.then().assertThat().statusCode(200);
        ArrayList<Map<String, Object>> product = response.path("data");
        for (int i = 0; i < product.size(); i++) {
            sendRequest("delete", "/chat/message-templates/" + product.get(i).get("id"));
            response.then().assertThat().statusCode(202);
        }
    }

    public void createMessageTemplate() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("content", "newmessagetemplate");
        sendRequestWithBody("post", "/chat/message-templates", payload);
        response.then().assertThat().statusCode(201);
    }

    public void deleteItemFromFav(String credentialUser, String productName, String seller) {
        setUserAuthv4(seller);
        String productId = getProductIdFromStore(productName);

        setUserAuthv4(credentialUser);
        sendRequest("delete", "/favorites?product_ids[]=" + productId);
    }

    public void turnOffChatAssistant() {
        sendRequest("get", "/chat/assistants");
        response.then().assertThat().statusCode(200);
        ArrayList<Map<String, Object>> data = response.path("data");

        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> payload = new HashMap<>();
            payload.put("active", false);
            payload.put("message", data.get(i).get("message"));
            sendRequestWithBody("patch", "/chat/assistants/" + data.get(i).get("id"), payload);
            response.then().assertThat().statusCode(200);
        }
    }

    public String getMainAddressTitle(String credential) {
        setUserAuthv4(credential);
        sendRequest("get", "/addresses");
        response.then().assertThat().statusCode(200);
        ArrayList<String> data = getArrayResponsePath("data");
        for (int i = 0; i < data.size(); i++) {
            if (getResponsePath(String.format("data[%1s]" + ".primary", i)).equals("true")) {
                XPRData.setActualTitleMainAddress(getResponsePath(String.format("data[%1s]" + ".title", i)));
                break;
            }
        }
        return XPRData.getActualTitleMainAddress();
    }

    public void cancelTransaction(String credential) {
        setForAPIcall();
        setUserAuthv4(credential);
        sendRequest("get", "/invoices?limit=1&states[]=pending");
        response.then().assertThat().statusCode(200);
        String id = getResponsePath("data.id");

        Map<String, String> reason = new HashMap<>();
        reason.put("reason", "other");
        reason.put("notes", "Deskripsi barang yang saya beli tidak sesuai dengan keinginan saya");

        Map<String, Object> payload = new HashMap<>();
        payload.put("state", "cancelled");
        payload.put("state_options", reason);

        String idInvoice = id.replaceAll("[^a-zA-Z0-9]", "");
        sendRequestWithBody("patch", "/invoices/"+ idInvoice +"/status", payload);
        response.then().assertThat().statusCode(200);
    }

    public void getSpecificMessage(String messageType) {
        String path = String.format("/chat/messages?limit=1&keywords=%1$s", messageType);
        sendRequest("get", path);
        if (response.statusCode() == 200) {
            String content = getMapResponsePath("data.messages[0]").get("content").toString();
            String user = getMapResponsePath("data.users[0]").get("username").toString();
            assertTrue(content.contains(messageType));
            CHATData.setDynamicChatData(content);
            CHATData.setSecondDynamicChatData(user);
        }
    }
}
