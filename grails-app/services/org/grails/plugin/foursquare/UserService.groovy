package org.grails.plugin.foursquare

import grails.converters.JSON

class UserService extends FoursquareService {

    public List<User> searchUsersByPhone(String oauthToken, String phone) {
        List<User> userList = []
        String urlString = getSearchUserUrl(oauthToken, 'phone', phone)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.results.each {
                User user = new User(it)
                userList << user
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return userList
    }

    public List<User> searchUsersByEmail(String oauthToken, String email) {
        List<User> userList = []
        String urlString = getSearchUserUrl(oauthToken, 'email', email)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.results.each {
                User user = new User(it)
                userList << user
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return userList
    }

    public List<User> searchUsersByTwitter(String oauthToken, String twitter) {
        List<User> userList = []
        String urlString = getSearchUserUrl(oauthToken, 'twitter', twitter)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.results.each {
                User user = new User(it)
                userList << user
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return userList
    }

    public List<User> searchUsersByName(String oauthToken, String name) {
        List<User> userList = []
        String urlString = getSearchUserUrl(oauthToken, 'name', name)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.results.each {
                User user = new User(it)
                userList << user
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return userList
    }

    List<Checkin> getUserCheckins(String oauthToken) {
        List<Checkin> checkinList = []
        String urlString = getUserCheckinsUrl(oauthToken)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.checkins.items.each {def checkinData ->
                Checkin checkin = new Checkin(checkinData)
                checkinList.add(checkin)
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return checkinList
    }

    List<User> getOwnFriendsList(String oauthToken) {
        List<User> userList = []
        String urlString = getFriendsListUrl(oauthToken)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.friends.items.each {def userData ->
                User user = new User(userData)
                userList.add(user)
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return userList
    }

    List<User> getUserFriendsList(String oauthToken, String userId) {
        List<User> userList = []
        String urlString = getFriendsListUrl(oauthToken)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.friends.items.each {def userData ->
                User user = new User(userData)
                userList.add(user)
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return userList
    }

    User unFriendUser() {
//        https://api.foursquare.com/v2/users/USER_ID/unfriend
    }

    private getFriendsListUrl(String oauthToken, String userId = 'self') {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/users/${userId}/friends?oauth_token=${oauthToken}&v=${date}"
        urlString
    }

    private getSearchUserUrl(String oauthToken, String selector, String value) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/users/search?${selector.encodeAsURL()}=${value}&oauth_token=${oauthToken}&v=${date}"
        urlString
    }

    private getUserCheckinsUrl(String oauthToken) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/users/self/checkins?oauth_token=${oauthToken}&v=${date}&limit=100"
        urlString
    }
}
