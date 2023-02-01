package com.example.shortlyapphijfqp.utils

import com.example.data.db.entities.ShortenEntity
import com.example.data.model.ShortenModel
import com.example.domain.model.Shorten

val shortenModel = ShortenModel(
    shortenId = 0,
    code = "gGqR5P",
    shortLink = "shrtco.de/gGqR5P",
    originalLink = "www.google.com"
)

val shorten = Shorten(
    shortenId = 0,
    code = "gGqR5P",
    shortLink = "shrtco.de/gGqR5P",
    originalLink = "www.google.com"
)

val shortenEntity = ShortenEntity(
    shortenId = 0,
    code = "gGqR5P",
    short_link = "shrtco.de/gGqR5P",
    original_link = "www.google.com"
)