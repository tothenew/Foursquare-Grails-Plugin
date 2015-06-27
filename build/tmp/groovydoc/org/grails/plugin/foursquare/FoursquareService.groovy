package org.grails.plugin.foursquare

import grails.util.Holders


class FoursquareService {

    static transactional = false

    @Delegate
    FourSquareCommonService fourSquareCommonService
    @Delegate
    UserService userService
    @Delegate
    VenueService venueService
    @Delegate
    CheckinsService checkinsService

}
