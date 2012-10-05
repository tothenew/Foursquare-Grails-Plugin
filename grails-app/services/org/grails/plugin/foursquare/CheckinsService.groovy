package org.grails.plugin.foursquare

import grails.converters.JSON

class CheckinsService extends FoursquareService {


    Checkin getCheckinDetails(String oauthToken, String checkinId) {
        Checkin checkin
        String urlString = getCheckinDataUrl(oauthToken, checkinId)
        try {
            URL url = new URL(urlString)
            def jsonString = JSON.parse(url.text)
            checkin = new Checkin(jsonString?.response?.checkin, true)
        } catch (Exception e) {
            e.printStackTrace()
        }
        return checkin
    }

    private getCheckinDataUrl(String oauthToken, String checkinId) {
        String date = (new Date()).format('yyyyMMdd')
        String urlString = "https://api.foursquare.com/v2/checkins/${checkinId}?oauth_token=${oauthToken}&v=${date}&limit=100"
        urlString
    }

}
