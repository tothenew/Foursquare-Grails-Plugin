package org.grails.plugin.foursquare

class VenuePhoto {
    String id
    Date createdAt
    String sourceName
    String sourceUrl
    String url
    Long height
    Long width
    String creatorId
    String creatorFirstName
    String creatorLastName
    String creatorPhotoUrl
    String visibility

    VenuePhoto() {

    }

    VenuePhoto(def photoData) {
        this.id = photoData.id
        this.createdAt = new Date(photoData.createdAt?.toLong() * 1000)
        this.sourceName = photoData?.source?.name
        this.sourceUrl = photoData?.source?.url
        this.url = photoData.prefix + photoData.suffix
        this.height = photoData?.height
        this.width = photoData?.width
        this.creatorId = photoData.user.id
        this.creatorFirstName = photoData?.user?.firstName
        this.creatorLastName = photoData?.user?.lastName
        this.creatorPhotoUrl = photoData?.user?.photo?.prefix + photoData?.user?.photo?.suffix
        this.visibility = photoData?.visibility
    }

}
