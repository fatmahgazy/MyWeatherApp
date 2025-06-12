package org.codeforegypt.myweatherapp.presentation.utils
import androidx.annotation.DrawableRes
import org.codeforegypt.myweatherapp.R

object WeatherIconUtils {
    @DrawableRes
    fun getWeatherIcon(code: Int, isDay: Boolean): Int {
        if (code == 0) {
            return if (isDay) R.drawable.clear_sky1 else R.drawable.clear_sky
        }

        if (code == 1) {
            return if (isDay) R.drawable.mainly_clear1 else R.drawable.mainly_clear
        }
        if (code == 2){
            return if (isDay) R.drawable.partly_cloudy1 else R.drawable.partly_cloudy
        }

        if (code == 3) {
            return if (isDay) R.drawable.overcast else R.drawable.overcast
        }

        if (code == 45) {
            return if (isDay) R.drawable.fog1 else R.drawable.fog
        }
        if (code == 48){
            return if (isDay) R.drawable.depositing_rime_fog1 else R.drawable.depositing_rime_fog
        }

        if (code == 51) {
            return if (isDay) R.drawable.drizzle_light1 else R.drawable.drizzle_light
        }
        if (code == 53){
            return if (isDay) R.drawable.drizzle_moderate1 else R.drawable.drizzle_moderate
        }
        if (code == 55){
            return if (isDay) R.drawable.drizzle_intensity1 else R.drawable.drizzle_intensity
        }

        if (code == 56) {
            return if (isDay) R.drawable.freezing_drizzle_light1 else R.drawable.freezing_drizzle_light
        }
        if (code == 57){
            return if (isDay) R.drawable.freezing_drizzle_intensity1 else R.drawable.freezing_drizzle_intensity
        }
        if (code == 61) {
            return if (isDay) R.drawable.rain_slight1 else R.drawable.rain_slight
        }
        if (code == 63){
            return if (isDay) R.drawable.rain_moderate1 else R.drawable.rain_moderate
        }
        if (code == 65){
            return if (isDay) R.drawable.rain_intensity1 else R.drawable.rain_intensity
        }

        if (code == 66) {
            return if (isDay) R.drawable.freezing_light1 else R.drawable.freezing_light
        }
        if (code == 67){
            return if (isDay) R.drawable.freezing_heavy1 else R.drawable.freezing_heavy
        }

        if (code == 71 ) {
            return if (isDay) R.drawable.snow_fall_light1 else R.drawable.snow_fall_light
        }
        if (code == 73 ){
            return if (isDay) R.drawable.snow_fall_moderate1 else R.drawable.snow_fall_moderate
        }
        if (code == 75){
            return if (isDay) R.drawable.snow_fall_intensity1 else R.drawable.snow_fall_intensity
        }

        if (code == 77) {
            return if (isDay) R.drawable.snow_grains1 else R.drawable.snow_grains
        }

        if (code == 80 ) {
            return if (isDay) R.drawable.rain_shower_slight1 else R.drawable.rain_shower_slight
        }
        if (code == 81){
            return if (isDay) R.drawable.rain_shower_moderate1 else R.drawable.rain_shower_moderate
        }
         if (code == 82){
             return if (isDay) R.drawable.rain_shower_violent1 else R.drawable.rain_shower_violent
         }

        if (code == 85) {
            return if (isDay) R.drawable.snow_shower_slight1 else R.drawable.snow_shower_slight
        }
        if (code == 86){
            return if (isDay) R.drawable.snow_shower_heavy1 else R.drawable.snow_shower_heavy
        }

        if (code == 95) {
            return if (isDay) R.drawable.thunderstrom_slight_or_moderate1 else R.drawable.thunderstrom_slight_or_moderate
        }

        if (code == 96) {
            return if (isDay) R.drawable.thunderstrom_with_slight_hail1 else R.drawable.thunderstrom_with_slight_hail
        }
        if (code == 99){
            return if (isDay) R.drawable.thunderstrom_with_heavy_hail1 else R.drawable.thunderstrom_with_heavy_hail
        }

        return if (isDay) R.drawable.mainly_clear1 else R.drawable.mainly_clear
    }
}
