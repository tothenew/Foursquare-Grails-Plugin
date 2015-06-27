package org.grails.plugin.foursquare

import grails.converters.JSON
import grails.util.Holders

class FourSquareCommonService {

    String getResponseOfQuery(String urlString) {
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


    public getClientSecret() {
        return Holders.config.fourSquare.secret
    }

    public getClientId() {
        return Holders.config.fourSquare.clientId
    }
}
