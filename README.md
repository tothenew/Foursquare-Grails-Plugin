# Plugin Documentation

This plugin serves as grails wrapper over the existing foursquare API. The plugin provides utilities that provide more rich and coherent experience while interacting with the Foursquare API. Using this plugin to access the Foursquare API you get Custom Built and highly enriched Objects as compared to the traditional API calls than return some JSON content.
Features:-

This plugin provides grails wrapper over existing foursquare api:-

    Search nearby locations/venue over foursquare.
    Search venues based on latitude & longitude.
    Search venues based on address.
    Get complete details for a venue like categories, checkis, tips, pictures, contact, website, tips, tags.
    Search users on foursquare based on their name, email, twitter-id, phone.
    Retrieve the checking done by a user.
    Retrieve friends of any user.
    Get a Checkin detail.

##  How to use:-
Registering app on Foursquare:-

Just create a foursquare app at https://foursquare.com/developers/apps and get the Client ID & Client Secret for that app.

## Set ClientId and ClientSecret :-

Set the value of clientId and client secret in the application's Config.groovy as

    fourSquare.clientId='XXX4CPK5UGC1MUDXXXXXXXXXXX'
    fourSquare.secret='XXXXTWGFEAFFTDUXXXXXXXXX'

## Use plugin :-

To use the plugin features, just inject the service in your controller/services/domain

def foursquareService

and it will provide all the methods available in the plugin.

Examples : API calls which do not require authorized user.

    searchVenues(String latitude, String longitude)
    searchVenues(String address)
    getVenueData(String foursquareLocationId)
    getFoursquareVenueCheckinsCount(String foursquareLocationId), etc

API calls requiring authenticated user.

    getCheckinDetails(String oauthToken, String checkinId)
    searchUsersByPhone(String oauthToken, String phone)
    getUserCheckins(String oauthToken)
    getUserFriendsList(String oauthToken, String userId)
    searchUsersByEmail(String oauthToken, String email)
    getCheckinDetails(String oauthToken, String checkinId)

OauthToken is the token obtained by authenticating any foursquare user using OAuth 2.0
