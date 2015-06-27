package org.grails.plugin.foursquare

class Tip {

    String id
    Date createdAt
    String text
    String likesCount
    String creatorId
    String creatorName
    String creatorPhotoUrl
    String creatorCity
    String creatorGender

    Tip() {

    }


    Tip(def tipData) {
        this.id = tipData.id
        this.text = tipData?.text
        this.createdAt = new Date(tipData.createdAt.toLong() * 1000)
        this.likesCount = tipData?.likes?.summary
        this.creatorId = tipData?.user?.id
        this.creatorCity = tipData.user?.homeCity
        this.creatorGender = tipData.user?.gender
        this.creatorName = "${tipData?.user?.firstName} ${tipData?.user?.lastName ?: ''}"
        this.creatorPhotoUrl = tipData?.user?.photo?.prefix + '30x30' + tipData?.user?.photo?.suffix
    }
}
