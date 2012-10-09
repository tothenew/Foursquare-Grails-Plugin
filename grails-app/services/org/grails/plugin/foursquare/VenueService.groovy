package org.grails.plugin.foursquare

import grails.converters.JSON

class VenueService extends FoursquareService {

    public List<Venue> searchVenues(String latitude, String longitude) {
        List<Venue> venueList = []
        String urlString = getSearchVenueUrl(latitude, longitude)
        try {
            String responseStr = getResponseOfQuery(urlString)
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

    public List<Venue> searchVenues(String address) {
        List<Venue> venueList = []
        String urlString = getSearchVenueUrl(address)
        try {
            String responseStr = getResponseOfQuery(urlString)
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


    public Venue getVenueData(String foursquareLocationId) {
        Venue venue
        String urlString = getVenueDataUrl(foursquareLocationId)
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

    public Long getFoursquareVenueCheckinsCount(String foursquareLocationId) {
        Long foursquareLocationCheckinCount = 0
        String urlString = getVenueDataUrl(foursquareLocationId)
        try {
            URL url = new URL(urlString)
            def jsonString = JSON.parse(url.text)
            foursquareLocationCheckinCount = jsonString.response.venue.stats.checkinsCount

        } catch (Exception e) {
            e.printStackTrace()
        }
        return foursquareLocationCheckinCount
    }


    public getSearchVenueUrl(String latitude, String longitude) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/venues/search?ll=${latitude},${longitude}&client_id=${clientId}&client_secret=${clientSecret}&v=${date}"
        urlString
    }

    public getSearchVenueUrl(String address) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/venues/search?near=${address.encodeAsURL()}&client_id=${clientId}&client_secret=${clientSecret}&v=${date}"
        urlString
    }

    public getVenueDataUrl(String foursquareLocationId) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/venues/${foursquareLocationId}?&client_id=${clientId}&client_secret=${clientSecret}&v=${date}"
        return urlString
    }
}
