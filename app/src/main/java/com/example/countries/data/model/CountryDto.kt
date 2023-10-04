package com.example.countries.data.model

import com.google.gson.annotations.SerializedName

data class CountryDto(
    val altSpellings: List<String>?,
    val area: Double,
    val borders: List<String>?,
    val capital: List<String>?,
    @SerializedName("capitalInfo")
    val capitalInfoDto: CapitalInfoDto,
    val carDto: CarDto,
    val cca2: String,
    val cca3: String,
    val ccn3: String,
    val cioc: String,
    @SerializedName("coatOfArms")
    val coatOfArmsDto: CoatOfArmsDto,
    val continents: List<String>,
    @SerializedName("currencies")
    val currenciesDto: CurrenciesDto?,
    @SerializedName("demonyms")
    val demonymsDto: DemonymsDto,
    val fifa: String,
    val flag: String,
    @SerializedName("flags")
    val flagsDto: FlagsDto?,
    @SerializedName("gini")
    val giniDto: GiniDto,
    @SerializedName("idd")
    val iddDto: IddDto,
    val independent: Boolean,
    val landlocked: Boolean,
    @SerializedName("languages")
    val languagesDto: LanguagesDto?,
    val latlng: List<Double>,
    @SerializedName("maps")
    val mapsDto: MapsDto,
    @SerializedName("name")
    val nameDto: NameDto?,
    val population: Int,
    @SerializedName("postalCode")
    val postalCodeDto: PostalCodeDto,
    val region: String,
    val startOfWeek: String,
    val status: String,
    val subregion: String,
    val timezones: List<String>,
    val tld: List<String>,
    @SerializedName("translations")
    val translationsDto: TranslationsDto,
    val unMember: Boolean
)