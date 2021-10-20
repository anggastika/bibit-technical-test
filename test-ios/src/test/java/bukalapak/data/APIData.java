package bukalapak.data;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class APIData {

    private static String authToken;
    private static String contentType = "application/json";
    private static String appVersion;
    private static String userAgentTest;
    private static String userAgent;
    private static String identity;
    private static String apiUrl;
    private static String accountsUrl;
    private static String clientId;
    private static String clientSecretId;
    private static String accessToken;
    private static Object body;
    private static RequestSpecification requestSpecification;
    private static Response response;
    private static String accessTokenExclusiveStaging;
    private static List<String> inspirationIds = new ArrayList<>();
    private static String bukalapakIdentity;
    private static String message;
    private static String logUserID;
    private static String noTelp;
    private static String cheapestDenomName;
    private static String cheapestDenomPrice;
    private static String username;
    private static int highestDenom = 500000;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        APIData.accessToken = accessToken;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        APIData.authToken = authToken;
    }

    public static String getContentType() {
        return contentType;
    }

    public static void setContentType(String contentType) {
        APIData.contentType = contentType;
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static void setAppVersion(String appVersion) {
        APIData.appVersion = appVersion;
    }

    public static String getUserAgentTest() {
        return userAgentTest;
    }

    public static void setUserAgentTest(String userAgentTest) {
        APIData.userAgentTest = userAgentTest;
    }

    public static String getUserAgent() {
        return userAgent;
    }

    public static void setUserAgent(String userAgent) {
        APIData.userAgent = userAgent;
    }

    public static String getIdentity() {
        return identity;
    }

    public static void setIdentity(String identity) {
        APIData.identity = identity;
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    public static void setApiUrl(String apiUrl) {
        APIData.apiUrl = apiUrl;
    }

    public static String getAccountsUrl() {
        return accountsUrl;
    }

    public static void setAccountsUrl(String accountsUrl) {
        APIData.accountsUrl = accountsUrl;
    }

    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        APIData.clientId = clientId;
    }

    public static String getClientSecretId() {
        return clientSecretId;
    }

    public static void setClientSecretId(String clientSecretId) {
        APIData.clientSecretId = clientSecretId;
    }

    public static Object getBody() {
        return body;
    }

    public static void setBody(Object body) {
        APIData.body = body;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public static void setRequestSpecification(RequestSpecification requestSpecification) {
        APIData.requestSpecification = requestSpecification;
    }

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        APIData.response = response;
    }

    public static String getAccessTokenExclusiveStaging() {
        return accessTokenExclusiveStaging;
    }

    public static void setAccessTokenExclusiveStaging(String token) {
        accessTokenExclusiveStaging = token;
    }

    public static List<String> getInspirationIds() {
        return inspirationIds;
    }

    public static void setInspirationIds(String id) {
        inspirationIds.add(id);
    }

    public static String getBukalapakIdentity() {
        return bukalapakIdentity;
    }

    public static void setBukalapakIdentity(String api_bukalapak_identity) {
        APIData.bukalapakIdentity = api_bukalapak_identity;
    }

    public static String getLogMessage() {
        return message;
    }

    public static void setLogMessage(String message) {
        APIData.message = message;
    }

    public static void setLogUserID(String logUserID) {
        APIData.logUserID = logUserID;
    }

    public static String getLogUserID() {
        return logUserID;
    }

    public static String getNoTelp() {
        return noTelp;
    }

    public static void setNoTelp(String noTelp) {
        APIData.noTelp = noTelp;
    }

    public static String getCheapestDenomName() {
        return cheapestDenomName;
    }

    public static void setCheapestDenomName(String cheapestDenomName) {
        APIData.cheapestDenomName = cheapestDenomName;
    }

    public static String getCheapestDenomPrice() {
        return cheapestDenomPrice;
    }

    public static void setCheapestDenomPrice(String cheapestDenomPrice) {
        APIData.cheapestDenomPrice = cheapestDenomPrice;
    }

    public static int getHighestDenomPrice() {
        return highestDenom;
    }

    public static void setHighestDenomPrice(int highestDenom) {
        APIData.highestDenom = highestDenom;
    }

    public static String getAPIUsername() {
        return username;
    }

    public static void setAPIUsername(String username) {
        APIData.username = username;
    }
}
