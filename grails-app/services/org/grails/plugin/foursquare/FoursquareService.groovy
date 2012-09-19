package org.grails.plugin.foursquare

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class FoursquareService {

    static transactional = false

    String getResponseFromFourSquare(String urlPath) {
        String responseData
        try {
            URL url = new URL(urlPath)
            responseData = url.text
        } catch (Exception e) {
            e.printStackTrace()
        }
        return responseData
    }

    String getResponseOfFoursquareQuery(String urlString) {
        println("****** Querying URL: ${urlString}")
        String responseString = ''
        try {
            URL page = new URL(urlString);
            responseString = page.text
        } catch (Exception e) {
            e.printStackTrace()
        }
        return responseString
    }

    public List<Venue> searchLocations(String latitude, String longitude) {
        List<Venue> venueList = []
        String urlString = getSearchFoursquareLocationUrl(latitude, longitude)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.venues.each {
                Venue venue = new Venue(it)
                venueList << venue
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return venueList
    }

    public List<User> searchUsersByPhone(String oauthToken, String phone) {
        List<User> userList = []
        String urlString = getSearchFoursquareUserUrl(oauthToken, 'phone', phone)
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
        String urlString = getSearchFoursquareUserUrl(oauthToken, 'email', email)
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
        String urlString = getSearchFoursquareUserUrl(oauthToken, 'twitter', twitter)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.results.each {
                println '-----------it--------' + it
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
        String urlString = getSearchFoursquareUserUrl(oauthToken, 'name', name)
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

    public List<Venue> searchLocations(String address) {
        List<Venue> venueList = []
        String urlString = getSearchFoursquareLocationUrl(address)
        try {
            String responseStr = getResponseOfFoursquareQuery(urlString)
            def jsonString = JSON.parse(responseStr)
            jsonString.response.venues.each {
                Venue venue = new Venue(it)
                venueList << venue
            }
        } catch (Exception e) {
            log.error(e.message)
        }
        return venueList
    }

    public Long getFoursquareLocationCheckinsCount(String foursquareLocationId) {
        Long foursquareLocationCheckinCount = 0
        String urlString = getFoursquareLocationDataUrl(foursquareLocationId)
        try {
            URL url = new URL(urlString)
            def jsonString = JSON.parse(url.text)
            foursquareLocationCheckinCount = jsonString.response.venue.stats.checkinsCount

        } catch (Exception e) {
            e.printStackTrace()
        }
        return foursquareLocationCheckinCount
    }

    public Venue getFoursquareLocationData(String foursquareLocationId) {
        Venue venue
        String urlString = getFoursquareLocationDataUrl(foursquareLocationId)
        try {
            URL url = new URL(urlString)
            def jsonString = JSON.parse(url.text)
            venue = new Venue(jsonString.response.venue)
            jsonString.response.venue.tips.groups.each {def group ->
                group.items.each { def tipData ->
                    Tip tip = new Tip(tipData)
                    venue.tips << tip
                }
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        return venue
    }

    public List<Tip> getRecentFoursquareCheckins(String foursquareLocationId, Integer count = 1) {
        List<Tip> tipList = []
        String urlString = getFoursquareLocationDataUrl(foursquareLocationId)
        try {
            URL url = new URL(urlString)
            def jsonString = JSON.parse(url.text)
            jsonString.response.venue.tips.groups.each {def group ->
                group.items.eachWithIndex { def tipData, Integer index ->
                    if (index < count) {
                        Tip tip = new Tip(tipData)
                        tipList << tip
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        return tipList
    }

    private getSearchFoursquareLocationUrl(String latitude, String longitude) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/venues/search?ll=${latitude},${longitude}&client_id=${clientId}&client_secret=${clientSecret}&v=${date}"
        urlString
    }

    private getSearchFoursquareUserUrl(String oauthToken, String selector, String value) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/users/search?${selector.encodeAsURL()}=${value}&oauth_token=${oauthToken}&v=${date}"
        urlString
    }

    private getSearchFoursquareLocationUrl(String address) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/venues/search?near=${address.encodeAsURL()}&client_id=${clientId}&client_secret=${clientSecret}&v=${date}"
        urlString
    }


    private getFoursquareLocationDataUrl(String foursquareLocationId) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/venues/${foursquareLocationId}?&client_id=${clientId}&client_secret=${clientSecret}&v=${date}"
        return urlString
    }

    private getClientSecret() {
        return ConfigurationHolder.config.fourSquare.secret
    }

    private getClientId() {
        return ConfigurationHolder.config.fourSquare.clientId
    }

}
